import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { HttpClient } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {
  url = 'http://localhost:3000/locations';
  urlPeticion = 'http://localhost:8080/peticiones';

  constructor(private http: HttpClient) {}

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
}


