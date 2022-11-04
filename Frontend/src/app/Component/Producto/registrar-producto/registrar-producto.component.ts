import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto, Unidad, Categoria } from '../../../Models/Producto/producto';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-registrar-producto',
  templateUrl: './registrar-producto.component.html',
  styleUrls: ['./registrar-producto.component.css']
})
export class RegistrarProductoComponent implements OnInit {

  producto: Producto = new Producto();

  unidades: Unidad[];
  categorias: Categoria[];

  isAdmin = false;
  isLogged = false;
  roles: string[];

  constructor(private servicio: ProductoService, private router: Router, private tokenService:TokenService) { }

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

    this.obtenerUnidades();
    this.obtenerCategorias();
  }

  obtenerUnidades() {
    this.servicio.obtenerUnidades().subscribe(dato => {
      this.unidades = dato;
      console.log(this.unidades);
    })
  }

  obtenerCategorias() {
    this.servicio.obtenerCategorias().subscribe(dato => {
      this.categorias = dato;
      console.log(this.categorias);
    })
  }


  guardarProducto() {
    this.servicio.registrarProducto(this.producto).subscribe(dato => {
      console.log(dato);
      this.irALaListaDeProductos();
    });
  }

  irALaListaDeProductos() {
    this.router.navigate(['/lista-productos']);
  }

  onSubmit() {
    this.guardarProducto();
  }

}
