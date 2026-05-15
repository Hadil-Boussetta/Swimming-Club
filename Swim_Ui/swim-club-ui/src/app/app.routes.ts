import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SwimmersComponent } from './pages/swimmers/swimmers.component';
import { CoachesComponent } from './pages/coaches/coaches.component';
import { CompetitionsComponent } from './pages/competitions/competitions.component';
import { SessionsComponent } from './pages/sessions/sessions.component';
import { ChatComponent } from './pages/chat/chat.component';
import { PoolMapComponent } from './pages/pool-map/pool-map.component';
import { PoolPrototypeComponent } from './pages/pool-prototype/pool-prototype.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { NewsComponent } from './pages/news/news.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'swimmers', component: SwimmersComponent },
  { path: 'coaches', component: CoachesComponent },
  { path: 'competitions', component: CompetitionsComponent },
  { path: 'sessions', component: SessionsComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'pool-map', component: PoolMapComponent },
  { path: 'pool-prototype', component: PoolPrototypeComponent },
  { path: 'news', component: NewsComponent },
];
