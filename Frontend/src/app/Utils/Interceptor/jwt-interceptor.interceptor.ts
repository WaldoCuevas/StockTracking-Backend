import { Injectable } from '@angular/core';
<<<<<<< Updated upstream:Frontend/src/app/Utils/Interceptor/jwt-interceptor.interceptor.ts
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
=======
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Router } from '@angular/router';
>>>>>>> Stashed changes:Frontend/src/app/Utils/Interceptor/interceptor.service.ts
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
<<<<<<< Updated upstream:Frontend/src/app/Utils/Interceptor/jwt-interceptor.interceptor.ts
        // headers: request.headers.set('Authorization', 'Bearer ' + token)
        headers: request.headers.set('Authorization', `Bearer ${ token }`)
=======
        //headers: request.headers.set('Authorization', 'Bearer ' + token)
        headers: request.headers.set('Authorization', `Bearer ${token}`)
>>>>>>> Stashed changes:Frontend/src/app/Utils/Interceptor/interceptor.service.ts
      });
    }
    return next.handle(intReq);
  }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorInterceptor,multi:true}];
 
