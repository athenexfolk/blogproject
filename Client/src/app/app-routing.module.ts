import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostEditorAreaComponent } from './components/post-editor-area/post-editor-area.component';
import { HomeComponent } from './pages/home/home.component';

const routes: Routes = [
  {path:"", component: HomeComponent},
  {path:"post-editor", component: PostEditorAreaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
