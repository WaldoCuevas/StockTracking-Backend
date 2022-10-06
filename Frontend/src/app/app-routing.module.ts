import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Import de Usuario
import { ListaUsuariosComponent } from './Component/Usuario/lista-usuarios/lista-usuarios.component';
import { RegistrarUsuarioComponent } from './Component/Usuario/registrar-usuario/registrar-usuario.component';
import { IniciarSesionComponent } from './Component/Usuario/iniciar-sesion/iniciar-sesion/iniciar-sesion.component';

//Import de Producto
import { ListaProductoComponent } from './Component/Producto/lista-producto/lista-producto.component';
import { ModificarProductoComponent } from './Component/Producto/modificar-producto/modificar-producto.component';
import { RegistrarProductoComponent } from './Component/Producto/registrar-producto/registrar-producto.component';

const routes: Routes = [
  //Rutas para usuarios
  { path: 'lista-usuarios', component: ListaUsuariosComponent },
  { path: 'registrar-usuario', component: RegistrarUsuarioComponent },
  { path: 'iniciar-sesion', component: IniciarSesionComponent, },

  //rutas para productos
  { path: 'registrar-producto', component: RegistrarProductoComponent },
  { path: 'lista-productos', component: ListaProductoComponent },
  { path: 'actualizar-producto/:id', component: ModificarProductoComponent },


  { path: '', component: IniciarSesionComponent, pathMatch: "full"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
