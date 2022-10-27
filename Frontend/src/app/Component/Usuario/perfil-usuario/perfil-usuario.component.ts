import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NuevoUsuario } from 'src/app/Models/Usuario/nuevo-usuario';
import { AuthService } from 'src/app/Service/Usuario/auth.service';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit {

  usuario:NuevoUsuario;
  nombreUsuario:string | null;
  
  constructor(private router: ActivatedRoute, private authService:AuthService, private tokenService:TokenService) { }

  ngOnInit(): void {
    this.nombreUsuario = this.tokenService.getUserName();
    this.authService.getUsuario(this.nombreUsuario).subscribe( dato => {
      this.usuario = dato;
    });
  }


}
