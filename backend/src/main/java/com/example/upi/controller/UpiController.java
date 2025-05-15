package com.example.upi.controller;

import com.example.upi.model.UserAccount;
import com.example.upi.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/upi")
public class UpiController {

    private final UserAccountRepository repo;

    public UpiController(UserAccountRepository repo) {
        this.repo = repo;
    }

    // Enable or Disable UPI
    @PostMapping("/toggle-upi")
    public ResponseEntity<String> toggleUpi(@RequestParam String phone, @RequestParam boolean enable) {
        UserAccount account = repo.findByPhoneNumber(phone).orElseGet(() -> {
            UserAccount acc = new UserAccount();
            acc.setPhoneNumber(phone);
            return acc;
        });
        account.setUpiEnabled(enable);
        repo.save(account);
        return ResponseEntity.ok("UPI " + (enable ? "Enabled" : "Disabled") + " for " + phone);
    }

    // Check Balance
    @GetMapping("/balance")
    public ResponseEntity<?> checkBalance(@RequestParam String phone) {
        return repo.findByPhoneNumber(phone)
            .<ResponseEntity<?>>map(acc -> ResponseEntity.ok(acc.getBalance()))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with phone " + phone + " not found"));
    }

    // Add Money
    @PostMapping("/add-money")
    public ResponseEntity<String> addMoney(@RequestParam String phone, @RequestParam double amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount!");
        }
        return repo.findByPhoneNumber(phone).map(acc -> {
            if (acc.getBalance() + amount > 100000) {
                return ResponseEntity.badRequest().body("Max balance 1 Lakh reached!");
            }
            acc.setBalance(acc.getBalance() + amount);
            repo.save(acc);
            return ResponseEntity.ok("Money added. New Balance: ₹" + acc.getBalance());
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with phone " + phone + " not found"));
    }

    // Transfer
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        if (amount > 20000) {
            return ResponseEntity.badRequest().body("Limit: Max ₹20k per transaction!");
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount!");
        }

        var senderOpt = repo.findByPhoneNumber(from);
        var receiverOpt = repo.findByPhoneNumber(to);

        if (senderOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sender not found: " + from);
        }
        if (receiverOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receiver not found: " + to);
        }

        UserAccount sender = senderOpt.get();
        UserAccount receiver = receiverOpt.get();

        if (!sender.isUpiEnabled() || !receiver.isUpiEnabled()) {
            return ResponseEntity.badRequest().body("Both accounts must have UPI enabled.");
        }

        if (sender.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Insufficient balance.");
        }

        LocalDate today = LocalDate.now();
        if (sender.getLastTransferDate() == null || !sender.getLastTransferDate().equals(today)) {
            sender.setDailyTransferCount(0);
            sender.setDailyTransferAmount(0);
            sender.setLastTransferDate(today);
        }

        if (sender.getDailyTransferCount() >= 3) {
            return ResponseEntity.badRequest().body("Max 3 transfers per day exceeded.");
        }

        if (sender.getDailyTransferAmount() + amount > 50000) {
            return ResponseEntity.badRequest().body("Max ₹50k transfer per day exceeded.");
        }

        sender.setBalance(sender.getBalance() - amount);
        sender.setDailyTransferCount(sender.getDailyTransferCount() + 1);
        sender.setDailyTransferAmount(sender.getDailyTransferAmount() + amount);

        receiver.setBalance(receiver.getBalance() + amount);

        repo.save(sender);
        repo.save(receiver);

        return ResponseEntity.ok("₹" + amount + " transferred from " + from + " to " + to);
    }
}
