import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto } from '../../../Models/Producto/producto';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {

  productos: Producto[];

  constructor(private productoServicio: ProductoService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerProductos();
  }

  actualizarProducto(id:number){
    this.router.navigate(['actualizar-producto',id]);
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

}
