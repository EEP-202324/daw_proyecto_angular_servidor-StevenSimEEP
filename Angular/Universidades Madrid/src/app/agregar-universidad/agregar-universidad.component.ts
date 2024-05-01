import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-agregar-universidad',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './agregar-universidad.component.html',
  styleUrl: './agregar-universidad.component.css'
})
export class AgregarUniversidadComponent {
  FormUniversidad = new FormGroup({
    id: new FormControl('',[Validators.required, Validators.pattern('^[0-9]+$')]),
    nombre: new FormControl(''),
    ubicacion: new FormControl(''),
    estado: new FormControl(''),
    photo: new FormControl(''),
    disponibilidad: new FormControl('')
  });

  constructor(private universidadService:UniversidadService) {}

  agregar() {
    const universidadDatos = this.FormUniversidad.value;
    this.universidadService.agregarUniversidad(universidadDatos).subscribe({
      next: nuevaUniversidad => {
        console.log('Nueva universidad agregada', nuevaUniversidad);
        alert('Universidad agregada exitosamente');
      },
      error: error => {
        console.log('Error al agregar', error);
        alert('No se ha podido agregar');
      }
    })
  }
}
