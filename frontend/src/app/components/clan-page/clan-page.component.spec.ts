import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClanPageComponent } from './clan-page.component';

describe('ClanPageComponent', () => {
  let component: ClanPageComponent;
  let fixture: ComponentFixture<ClanPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClanPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClanPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
