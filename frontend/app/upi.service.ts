import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UpiService {
  private baseUrl = 'http://localhost:8080/api/upi'; // Spring Boot backend URL

  constructor(private http: HttpClient) {}

  // Toggle UPI status (Enable/Disable)
  toggleUpi(phone: string, enable: boolean): Observable<any> {
    const params = new HttpParams().set('phone', phone).set('enable', enable.toString());
    return this.http.post(`${this.baseUrl}/toggle-upi`, params);
  }

  // Check Account Balance
  checkBalance(phone: string): Observable<any> {
    const params = new HttpParams().set('phone', phone);
    return this.http.get(`${this.baseUrl}/balance`, { params });
  }

  // Add money
  addMoney(phone: string, amount: number): Observable<any> {
    const params = new HttpParams().set('phone', phone).set('amount', amount.toString());
    return this.http.post(`${this.baseUrl}/add-money`, params);
  }

  // Transfer money
  transferMoney(from: string, to: string, amount: number): Observable<any> {
    const params = new HttpParams()
      .set('from', from)
      .set('to', to)
      .set('amount', amount.toString());
    return this.http.post(`${this.baseUrl}/transfer`, params);
  }
}
