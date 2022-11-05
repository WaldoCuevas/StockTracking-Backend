import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Productos
import { ListaProductoComponent } from './Component/Producto/lista-producto/lista-producto.component';
import { ModificarProductoComponent } from './Component/Producto/modificar-producto/modificar-producto.component';
import { RegistrarProductoComponent } from './Component/Producto/registrar-producto/registrar-producto.component';
import { DetallesProductoComponent } from './Component/Producto/detalles-producto/detalles-producto.component';

//Usuarios
import { RegistrarUsuarioComponent } from './Component/Usuario/registrar-usuario/registrar-usuario.component';
import { PerfilUsuarioComponent } from './Component/Usuario/perfil-usuario/perfil-usuario.component';
import { IniciarSesionComponent } from './Component/Usuario/iniciar-sesion/iniciar-sesion.component';
import { ListaUsuarioComponent } from './Component/Usuario/lista-usuario/lista-usuario.component';

// Utils
import { GuardService } from './Utils/Guard/guard.service';

const routes: Routes = [

  //Rutas para usuarios

  { path: 'registrar-usuario', component: RegistrarUsuarioComponent },
  { path: 'iniciar-sesion', component: IniciarSesionComponent },
  { path: 'perfil-usuario', component: PerfilUsuarioComponent },
  { path: 'lista-usuarios', component: ListaUsuarioComponent },

  // //Rutas para productos 
  { path: 'registrar-producto', component: RegistrarProductoComponent, canActivate: [GuardService], data: { expectedRol: ['admin'] } },
  { path: 'lista-productos', component: ListaProductoComponent, canActivate: [GuardService], data: { expectedRol: ['admin', 'user'] } },
  { path: 'actualizar-producto/:id', component: ModificarProductoComponent, canActivate: [GuardService], data: { expectedRol: ['admin'] } },
  { path: 'detalles-producto/:id', component: DetallesProductoComponent, canActivate: [GuardService], data: { expectedRol: ['admin', 'user'] } },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
