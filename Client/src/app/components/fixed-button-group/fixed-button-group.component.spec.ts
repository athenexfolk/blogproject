import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FixedButtonGroupComponent } from './fixed-button-group.component';

describe('FixedButtonGroupComponent', () => {
  let component: FixedButtonGroupComponent;
  let fixture: ComponentFixture<FixedButtonGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FixedButtonGroupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FixedButtonGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
