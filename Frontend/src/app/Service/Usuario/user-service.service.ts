import { Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../Models/Usuario/usuario';
import { Credenciales } from 'src/app/Models/Usuario/credenciales';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private baseUrl = "http://localhost:8080/api/usuarios";

  private urlCredenciales = "http://localhost:8080/api/credenciales";

  private obtenerToken = "http://localhost:8080/api/token";

  constructor(private httpClient: HttpClient) { }

  getUserById(id:number): Observable<Usuario> {
    return this.httpClient.get<Usuario>(`${this.baseUrl}/${id}`);
  }

  getListaUsuarios(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(`${this.baseUrl}`);
  }

  registrarUsuario(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, usuario);
  }

  //Verificaciones y Login

  verificarCredenciales(credencial:Credenciales): Observable<Credenciales> {
    return this.httpClient.post<Credenciales>(`${this.urlCredenciales}`,credencial);
  }

  login(credencial:Credenciales): Observable<any> {
    return this.httpClient.post(`${this.obtenerToken}`,credencial, { responseType: 'text'});
  }

}
