import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {
  Distribution,
  FinanceService,
  Stonk,
  User,
} from 'src/services/finance.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public data = {};
  public stonks: any[] = [];
  public distributions: any[] = [];

  userId: number = 1;
  user: User;
  stockName = new FormControl('');
  startingAmount = new FormControl('');
  amount: String = '0';
  public columns = ['price', 'year'];
  public isPortfolioSet: boolean = false;
  public isDistributionSet: boolean = false;
  distributionSaved: boolean = false;
  public graphdata: any[];
  currentData: any[];
  amountData: any[];

  constructor(private financeService: FinanceService) {}

  ngOnInit(): void {
    this.financeService.getUser(this.userId).subscribe({
      next: (d: User) => {
        if (d != null) {
          this.user = d;
          this.amount = d?.startingAmount.toString();
          this.isPortfolioSet = true;
        }
        if (d?.stonks != null && d?.stonks?.length > 0) {
          this.stonks = d.stonks;
          this.distributionSaved = true;
        }
      },
    });
  }

  public createPortolio() {
    this.amount = this.startingAmount.value;
    this.distributionSaved = false;
    this.isPortfolioSet = true;
    if (this.user == null) {
      this.financeService
        .createUser({
          startingAmount: this.startingAmount.value,
        })
        .subscribe({
          next: (a: User) => (this.user = a),
          error: (err) => console.log(err),
        });
    } else {
      this.financeService
        .createUser({
          userId: this.user.userId,
          startingAmount: this.startingAmount.value,
        })
        .subscribe({
          next: (a: User) => this.user,
          error: (err) => console.log(err),
        });
    }
    this.emptyList();
    this.distributionSaved = false;
  }

  public getStonk() {
    let ticker = this.stockName.value;
    if (ticker == '') return;
    this.financeService.getStockByTicker(ticker).subscribe({
      next: (data) => {
        console.log(data);
        console.log(this.stonks);
        this.stonks.push(data);
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });
  }

  public updateSliders() {
    let startingAmountPercentLeft = 100;
    let elems = document.getElementsByClassName('slider');
    let elemsValues = document.getElementsByClassName('input');
    let elemArr = Array.from(elems);
    for (const [i, elem] of elemArr.entries()) {
      while ((elem as any).value > startingAmountPercentLeft) {
        (elem as any).value--;
        if (startingAmountPercentLeft < 0) {
          startingAmountPercentLeft = 0;
          break;
        }
      }
      (elemsValues[i] as any).value = (elems[i] as any).value;
      startingAmountPercentLeft -= (elemsValues[i] as any).value;
    }
  }
  public updateBox() {
    let startingAmountPercentLeft = 100;
    let elems = document.getElementsByClassName('input');
    let elemsValues = document.getElementsByClassName('slider');
    let elemArr = Array.from(elems);
    for (const [i, elem] of elemArr.entries()) {
      while ((elem as any).value > startingAmountPercentLeft) {
        (elem as any).value--;
        if (startingAmountPercentLeft < 0) {
          startingAmountPercentLeft = 0;
          break;
        }
      }
      (elemsValues[i] as any).value = (elems[i] as any).value;
      startingAmountPercentLeft -= (elemsValues[i] as any).value;
    }
  }

  public emptyList() {
    this.stonks = [];
  }

  public removeStonk(event: any) {
    let ticker = event.toString().toUpperCase();
    if (ticker == '') return;
    this.stonks = this.stonks.filter((element) => {
      return element.stockName !== ticker;
    });
  }

  public saveDistributions() {
    if (this.user == null) {
      return;
    }

    let elems = document.getElementsByClassName('slider');
    let elemArr = Array.from(elems);
    let percTotal = elemArr.reduce(
      (partialSum, a) => partialSum + (a as any).value,
      0
    );

    if (percTotal < 100) return;

    let stonks = this.stonks.map((s, i) => {
      return {
        name: s.name,
        price: s.price,
        change: s.change,
        currency: s.currency,
        stockName: s.stockName,
        percentage: (elemArr[i] as any).value,
        user: this.user,
        amount: this.startingAmount.value * (elemArr[i] as any).value / s.price / 100
      } as Stonk;
    });

    this.financeService
      .setDistributions({ stonks: stonks } as Distribution)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.getUser();
        },
        error: (err: HttpErrorResponse) => console.log(err),
      });
  }

  public viewHistory(event: any) {
    /*let ticker = event.toString().toUpperCase();
    if (ticker == '') return;
    this.financeService.getHistory(ticker).subscribe({
      next: (data) => {
        this.data = data;
        console.log(data);
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });*/
    this.getUser();
  }

  public getUser() {
    this.financeService.getUser(1).subscribe({
      next: (data: User) => {
        let stonks = data.stonks;
        this.distributionSaved = true;
        let jointData = [];
        let amountData = [];
        let currentData = [];

        for (let stonk of stonks) {
          let map = stonk.stockHistories.map(p => {return [(new Date(p.date)).toISOString(), p.close]}).sort();
          jointData.push(map);

          let rand = Math.random()*10;
          amountData.push([stonk.name, stonk.percentage]);
          currentData.push([stonk.name, stonk.amount * stonk.price + rand]);
        }
        this.graphdata = jointData;
        this.currentData = currentData;
        this.amountData = amountData;
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });
  }

  public getTicker() {
    this.financeService.getStockByTicker('aapl').subscribe({
      next: (data) => {
        this.data = data;
        console.log(data);
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });
  }
}
