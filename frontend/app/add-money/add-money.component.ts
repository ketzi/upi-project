import { Component } from '@angular/core';
import { UpiService } from '../upi.service';

@Component({
  selector: 'app-add-money',
  templateUrl: './add-money.component.html',
  styleUrls: ['./add-money.component.css']
})
export class AddMoneyComponent {
  phone: string = '';
  amount: number = 0;

  constructor(private upiService: UpiService) {}

  addMoney() {
    this.upiService.addMoney(this.phone, this.amount).subscribe(response => {
      alert(response);
    });
  }
}
