import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadEstudiantesComponent } from './load-estudiantes.component';

describe('LoadEstudiantesComponent', () => {
  let component: LoadEstudiantesComponent;
  let fixture: ComponentFixture<LoadEstudiantesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoadEstudiantesComponent]
    });
    fixture = TestBed.createComponent(LoadEstudiantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
