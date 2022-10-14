import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto, Unidad, Categoria } from '../../../Models/Producto/producto';

@Component({
  selector: 'app-registrar-producto',
  templateUrl: './registrar-producto.component.html',
  styleUrls: ['./registrar-producto.component.css']
})
export class RegistrarProductoComponent implements OnInit {

  producto: Producto = new Producto();

  unidades: Unidad[];
  categorias: Categoria[];

  constructor(private servicio: ProductoService, private router: Router) { }

  ngOnInit(): void {
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
