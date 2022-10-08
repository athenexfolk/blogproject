import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { PostContent } from 'src/models/post.request.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public posts:PostContent[] = [];

  constructor(
    private postService:PostService
  ) { }

  ngOnInit(): void {
    this.postService.getAllPost()
      .subscribe(p=>{
        p.forEach(i=>{
          i.create_at = i.create_at.slice(0,-13)
          i.create_at = i.create_at.split('T',2)[0] + " " + i.create_at.split('T',2)[1]
          this.posts.push(i)
        })
      }
    );
  }

}
