import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  guardarUsuario() {
  }

  goToListaUsuarios() {
    this.router.navigate(['/lista-usuarios'])
  }

  onSubmit() {
    this.guardarUsuario();
  }

}
