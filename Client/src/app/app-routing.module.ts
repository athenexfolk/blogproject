import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePostComponent } from './components/home-post/home-post.component';
import { NotificationComponent } from './components/notification/notification.component';
import { PostEditorAreaComponent } from './components/post-editor-area/post-editor-area.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';

const routes: Routes = [
  {
    path:"",
    component: HomeComponent,
    children: [
      {
        path: "",
        component: HomePostComponent
      },
      {
        path:"post-editor",
        component: PostEditorAreaComponent
      },
      {
        path:"notification",
        component: NotificationComponent
      },
      {
        path:"profile",
        component: ProfileComponent
      }
    ]
  },
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"register",
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
