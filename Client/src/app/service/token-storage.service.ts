import { Injectable } from '@angular/core';
import { ITokenResDto } from 'src/models/http.dto.respond.interface';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {


  constructor() { /* TODO document why this constructor is empty */  }

  getToken(): ITokenResDto{
    const stringJWT = localStorage.getItem("JWT")!;
    const token = JSON.parse(stringJWT);
    return token;
  }

  saveToken(token:ITokenResDto){
    localStorage.setItem("JWT",JSON.stringify(token));
  }

  clearToken(){
    localStorage.removeItem("JWT");
  }

}
