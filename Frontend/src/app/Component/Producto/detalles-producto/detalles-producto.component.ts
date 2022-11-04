import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Categoria, Producto, Unidad } from 'src/app/Models/Producto/producto';
import { ProductoService } from 'src/app/Service/Producto/producto.service';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Component({
  selector: 'app-detalles-producto',
  templateUrl: './detalles-producto.component.html',
  styleUrls: ['./detalles-producto.component.css']
})
export class DetallesProductoComponent implements OnInit {

  id:number;
  producto:Producto;
  unidad: Unidad;
  categoria: Categoria;

  isAdmin = false;
  isLogged = false;
  roles: string[];
  
  constructor(private route:ActivatedRoute, 
    private productoService: ProductoService,
    private tokenService:TokenService) { }

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

    this.id = this.route.snapshot.params['id'];
    this.producto = new Producto();
    this.unidad = new Unidad();
    this.categoria = new Categoria();
    this.productoService.obtenerProductoPorId(this.id).subscribe(dato => {
      this.producto = dato;
      this.unidad = dato.unidad;
      this.categoria = dato.categoria;
    });
  }

}
