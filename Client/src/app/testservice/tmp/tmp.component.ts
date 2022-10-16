import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/service/image.service';
import { PostsService } from 'src/app/service/posts.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-tmp',
  template: `
      <div class="form-group">
        <label for="file">Choose File</label>
        <input type="file"
              id="image"
              name ="image"
              (change)="handleFileInput($event.target)">
      </div>
  `
})
export class TmpComponent implements OnInit {

  constructor(
    private token: TokenStorageService,
    private user: UserService,
    private post: PostsService,
    private img: ImageService
  ) { }



  ngOnInit(): void {
    console.info(this.token.getToken());

    this.user.login({ username: "angular1", password: "angular" });

    // this.user.profile("villium").subscribe(e=>console.log(e));
    this.user.myProfile().subscribe(e => console.log(e))

    // this.user.register({
    //   username: "angular1",
    //   password: "angular",
    //   email : "angular"
    // }).subscribe(e=>console.log(e))

    // this.user.updateProfile({
    //   old_password : "angular",
    //   email : "angular@sdfgoogle",
    //   imgId : null,
    //   password : "angular"
    // }).subscribe(e=>console.log(e));





    // this.post.getPosts(0,3).subscribe(e=>console.info(e));
    // this.post.getPost(9).subscribe(e=>console.info(e));
    // this.post.addPost({
    //   content:"angular",
    //   imgId: null,
    //   shortContent:"ng",
    //   title:"test"
    // }).subscribe(e=>console.info(e));
    // this.post.remove(9).subscribe();
    // this.post.update(10,{
    //   content: "updateksdjf;ajfakjsfkasjfakjfkasjfakjfklajfkjalskdfj",
    //   title: "abcd"
    // }).subscribe(e=>console.info(e));



    // this.img.remove(23).subscribe(e=>console.log(e))

    this.img.myImage(0,10).subscribe(e=>console.log(e))

  }



  handleFileInput($event: any) {
    let file: File = $event.files[0]
    if(file!=null)
      this.img.toBase64(file).then(f=>{
        this.img.upload(f).subscribe(e => console.info(e))
      })
  }



}
