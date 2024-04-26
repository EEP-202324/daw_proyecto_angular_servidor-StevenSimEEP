import { Component, Input } from '@angular/core';
import { Universidad } from '../universidad';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-universidad',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './universidad.component.html',
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  @Input() universidad! : Universidad;
}
