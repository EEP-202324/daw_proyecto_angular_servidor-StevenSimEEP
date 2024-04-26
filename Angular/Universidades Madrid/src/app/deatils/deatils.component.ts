import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UniversidadService } from '../universidad.service';
import { Universidad } from '../universidad';

@Component({
  selector: 'app-deatils',
  standalone: true,
  imports: [],
  templateUrl: './deatils.component.html',
  styleUrl: './deatils.component.css'
})
export class DeatilsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  universidadService = inject(UniversidadService);
universidad: Universidad | undefined;

  constructor() {
    const universidadId =
    Number(this.route.snapshot.params['id']);
    this.universidadService.getUniversidadById(universidadId);
  }
}
