import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Producto, Categoria, Unidad } from '../../Models/Producto/producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  //Esta url obtiene todos los productos en el backend
  private Url = 'http://localhost:8080/api/productos';

  constructor(private httpClient: HttpClient) { }

  //Metodos para las 4 acciones basicas CRUD

  // Guardar un producto (CREATE)
  registrarProducto(producto: Producto): Observable<object> {
    return this.httpClient.post(`${this.Url}`, producto);
  }

  //Obtener un producto (READ)
  obtenerProductoPorId(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(`${this.Url}/${id}`);
  }

  //Actualizar un producto (UPDATE)
  actualizarProducto(id: number, producto: Producto): Observable<Object> {
    return this.httpClient.put(`${this.Url}/${id}`, producto);
  }

  //Eliminar un producto (DELETE)
  eliminarProducto(id: number): Observable<object> {
    return this.httpClient.delete(`${this.Url}/${id}`);
  }

  // Otros metodos necesarios para trabajar

  //Metodo para obtener todos los productos
  obtenerListaDeProductos(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(`${this.Url}`);
  }

  //metodo para obtener un tipo de unidad de un producto (Tabla Unidad)

  private getCategoria = 'http://localhost:8080/api/productos/categorias';

  obtenerCategorias(): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(`${this.getCategoria}`);
  }

  //metodo para obtener una categoria de un producto (Tabla Categoria)

  private getUnidad = 'http://localhost:8080/api/productos/unidades';

  obtenerUnidades(): Observable<Unidad[]> {
    return this.httpClient.get<Unidad[]>(`${this.getUnidad}`);
  }


}
