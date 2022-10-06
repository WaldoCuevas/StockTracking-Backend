import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Models/Usuario/usuario';
import { UserServiceService } from 'src/app/Service/Usuario/user-service.service';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  usuario: Usuario = new Usuario();

  constructor(private usuarioServicio: UserServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  guardarUsuario() {
    this.usuarioServicio.registrarUsuario(this.usuario).subscribe(dato => {
      console.log(dato);
      this.goToListaUsuarios();
    });
  }

  goToListaUsuarios() {
    this.router.navigate(['/lista-usuarios'])
  }

  onSubmit() {
    this.guardarUsuario();
  }

}
