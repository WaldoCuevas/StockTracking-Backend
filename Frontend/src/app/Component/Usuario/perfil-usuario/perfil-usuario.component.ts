import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/Models/Usuario/usuario';
import { UserServiceService } from 'src/app/Service/Usuario/user-service.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit {

  id:number;
  usuario:Usuario;
  constructor(private router: ActivatedRoute, private servicio: UserServiceService) { }

  ngOnInit(): void {
    this.id = this.router.snapshot.params['id'];
    this.usuario = new Usuario();

    this.servicio.getUserById(this.id).subscribe(dato => {
      this.usuario = dato;
      console.log(this.usuario);
    });

  }

}
