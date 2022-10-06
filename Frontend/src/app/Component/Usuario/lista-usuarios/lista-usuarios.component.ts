import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/Service/Usuario/user-service.service';
import { Usuario } from '../../../Models/Usuario/usuario';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {

  usuarios: Usuario[];
  userRol: string;

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers() {
    this.userService.getListaUsuarios().subscribe(dato => {
      this.usuarios = dato;
    });
  }

}
