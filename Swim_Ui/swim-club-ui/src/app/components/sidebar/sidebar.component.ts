import { Component, AfterViewInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import gsap from 'gsap';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements AfterViewInit {
  ngAfterViewInit() {
    gsap.from('a', {
      x: -20,
      opacity: 0,
      duration: 0.4,
      stagger: 0.05,
      ease: 'power2.out'
    });
    
    gsap.from('.opacity-0', { // Target any nested UI like the help box
      opacity: 0,
      y: 10,
      delay: 0.4,
      duration: 0.5
    });
  }
}
