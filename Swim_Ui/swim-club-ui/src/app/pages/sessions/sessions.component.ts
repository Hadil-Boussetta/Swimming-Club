import { Component, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CdkDragDrop, moveItemInArray, transferArrayItem, CdkDrag, CdkDropList } from '@angular/cdk/drag-drop';
import gsap from 'gsap';

export interface Session {
  id: string;
  time: string;
  title: string;
  subtitle: string;
  type: 'blue' | 'purple' | 'emerald' | 'amber' | 'rose' | 'slate';
}

@Component({
  selector: 'app-sessions',
  standalone: true,
  imports: [CommonModule, CdkDropList, CdkDrag],
  templateUrl: './sessions.component.html',
  styleUrl: './sessions.component.css'
})
export class SessionsComponent implements AfterViewInit {
  days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  
  sessionsByDay: { [key: string]: Session[] } = {
    'Monday': [
      { id: 'm1', time: '06:00 - 08:00', title: 'Elite Squad', subtitle: 'Lanes 1-4 • Coach M. Phelps', type: 'blue' },
      { id: 'm2', time: '16:00 - 17:30', title: 'Masters Program', subtitle: 'Lanes 1-6 • Coach S. Jenkins', type: 'purple' }
    ],
    'Tuesday': [
      { id: 't1', time: '08:00 - 09:30', title: 'Aqua Aerobics', subtitle: 'Shallow End • Free', type: 'emerald' }
    ],
    'Wednesday': [
      { id: 'w1', time: '06:00 - 08:00', title: 'Elite Squad', subtitle: 'Lanes 1-4 • Coach M. Phelps', type: 'blue' },
      { id: 'w2', time: '14:00 - 15:00', title: 'Learn to Swim (Kids)', subtitle: 'Teaching Pool • Coach Jenkins', type: 'rose' },
      { id: 'w3', time: '16:00 - 17:30', title: 'Masters Program', subtitle: 'Lanes 1-6 • Coach M. Phelps', type: 'purple' }
    ],
    'Thursday': [
      { id: 'th1', time: '08:00 - 09:30', title: 'Aqua Aerobics', subtitle: 'Shallow End • Free', type: 'emerald' },
      { id: 'th2', time: '11:00 - 15:00', title: 'Maintenance', subtitle: 'Pool Closed', type: 'slate' }
    ],
    'Friday': [
      { id: 'f1', time: '06:00 - 08:00', title: 'Elite Squad', subtitle: 'Lanes 1-4 • Coach M. Phelps', type: 'blue' }
    ],
    'Saturday': [
      { id: 's1', time: '09:00 - 12:00', title: 'Junior Competition', subtitle: 'All Lanes • Regional', type: 'amber' }
    ],
    'Sunday': []
  };

  drop(event: CdkDragDrop<Session[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }

  ngAfterViewInit() {
    gsap.from('.session-card', {
      y: 30,
      opacity: 0,
      duration: 0.5,
      stagger: 0.05,
      ease: 'power2.out'
    });
    
    gsap.from('h1, p, .cal-header > div', {
      y: -10,
      opacity: 0,
      duration: 0.4,
      stagger: 0.05,
      ease: 'power1.out'
    });
  }
}
