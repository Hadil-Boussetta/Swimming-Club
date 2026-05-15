import { Component, AfterViewInit } from '@angular/core';
import gsap from 'gsap';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements AfterViewInit {
  ngAfterViewInit() {
    // Header texts
    gsap.from('h1, p', {
      y: -20,
      opacity: 0,
      duration: 0.5,
      stagger: 0.1,
      ease: 'power2.out'
    });

    // Top buttons/components
    gsap.from('button, select', {
      x: 20,
      opacity: 0,
      duration: 0.5,
      delay: 0.2,
      stagger: 0.1,
      ease: 'power2.out'
    });

    // Stagger fade-up animation for all metric cards and main grids
    gsap.from('.glass', {
      y: 40,
      opacity: 0,
      duration: 0.6,
      stagger: 0.1,
      ease: 'power3.out',
      delay: 0.1
    });
    
    // CSS bar chart inner bars
    gsap.from('.group > div > div', {
      scaleY: 0,
      transformOrigin: "bottom center",
      opacity: 0,
      duration: 0.8,
      stagger: 0.1,
      ease: 'back.out(1.7)',
      delay: 0.4
    });
  }
}
