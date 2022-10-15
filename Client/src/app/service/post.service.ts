import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { PostContent, PostPagination } from 'src/models/post.request.model';
import { IPostService } from './IPostService';

import { environment } from '../../environments/environment';
import { User } from 'src/models/user.request.model';


@Injectable({
  providedIn: 'root'
})
export class PostService implements IPostService {

  constructor(
    private http : HttpClient
  ) { }


  getAllPost():Observable<PostContent[]>{
    const url = this.getAction(`posts`)
    return this.http.get<PostContent[]>(url)
  }

  getPostThumbnailPagination(page: number, size: number): Observable<PostPagination> {
    const header={
      'Content-Type': 'application/json',
      'Authorization': `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWxsaXVtIiwicm9sZSI6WyJ1c2VyIl0sImlzcyI6Ii9hcGkvbG9naW4iLCJleHAiOjE2NjU4Mzg2MzF9.9TDn2urp7tU0QMtVwWMp9NnxrkLnxrB4KxG6-lF5rTM`
    }

    const url = this.getAction(`posts/${page}/${size}`)
    
    return this.http.get<PostPagination>(url,{headers:header})
  }


  getPostById(postID:string): Observable<PostContent> {
    return this.http.get<PostContent>(
      this.getAction(`post/${postID}`)
    )
  }


  getMyProfile() {

    const header = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWxsaXVtIiwicm9sZSI6WyJ1c2VyIl0sImlzcyI6Ii9hcGkvbG9naW4iLCJleHAiOjE2NjUyNDE1Mzd9._WLquTjvwJoHQgTLxx3IluYlBEwFbi086ygwWQiH8wM`
    }

    const url = this.getAction(`myprofile`)
    
    return this.http.get<User>(url,{headers:header})
  }

  private getAction(action:string):string{
    return `${environment.api_url}/${action}`;
  }





}
