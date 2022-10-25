import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Credenciales } from './../../../../Models/Usuario/credenciales';
import { UserServiceService } from './../../../../Service/Usuario/user-service.service';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.css'],
})
export class IniciarSesionComponent implements OnInit {

  credenciales: Credenciales = new Credenciales();
  token:string;

  constructor(private router: Router, private servicio: UserServiceService) { }

  ngOnInit(): void {
  }

  verificacion() {

    //Verificar login

    this.servicio.verificarCredenciales(this.credenciales).subscribe((dato) => {
      if (dato != null) {
        alert('Credenciales verificadas!');
        this.credenciales.id = dato.id;
        this.credenciales.email = dato.email;
        this.credenciales.password = dato.password;

        this.irAlPerfil(this.credenciales.id);
      } else {
        alert('Credenciales Incorrecta, intente nuevamente!');
      }
    });

    //Crear sesion con ese login

    this.servicio.login(this.credenciales).subscribe((dato) => {

      localStorage.setItem("token", dato)

    });

  }

  iniciarSesion() {
    this.verificacion();
  }

  irAlPerfil(id: any) {
    this.router.navigate(['perfil-usuario', id]);
  }

}
