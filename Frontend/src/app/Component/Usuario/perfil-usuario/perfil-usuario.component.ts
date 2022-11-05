import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NuevoUsuario } from 'src/app/Models/Usuario/nuevo-usuario';
import { TokenService } from 'src/app/Service/Usuario/token.service';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit {

  usuario: NuevoUsuario = new NuevoUsuario();

  nombreUsuario: string | null;

  isAdmin = false;
  isLogged = false;
  roles: string[];

  constructor(private router: ActivatedRoute, private usuarioService: UsuarioService, private tokenService: TokenService) { }

  ngOnInit(): void {

    this.nombreUsuario = this.tokenService.getUserName();
    this.usuarioService.getUsuario(this.nombreUsuario).subscribe(dato => {
      this.usuario = dato;
    });

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

}
