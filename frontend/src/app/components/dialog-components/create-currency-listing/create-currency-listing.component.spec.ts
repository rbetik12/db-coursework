import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCurrencyListingComponent } from './create-currency-listing.component';

describe('CreateCurrencyListingComponent', () => {
  let component: CreateCurrencyListingComponent;
  let fixture: ComponentFixture<CreateCurrencyListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCurrencyListingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCurrencyListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
