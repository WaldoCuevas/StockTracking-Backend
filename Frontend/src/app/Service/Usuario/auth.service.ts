import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { JWTDTO } from 'src/app/Models/Usuario/jwt-dto';
import { LoginUsuario } from 'src/app/Models/Usuario/login-usuario';
import { NuevoUsuario } from 'src/app/Models/Usuario/nuevo-usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  Url = 'http://localhost:8080/auth/';

  constructor(private httpClient: HttpClient) { }

  nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(`${this.Url + 'nuevo'}`, nuevoUsuario);
  }

  login(loginUsuario: LoginUsuario): Observable<JWTDTO> {
    return this.httpClient.post<JWTDTO>(`${this.Url + 'login'}`, loginUsuario);
  }

  getUsuario(nombreUsuario: String | null): Observable<NuevoUsuario> {
    return this.httpClient.get<NuevoUsuario>(`${this.Url + 'usuario'}/${nombreUsuario}`);
  }

}
