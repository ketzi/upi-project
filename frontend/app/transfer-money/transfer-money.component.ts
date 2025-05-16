import { Component } from '@angular/core';
import { UpiService } from '../upi.service';

@Component({
  selector: 'app-transfer-money',
  templateUrl: './transfer-money.component.html',
  styleUrls: ['./transfer-money.component.css']
})
export class TransferMoneyComponent {
  from: string = '';
  to: string = '';
  amount: number = 0;

  constructor(private upiService: UpiService) {}

  transferMoney() {
    this.upiService.transferMoney(this.from, this.to, this.amount).subscribe(response => {
      alert(response);
    });
  }
}
