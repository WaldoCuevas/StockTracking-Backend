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

  constructor(private httpClient: HttpClient) { }

  getListaUsuarios(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(`${this.baseUrl}`);
  }

  registrarUsuario(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, usuario);
  }

  login(credencial:Credenciales): Observable<Credenciales> {
    return this.httpClient.post<Credenciales>(`${this.urlCredenciales}`,credencial);
  }

}
