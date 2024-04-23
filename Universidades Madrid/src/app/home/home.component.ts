import { Component } from '@angular/core';
import { UniversidadComponent } from '../universidad/universidad.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    UniversidadComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
