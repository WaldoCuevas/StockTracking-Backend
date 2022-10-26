import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/Service/Usuario/token.service';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService, private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let intReq = request;
    const token = this.tokenService.getToken();
    if (token != null) {
      intReq = request.clone({
        // headers: request.headers.set('Authorization', 'Bearer ' + token)
        headers: request.headers.set('Authorization', `Bearer ${ token }`)
      });
    }
    return next.handle(intReq);
  }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorInterceptor,multi:true}];
 
