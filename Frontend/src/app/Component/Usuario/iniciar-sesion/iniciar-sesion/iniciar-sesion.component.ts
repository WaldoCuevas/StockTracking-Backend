import { HttpResponse } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { subscribeOn } from 'rxjs';
import { Credenciales } from './../../../../Models/Usuario/credenciales';
import { UserServiceService } from './../../../../Service/Usuario/user-service.service';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.css'],
})
export class IniciarSesionComponent implements OnInit {

  credenciales: Credenciales = new Credenciales();

  constructor( private router: Router, private servicio: UserServiceService) {}

  ngOnInit(): void {
  }

  verificacion() {
    this.servicio.login(this.credenciales).subscribe((dato) => {

      if (dato != null){
        alert('Credenciales verificadas!');
      this.irALaListaDeUsuario();
      } else {
      alert('Credenciales Incorrecta, intente nuevamente!');

    }

    });
  }

  iniciarSesion() {
    this.verificacion();
  }

  irALaListaDeUsuario() {
    this.router.navigate(['lista-usuarios']);
  }
}
