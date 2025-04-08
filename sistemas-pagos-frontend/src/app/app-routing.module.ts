import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { PagosComponent } from './pagos/pagos.component';
import { EstudiantesComponent } from './estudiantes/estudiantes.component';
import { LoadEstudiantesComponent } from './load-estudiantes/load-estudiantes.component';
import { LoadPagosComponent } from './load-pagos/load-pagos.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import { AuthGuard } from './guards/auth.guard';
import { AuthorizationGuard } from './guards/authorization.guard';



const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "login", component: LoginComponent },
  {
    path: "admin", component: AdminTemplateComponent,
    canActivate:[AuthGuard],
    children: [
      { path: "home", component: HomeComponent },
      { path: "profile", component: ProfileComponent },
      { path: "pagos", component: PagosComponent },
      { path: "estudiantes", component: EstudiantesComponent },
      { path: "loadEstudiantes", component: LoadEstudiantesComponent,
        canActivate: [AuthorizationGuard], data:{roles: ['ADMIN']}
       },
      { path: "loadPagos", component: LoadPagosComponent,
        canActivate: [AuthorizationGuard], data:{roles: ['ADMIN']
       },
      { path: "dashboard", component: DashboardComponent },
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
