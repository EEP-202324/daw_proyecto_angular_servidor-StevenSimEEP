import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UniversidadService } from '../universidad.service';
import { Universidad } from '../universidad';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Peticion } from '../peticion';

@Component({
  selector: 'app-deatils',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './deatils.component.html',
  styleUrl: './deatils.component.css'
})
export class DeatilsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  universidadService = inject(UniversidadService);
  universidad: Universidad | undefined;

applyForm = new FormGroup({
  nombre: new FormControl(''),
  apellido: new FormControl(''),
  email: new FormControl('')
});

  constructor() {
    const universidadId = Number(this.route.snapshot.params['id']);
    this.universidadService.getUniversidadById(universidadId).subscribe(
      universidad => {
        this.universidad = universidad;
      });
  }
  submitApplication() {
    this.universidadService.submitApplication(
      this.applyForm.value.nombre ?? '',
      this.applyForm.value.apellido ?? '',
      this.applyForm.value.nombre ?? ''
    );

    const peticion: Peticion = {...(this.applyForm.value)};
    this.universidadService.enviarPeticion(peticion);
  }
}
