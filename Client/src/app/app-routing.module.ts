import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotificationComponent } from './components/notification/notification.component';
import { PostEditorAreaComponent } from './components/post-editor-area/post-editor-area.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './pages/home/home.component';
import { TmpComponent } from './testservice/tmp/tmp.component';

const routes: Routes = [
  {path:"", component: HomeComponent},
  {path:"post-editor", component: PostEditorAreaComponent},
  {path:"notification", component: NotificationComponent},
  {path:"profile",component: ProfileComponent},
  {path:"testService",component:TmpComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
