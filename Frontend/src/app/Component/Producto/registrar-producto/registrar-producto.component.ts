import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto } from '../../../Models/Producto/producto';


@Component({
  selector: 'app-registrar-producto',
  templateUrl: './registrar-producto.component.html',
  styleUrls: ['./registrar-producto.component.css']
})
export class RegistrarProductoComponent implements OnInit {

  producto: Producto = new Producto();

  constructor(private servicio: ProductoService, private router: Router) { }

  ngOnInit(): void {
  }

  guardarProducto(){
    this.servicio.registrarProducto(this.producto).subscribe(dato=>{
      console.log(dato);
      this.irALaListaDeProductos();
    },);
  }

  irALaListaDeProductos(){
    this.router.navigate(['/lista-productos']);
  }

  onSubmit(){
    this.guardarProducto();
  }

}
