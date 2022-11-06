import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto } from '../../../Models/Producto/producto';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {

  productos: Producto[];

  roles:string[];
  isAdmin = false;
  isLogged = false;

  constructor(
    private productoServicio: ProductoService, private router: Router, private tokenService:TokenService) { }

  ngOnInit(): void {

    this.roles = this.tokenService.getAuthorities();

    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.roles.forEach(rol => {
        if (rol === 'ROLE_ADMIN') {
          this.isAdmin = true;
          
        }
      })

    }

    this.obtenerProductos();
    
    
  }

  actualizarProducto(id:number){
    this.router.navigate(['actualizar-producto', id]);
  }

  private obtenerProductos(){
    this.productoServicio.obtenerListaDeProductos().subscribe(dato=> {
      this.productos = dato;
    });

  }

  eliminarProducto(id:number){
    this.productoServicio.eliminarProducto(id).subscribe(dato=>{
      console.log(dato);
      this.obtenerProductos();
    })
  }

  verDetallesProducto(id:number) {
    this.router.navigate(['detalles-producto', id]);
  }

}
