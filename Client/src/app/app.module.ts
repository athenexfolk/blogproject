import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { PostEditorAreaComponent } from './components/post-editor-area/post-editor-area.component';
import { BodyComponent } from './components/body/body.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PostComponent } from './components/post/post.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { LoginComponent } from './pages/login/login.component';
import { HomePostComponent } from './components/home-post/home-post.component';
import { RegisterComponent } from './pages/register/register.component';
import { BoldComponent } from './components/tools/bold/bold.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    PostEditorAreaComponent,
    BodyComponent,
    NotificationComponent,
    ProfileComponent,
    PostComponent,
    PaginationComponent,
    LoginComponent,
    HomePostComponent,
    RegisterComponent,
    BoldComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
