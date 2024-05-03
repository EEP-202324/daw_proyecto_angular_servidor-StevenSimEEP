import { Component, inject } from '@angular/core';
import { UniversidadComponent } from '../universidad/universidad.component';
import { Universidad } from '../universidad';
import { CommonModule } from '@angular/common';
import { UniversidadService } from '../universidad.service';
import { RouterModule } from '@angular/router';
import { DeatilsComponent } from '../deatils/deatils.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    UniversidadComponent,
    DeatilsComponent
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

    this.universidadService.getUniversidadActualizadaObservable().subscribe(
      updatedUniversidad => {
        if (updatedUniversidad) {
          const index = this.listaUniversidades.findIndex(u => u.id === updatedUniversidad.id);
          if (index !== -1) {
            this.listaUniversidades[index] = updatedUniversidad;
          }
        }
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

  onUniversidadActualizada(updatedUniversidad: Universidad) {
    const index = this.listaUniversidades.findIndex(u => u.id === updatedUniversidad.id);
    if (index !== -1) {
      this.listaUniversidades[index] = updatedUniversidad;
    }
  }

  confirmarEliminar() {
    const nombreIngresado = prompt('Ingresa el nombre de la universidad que deseas eliminar:');
    if (nombreIngresado) {
      const universidadAEliminar = this.listaUniversidades.find(u => u.nombre === nombreIngresado);
      if (universidadAEliminar) {
        const confirmacion = confirm(`¿Estás seguro que deseas eliminar "${nombreIngresado}"?`);
        if (confirmacion) {
          this.eliminarUniversidad(universidadAEliminar.id);
        }
      } else {
        alert(`No se encontró ninguna universidad con el nombre "${nombreIngresado}".`);
      }
    }
  }
  eliminarUniversidad(id: number) {
    this.universidadService.eliminarUniversidad(id).subscribe(
      () => {
        alert('La universidad ha sido eliminada exitosamente.');
        const universidadActualizada: Universidad | undefined = undefined;
        this.universidadService.emitirUniversidadActualizada(universidadActualizada);
      },
      error => {
        console.error('Error al eliminar la universidad:', error);
        alert('Ocurrió un error al eliminar la universidad. Por favor, inténtalo de nuevo más tarde.');
      }
    );
  }

}
