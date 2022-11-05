import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Utils
import { InterceptorService } from './Utils/Interceptor/interceptor.service';
import { FormsModule } from '@angular/forms';

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


@NgModule({
  declarations: [
    AppComponent,
    ListaUsuarioComponent,
    RegistrarUsuarioComponent,
    ListaProductoComponent,
    ModificarProductoComponent,
    RegistrarProductoComponent,
    IniciarSesionComponent,
    DetallesProductoComponent,
    PerfilUsuarioComponent,
    ListaUsuarioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [InterceptorService ],
  bootstrap: [AppComponent]
}) 

export class AppModule { }


