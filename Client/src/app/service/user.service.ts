import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Authen, JWTResponse, UserRegister, User, ProfileUpdate } from 'src/models/user.request.model';
import { IUserService } from './IUserService';

@Injectable({
  providedIn: 'root'
})
export class UserService implements IUserService{

  constructor(
    private http : HttpClient
  ) { }
  
  login(auth: Authen): Observable<JWTResponse> {
    throw new Error('Method not implemented.');
  }
  register(userRegister: UserRegister): Observable<User> {
    throw new Error('Method not implemented.');
  }
  showMyProfile(): Observable<User> {
    throw new Error('Method not implemented.');
  }
  showProfile(username: String): Observable<User> {
    throw new Error('Method not implemented.');
  }
  updateMyProfile(updateData: ProfileUpdate): Observable<User> {
    throw new Error('Method not implemented.');
  }
}
