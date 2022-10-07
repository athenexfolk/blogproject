import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Post } from 'src/models/post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public posts!:Post[];

  constructor(
    private titleService:Title
  ) {
    titleService.setTitle("Home");
  }

  ngOnInit(): void {


  }

}
