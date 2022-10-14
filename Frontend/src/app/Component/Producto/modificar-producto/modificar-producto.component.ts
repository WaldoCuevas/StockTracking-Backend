import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto, Categoria, Unidad } from '../../../Models/Producto/producto';

@Component({
  selector: 'app-modificar-producto',
  templateUrl: './modificar-producto.component.html',
  styleUrls: ['./modificar-producto.component.css']
})
export class ModificarProductoComponent implements OnInit {

  id:number;
  producto:Producto = new Producto();

  unidades: Unidad[];
  categorias: Categoria[];

  constructor(private servicio: ProductoService, private router:Router, private route:ActivatedRoute) { }

  ngOnInit(): void {

    this.obtenerCategorias();
    this.obtenerUnidades();

    this.id = this.route.snapshot.params['id'];
    this.servicio.obtenerProductoPorId(this.id).subscribe(dato =>{
      this.producto = dato;
    });
  }

  irALaListaDeProducto(){
    this.router.navigate(['/lista-productos']);
  }

  onSubmit(){
    this.servicio.actualizarProducto(this.id,this.producto).subscribe(dato=>{
      this.irALaListaDeProducto();
    })
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

}
