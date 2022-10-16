import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IPostAddReqDto, IPostUpdateReqDto } from 'src/models/http.dto.request.interface';
import { IPostResDto, IPostsPageResDto } from 'src/models/http.dto.respond.interface';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  private api_url :string = environment.api_url;

  constructor(
    private http:HttpClient,
    private token: TokenStorageService
  ) { }

  getPosts(page:number, size:number){
    return this.http.get<IPostsPageResDto>(`${this.api_url}/posts/${page}/${size}`);
  }

  getPost(id:number){
    return this.http.get<IPostResDto>(`${this.api_url}/post/${id}`);
  }

  addPost(post:IPostAddReqDto){
    return this.http.post<IPostResDto>(`${this.api_url}/post`,post,{
      headers:this.header()
    });
  }

  remove(id:number){
    return this.http.delete(`${this.api_url}/post/${id}`,{
      headers:this.header()
    });
  }

  update(id:number, post:IPostUpdateReqDto){
    return this.http.put<IPostResDto>(`${this.api_url}/post/${id}`,post,{
      headers:this.header()
    })
  }


  private header(){
    return{
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token.getToken().access_token}`
    }
  }
}
