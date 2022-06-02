import { HttpClientModule } from '@angular/common/http';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { GoogleChartsModule } from 'angular-google-charts';
import { ServicesModule } from 'src/services/services.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GoogleLineChartComponent } from './google-line-chart/google-line-chart.component';

@NgModule({
  declarations: [AppComponent, GoogleLineChartComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ServicesModule,
    HttpClientModule,
    ReactiveFormsModule,
    GoogleChartsModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent],
  
})
export class AppModule {}
