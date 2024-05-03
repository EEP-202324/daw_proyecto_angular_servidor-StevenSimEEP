import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { HttpClient } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {
  url = 'http://localhost:3000/locations';
  urlPeticion = 'http://localhost:8080/peticiones';

  private universidadActualizadaSubject = new Subject<Universidad>();

  constructor(private http: HttpClient) {}

  updateUniversidad(updatedUniversidad: Universidad): Observable<Universidad> {
    return this.http.put<Universidad>(`${this.url}/${updatedUniversidad.id}`, updatedUniversidad);
  }

  getAllUniversidades() {
    return this.http.get<Universidad[]>(this.url);
  }

  getUniversidadById(id: number) {
    return this.http.get<Universidad>(`${this.url}/${id}`);
  }

  submitApplication(nombre: string, apellido: string, email: string) {
    alert(`Petición de: ${nombre} ${apellido} (${email})`)
  }

  enviarPeticion(peticion: Peticion): Observable<Peticion> {
    alert("petición recibida");
    return this.http.post<Peticion>(this.url, peticion);
  }

  borrarPeticion(): Observable<Peticion> {
    return this.http.delete(`${this.url}/c008`);
  }

  agregarUniversidad(universidadDatos: any): Observable<Universidad> {
    return this.http.post<Universidad>(this.url, universidadDatos);
  }
  getUniversidadActualizadaObservable(): Observable<Universidad> {
    return this.universidadActualizadaSubject.asObservable();
  }

  emitirUniversidadActualizada(universidad: Universidad | undefined) {
    if(universidad !== undefined) {
      this.universidadActualizadaSubject.next(universidad);
    }
  }
  eliminarUniversidad(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}


