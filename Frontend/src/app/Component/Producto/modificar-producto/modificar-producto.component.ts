import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../../../Service/Producto/producto.service';
import { Producto } from '../../../Models/Producto/producto';

@Component({
  selector: 'app-modificar-producto',
  templateUrl: './modificar-producto.component.html',
  styleUrls: ['./modificar-producto.component.css']
})
export class ModificarProductoComponent implements OnInit {

  id:number;
  producto:Producto = new Producto();

  constructor(private servicio: ProductoService, private router:Router, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.servicio.obtenerProductoPorId(this.id).subscribe(dato =>{
      this.producto = dato;
    });
  }

  irALaListaDeProducto(){
    this.router.navigate(['/productos']);
  }

  onSubmit(){
    this.servicio.actualizarProducto(this.id,this.producto).subscribe(dato=>{
      this.irALaListaDeProducto();
    })
  }

}
