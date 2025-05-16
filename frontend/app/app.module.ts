import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UpiToggleComponent } from './upi-toggle/upi-toggle.component';
import { CheckBalanceComponent } from './check-balance/check-balance.component';
import { AddMoneyComponent } from './add-money/add-money.component';
import { TransferMoneyComponent } from './transfer-money/transfer-money.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    UpiToggleComponent,
    CheckBalanceComponent,
    AddMoneyComponent,
    TransferMoneyComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

