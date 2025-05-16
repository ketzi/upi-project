import { Component } from '@angular/core';
import { UpiService } from '../upi.service';

@Component({
  selector: 'app-upi-toggle',
  templateUrl: './upi-toggle.component.html',
  styleUrls: ['./upi-toggle.component.css']
})
export class UpiToggleComponent {
  phone: string = '';
  enable: boolean = false;

  constructor(private upiService: UpiService) {}

  toggleUpi() {
    this.upiService.toggleUpi(this.phone, this.enable).subscribe(response => {
      alert(response);
    });
  }
}
