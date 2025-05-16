import { Component } from '@angular/core';
import { UpiService } from '../upi.service';

@Component({
  selector: 'app-check-balance',
  templateUrl: './check-balance.component.html',
  styleUrls: ['./check-balance.component.css']
})
export class CheckBalanceComponent {
  phone: string = '';
  balance: number | null = null;

  constructor(private upiService: UpiService) {}

  checkBalance() {
    this.upiService.checkBalance(this.phone).subscribe(response => {
      this.balance = response;
    });
  }
}
