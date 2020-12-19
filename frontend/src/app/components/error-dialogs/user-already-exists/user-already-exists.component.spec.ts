import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAlreadyExistsComponent } from './user-already-exists.component';

describe('UserAlreadyExistsComponent', () => {
  let component: UserAlreadyExistsComponent;
  let fixture: ComponentFixture<UserAlreadyExistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAlreadyExistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAlreadyExistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
