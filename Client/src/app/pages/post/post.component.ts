import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { Post } from 'src/models/post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  content!:Post;

  constructor(
    private route:ActivatedRoute,
    private router:Router,
    private postService:PostService,
    private titleService:Title
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      param=>{

        let postID:number = +param.get("id")!;

        // ถ้า id ไม่ใช้ตัวเลข ให้กลับไปที่ localhost:4200/
        if(isNaN(postID)){
          this.router.navigate([''])
        }else{
          this.getContent(postID)
        }
        
      }
    )
  }

  private getContent(postID:number){
    this.postService.getContent(postID).subscribe(
      content=>{
        this.content = content;
        this.titleService.setTitle(content.title);
      }
    )
  }

}
