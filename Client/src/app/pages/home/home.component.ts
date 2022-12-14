import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { PostService } from 'src/app/service/post.service';
import { Post, PostContent } from 'src/models/post.request.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  

  constructor(
    private titleService:Title,
    private postService:PostService
  ) {
    titleService.setTitle("Home");
  }

  ngOnInit(): void {

    // this.postService.getPostThumbnailPagination(0,10)
    //   .subscribe(i=>{console.log(i);})


    // this.postService.getMyProfile()
    //   .subscribe(i=>{console.log(i);})
  }

}
