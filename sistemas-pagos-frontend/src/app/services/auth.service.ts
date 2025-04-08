import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public users: any = {
    admin: { password: '1234', roles: ['ESTUDIANTE', 'ADMIN'] },
    user1: { password: '1234', roles: ['ESTUDIANTE'] }
  }

  public username: any;
  public isAuthenticated: boolean = false;
  public roles: string[] = [];

  constructor(private router: Router) { }

  public login(username: string, password: string): boolean {
    if (this.users[username] && this.users[username]['password'] == password) {
      this.isAuthenticated = true;
      this.username = username;
      this.roles = this.users[username]['roles'];
      return true;
    } else {
      return false;
    }
  }

  logout(): void {
    this.isAuthenticated = false;
    this.username = undefined;
    this.roles = [];
    this.router.navigateByUrl("/login");
  }
}
