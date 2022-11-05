import { Component, OnInit } from '@angular/core';
import { NuevoUsuario } from 'src/app/Models/Usuario/nuevo-usuario';
import { TokenService } from 'src/app/Service/Usuario/token.service';
import { UsuarioService } from 'src/app/Service/Usuario/usuario.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css']
})
export class ListaUsuarioComponent implements OnInit {

  usuarios:NuevoUsuario[];
  isAdmin = false;

  constructor(private tokenService:TokenService, private usuarioService:UsuarioService ) { }

  ngOnInit(): void {

    if (this.tokenService.getToken()) {
      this.isAdmin = true;
      this.getUsers();
    }

  }

  private getUsers() {
    this.usuarioService.obtenerListaDeUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

}
