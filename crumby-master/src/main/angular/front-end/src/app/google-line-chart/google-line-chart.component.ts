import { Component, Input, OnInit } from '@angular/core';
import { ChartType, Row } from 'angular-google-charts';
import { FinanceService } from 'src/services/finance.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-google-line-chart',
  templateUrl: './google-line-chart.component.html',
  styleUrls: ['./google-line-chart.component.scss'],
})
export class GoogleLineChartComponent {
  @Input() data : any; 
  @Input() data2 : any;
  @Input() data3 : any;
  @Input() columnNames: string[];
  // @Input() columnNames2: string[];
  // @Input() columnNames3: string[];

  constructor() {}

  ngOnInit(): void {}

  title = 'Portfolio Value over time';
  type = ChartType.LineChart;
  options = {
    };
  width = 1200;
  height = 500;

  //the left pie chart
  title2 = 'Stock % of Starting Funds';
  type2 = ChartType.PieChart;
  options2 = {};
  width2 = 600;
  height2 = 300;

  //the right pie chart
  title3 = 'Stock % of Current Funds';
}
