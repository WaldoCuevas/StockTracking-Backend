import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './Service/Usuario/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = "stockTracking"

  nombreUsuario: string | null;

  isAdmin = false;
  isLogged = false;
  roles: string[];

  constructor(private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {

    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.roles = this.tokenService.getAuthorities();
      this.roles.forEach(rol => {
        if (rol === 'ROLE_ADMIN') {
          this.isAdmin = true;
        }
      })

    }

  }

  public getUserName(nombreUsuario: string | null): void {
    this.nombreUsuario = this.tokenService.getUserName();
    console.log(this.nombreUsuario);
    this.router.navigate(['perfil-usuario', this.nombreUsuario]);
  }

  cerrarSesion(): void {
    this.isLogged = false;
    this.isAdmin = false;
    this.tokenService.logOut();
    this.router.navigate(['iniciar-sesion'])
  }

}