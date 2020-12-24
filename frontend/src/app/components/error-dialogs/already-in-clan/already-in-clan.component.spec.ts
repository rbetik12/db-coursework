import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlreadyInClanComponent } from './already-in-clan.component';

describe('AlreadyInClanComponent', () => {
  let component: AlreadyInClanComponent;
  let fixture: ComponentFixture<AlreadyInClanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlreadyInClanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlreadyInClanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
