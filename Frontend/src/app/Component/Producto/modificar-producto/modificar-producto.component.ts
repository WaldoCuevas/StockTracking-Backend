import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria, Producto, Unidad } from 'src/app/Models/Producto/producto';
import { ProductoService } from 'src/app/Service/Producto/producto.service';
import { TokenService } from 'src/app/Service/Usuario/token.service';

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

  isAdmin = false;
  isLogged = false;
  roles: string[];

  constructor(private servicio: ProductoService, private router:Router, private route:ActivatedRoute, private tokenService:TokenService) { }

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
