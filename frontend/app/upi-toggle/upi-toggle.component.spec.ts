import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpiToggleComponent } from './upi-toggle.component';

describe('UpiToggleComponent', () => {
  let component: UpiToggleComponent;
  let fixture: ComponentFixture<UpiToggleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpiToggleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpiToggleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
