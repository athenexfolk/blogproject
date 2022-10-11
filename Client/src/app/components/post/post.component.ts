import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { Post, PostContent, PostPagination } from 'src/models/post.request.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public postPage!:PostPagination;
  public posts!:Post[];

  constructor(
    private postService:PostService
  ) { }

  ngOnInit(): void {
    this.postService.getPostThumbnailPagination(0,10)
      .subscribe(p=>{
        this.postPage = p
        this.posts = p.content
        console.log(this.posts);
      }
    );
  }
}
