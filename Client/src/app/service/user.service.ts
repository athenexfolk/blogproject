import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ILoginReqDto, IProfileUpdateReqDto, IRegisterReqDto } from 'src/models/http.dto.request.interface';
import { ITokenResDto, IUserResDto } from 'src/models/http.dto.respond.interface';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {



  constructor(
    private token:TokenStorageService,
    private http: HttpClient
  ) { }

  login(user : ILoginReqDto){
    this.http.post<ITokenResDto>(this.action("login"),user).subscribe({
      next: token =>{this.token.saveToken(token)},
      error: err =>{console.error(err)},
      complete: ()=>{console.log("Login compleate");
      }
    });
  }

  register(info:IRegisterReqDto){
    return this.http.post<IUserResDto>(this.action("register"),info);
  }

  profile(username : string){
    return this.http.get<IUserResDto>(this.action(`user/${username}`))
  }

  myProfile(){
    return this.http.get<IUserResDto>(this.action("myprofile"),{
      headers:this.header()
    });
  }

  updateProfile(info:IProfileUpdateReqDto){
    return this.http.put<IUserResDto>(this.action("myprofile"),info,{headers:this.header()});
  }




  // Utill

  private action(action:string):string{
    return `${environment.api_url}/${action}`;
  }

  private header(){
    return{
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token.getToken().access_token}`
    }
  }
}
