import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Producto } from '../../Models/Producto/producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  //Esta url obtiene todos los productos en el backend
  private Url = 'http://localhost:8080/api/productos';

  constructor(private httpClient: HttpClient) {}

  //Metodo para obtener todos los productos
  obtenerListaDeProductos(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(`${this.Url}`);
  }

  //Este metodo sirve para registrar un producto
  registrarProducto(producto: Producto): Observable<object> {
    return this.httpClient.post(`${this.Url}`, producto);
  }

  actualizarProducto(id: number, producto: Producto): Observable<Object> {
    return this.httpClient.put(`${this.Url}/${id}`, producto);
  }

  obtenerProductoPorId(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(`${this.Url}/${id}`);
  }

  eliminarProducto(id: number): Observable<object> {
    return this.httpClient.delete(`${this.Url}/${id}`);
  }

}
