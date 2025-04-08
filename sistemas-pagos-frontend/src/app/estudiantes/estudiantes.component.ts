import { Component, OnInit } from '@angular/core';
import { Estudiante } from '../models/estudiantes.model';
import { EstudiantesService } from '../services/estudiantes.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-estudiantes',
  standalone: false,
  templateUrl: './estudiantes.component.html',
  styleUrl: './estudiantes.component.css'
})
export class EstudiantesComponent implements OnInit{

  estudiantes!: Array<Estudiante>;
  estudiantesDataSource!: MatTableDataSource<Estudiante>;
  displayedColumns: string[] = ['id','nombre','apellido','codigo','programaId','pagos']
  
  constructor(private estudianteService:EstudiantesService ){}

  ngOnInit(): void {
    this.estudianteService.getAllEstudiantes().subscribe({
      next: value => {
        this.estudiantes = value;
        this.estudiantesDataSource = new MatTableDataSource<Estudiante>(this.estudiantes);
      },
      error: err => {
        console.log(err);
      }
    })
  }

}
