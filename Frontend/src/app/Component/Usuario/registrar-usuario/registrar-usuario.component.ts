import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { NuevoUsuario } from 'src/app/Models/Usuario/nuevo-usuario';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  usuario: NuevoUsuario = new NuevoUsuario();

  isAdmin = false;
  isLogged = false;
  roles: string[];

  constructor(private tokenService: TokenService,
    private usuarioService: UsuarioService,
    private router: Router) { }

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

  guardarUsuario() {
    this.usuarioService.nuevo(this.usuario).subscribe(
      {
        next: (data) => {
          alert('Cuenta creada xd');
          console.log(data);
          this.goToListaUsuarios();

        }, error: (err) => {
          console.log('Error al crear la cuenta', err)
        }
      });
  }

  goToListaUsuarios() {
    this.router.navigate(['/iniciar-sesion'])
  }

  onSubmit() {
    this.guardarUsuario();
  }

}
