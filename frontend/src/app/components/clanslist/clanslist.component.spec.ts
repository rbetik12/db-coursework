import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClanslistComponent } from './clanslist.component';

describe('ClanslistComponent', () => {
  let component: ClanslistComponent;
  let fixture: ComponentFixture<ClanslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClanslistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClanslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
