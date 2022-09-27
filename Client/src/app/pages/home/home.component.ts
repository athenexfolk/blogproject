import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';
import { Post } from 'src/models/post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public posts!:Post[];

  constructor(
    private $post:PostService
  ) { }

  ngOnInit(): void {

    this.$post.getAll().subscribe(
      posts=>{
        this.posts = posts;
      }
    )

  }

}
