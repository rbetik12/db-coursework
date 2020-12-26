import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateClanComponent } from './create-clan.component';

describe('CreateClanComponent', () => {
  let component: CreateClanComponent;
  let fixture: ComponentFixture<CreateClanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateClanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateClanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
