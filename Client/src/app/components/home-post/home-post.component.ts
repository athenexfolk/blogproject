import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-home-post',
  templateUrl: './home-post.component.html',
  styleUrls: ['./home-post.component.css']
})
export class HomePostComponent implements OnInit {

  constructor(
    private titleService:Title,
    private postService:PostService
  ) {
    titleService.setTitle("Home");
  }

  ngOnInit(): void {
  }

}
