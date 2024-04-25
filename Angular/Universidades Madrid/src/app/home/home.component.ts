import { Component, inject } from '@angular/core';
import { UniversidadComponent } from '../universidad/universidad.component';
import { Universidad } from '../universidad';
import { CommonModule } from '@angular/common';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    UniversidadComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  listaUniversidades: Universidad[] = [];
  universidadService: UniversidadService = inject(UniversidadService);

  constructor() {
    this.listaUniversidades =
  this.universidadService.getAllUniversidades();
  }
}
