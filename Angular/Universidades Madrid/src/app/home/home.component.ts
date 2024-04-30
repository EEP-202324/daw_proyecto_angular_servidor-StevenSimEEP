import { Component, inject } from '@angular/core';
import { UniversidadComponent } from '../universidad/universidad.component';
import { Universidad } from '../universidad';
import { CommonModule } from '@angular/common';
import { UniversidadService } from '../universidad.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    UniversidadComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  listaUniversidades: Universidad[] = [];
  universidadService: UniversidadService = inject(UniversidadService);
  listaUniversidadesFiltrada: Universidad[] = [];

  constructor() {
    this.universidadService.getAllUniversidades().subscribe(
      universidades => {
        this.listaUniversidades = universidades;
        this.listaUniversidadesFiltrada = universidades;
      }
    );
   }

  filtrar(text: string) {
    if (!text) {
      this.listaUniversidadesFiltrada = this.listaUniversidades;
    } else {
      this.listaUniversidadesFiltrada = this.listaUniversidades.filter(
        universidad => universidad?.nombre.toLowerCase().includes(text.toLowerCase())
      );
    }
  }
}
