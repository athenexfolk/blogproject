import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { FixedButtonGroupComponent } from './components/fixed-button-group/fixed-button-group.component';
import { PostEditorAreaComponent } from './components/post-editor-area/post-editor-area.component';
import { BodyComponent } from './components/body/body.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PostComponent } from './components/post/post.component';
import { AddTextComponent } from './components/tools/add-text/add-text.component';
import { TmpComponent } from './testservice/tmp/tmp.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    FixedButtonGroupComponent,
    PostEditorAreaComponent,
    BodyComponent,
    NotificationComponent,
    ProfileComponent,
    PostComponent,
    AddTextComponent,
    TmpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
