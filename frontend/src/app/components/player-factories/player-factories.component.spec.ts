import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerFactoriesComponent } from './player-factories.component';

describe('PlayerFactoriesComponent', () => {
  let component: PlayerFactoriesComponent;
  let fixture: ComponentFixture<PlayerFactoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlayerFactoriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerFactoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
