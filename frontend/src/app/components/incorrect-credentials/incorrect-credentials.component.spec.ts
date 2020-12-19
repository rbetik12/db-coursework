import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncorrectCredentialsComponent } from './incorrect-credentials.component';

describe('IncorrectCredentialsComponent', () => {
  let component: IncorrectCredentialsComponent;
  let fixture: ComponentFixture<IncorrectCredentialsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IncorrectCredentialsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IncorrectCredentialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
