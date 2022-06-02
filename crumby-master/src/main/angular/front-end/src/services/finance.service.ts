import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FinanceService {
  private baseUrl: string;
  constructor(private client: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
  }

  getStockByTicker(ticker: string) {
    return this.client.get(`${this.baseUrl}stock`, {
      params: new HttpParams().appendAll({ 'stock-name': ticker }),
    });
  }

  removeStockByTicker(ticker: string) {
    return this.client.get(`${this.baseUrl}remove`, {
      params: new HttpParams().appendAll({ 'stock-name': ticker }),
    });
  }

  getUser(id: number) {
    return this.client.get(`${this.baseUrl}users/${id}`);
  }

  updateUser(id: number, user: User) {
    return this.client.put(`${this.baseUrl}users/${id}`, user);
  }

  createUser(user: User) {
    return this.client.post(`${this.baseUrl}users`, user);
  }

  setDistributions(distribution: Distribution) {
    return this.client.post(`${this.baseUrl}set-distributions/`, distribution);
  }

  getHistory(ticker: string){
    return this.client.get(`${this.baseUrl}historyStock`, {
      params: new HttpParams().appendAll({ 'stock-name': ticker }),
    });
  }
}

export interface User {
  userId?: number;
  startingAmount: number;
  createdOn?: Date;
  updatedOn?: Date;
  stonks?: Stonk[];
}

export interface Stonk {
  name: string;
  price: number;
  change: number;
  currency: string;
  stockName: string;
  percentage: number;
  userId: number;
  user: User;
  stockHistories: any[];
  amount: number;
}

export interface Distribution {
  stonks: Stonk[];
}
