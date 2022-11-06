import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './Service/Usuario/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isLogged = false;
  isAdmin = false;
  roles: string[];

  nombreUsuario: string | null;

  constructor(private router: Router, private tokenService: TokenService) { }

  ngOnInit() {

    this.roles = this.tokenService.getAuthorities();

    if (this.tokenService.getToken()) {
      this.isLogged = true;
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
    this.tokenService.logOut();
  }

}