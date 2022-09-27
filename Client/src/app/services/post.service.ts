import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from 'src/models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = 'http://localhost:8080/api'

  constructor(
    private http:HttpClient
  ) { }

  public getAll(){
    return this.http.get<Post[]>(`${this.url}/posts`);
  }

  public getContent(id:number){
    return this.http.get<Post>(`${this.url}/post/${id}`);
  }

}
