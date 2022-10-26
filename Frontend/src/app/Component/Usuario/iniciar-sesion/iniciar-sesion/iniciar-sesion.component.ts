import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { JWTDTO } from 'src/app/Models/Usuario/jwt-dto';
import { LoginUsuario } from 'src/app/Models/Usuario/login-usuario';
import { AuthService } from 'src/app/Service/Usuario/auth.service';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.css'],
})
export class IniciarSesionComponent implements OnInit {

  isLogged = false;
  isLoginFalse = false;
  loginUsuario: LoginUsuario;

  nombreUsuario: string;
  password: string

  errMsj:string;

  roles: string[] = [];

  constructor(private router: Router, private tokenService: TokenService, private authService: AuthService) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFalse = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  verificacion() {

  }

  //onLogin
  iniciarSesion() {
    this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);
    this.authService.login(this.loginUsuario).subscribe({
      next: (data) => {
        this.isLogged = true;
        this.isLoginFalse = false;

        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.nombreUsuario);
        this.tokenService.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/lista-productos']);
      }, error: err => {
        this.isLogged = false;
        this.isLoginFalse = true;
        this.errMsj = err.error.mensaje;
        console.log(this.errMsj);
        console.log(this.nombreUsuario);
        console.log(this.password);
      }
    });
  }


  irAlPerfil(id: any) {
    this.router.navigate(['perfil-usuario', id]);
  }

}
