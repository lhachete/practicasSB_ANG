import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadPagosComponent } from './load-pagos.component';

describe('LoadPagosComponent', () => {
  let component: LoadPagosComponent;
  let fixture: ComponentFixture<LoadPagosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoadPagosComponent]
    });
    fixture = TestBed.createComponent(LoadPagosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
