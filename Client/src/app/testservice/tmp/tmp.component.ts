import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-tmp',
  template : ` `
})
export class TmpComponent  implements OnInit{

  constructor(
    private token: TokenStorageService,
    private user: UserService
  ) {  }



  ngOnInit(): void {
    console.info(this.token.getToken());

    this.user.login({username:"angular1",password:"angular"});

    // this.user.profile("villium").subscribe(e=>console.log(e));
    // this.user.myProfile().subscribe(e=>console.log(e))

    // this.user.register({
    //   username: "angular1",
    //   password: "angular",
    //   email : "angular"
    // }).subscribe(e=>console.log(e))

    this.user.updateProfile({
      old_password : "angular",
      email : "angular@sdfgoogle",
      imgId : null,
      password : "angular"
    }).subscribe(e=>console.log(e));


  }



}
