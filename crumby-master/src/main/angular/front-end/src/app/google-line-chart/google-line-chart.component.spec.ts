import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleLineChartComponent } from './google-line-chart.component';

describe('GoogleLineChartComponent', () => {
  let component: GoogleLineChartComponent;
  let fixture: ComponentFixture<GoogleLineChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GoogleLineChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleLineChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
