import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IImageUploadDto } from 'src/models/http.dto.request.interface';
import { IImagePageResDto, ImageEntity } from 'src/models/http.dto.respond.interface';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private api_url:string = environment.api_url;

  constructor(
    private http:HttpClient,
    private token:TokenStorageService
  ) { }


  upload(file:any){
    return this.http.post<any>(`${this.api_url}/upload/base64`,file,{
      headers:this.header()
    })
  }

  remove(id:number){
    return this.http.delete(`${this.api_url}/delete/image/${id}`,{
      headers:this.header()
    })
  }

  myImage(page:number, size:number){
    return this.http.get<IImagePageResDto>(`${this.api_url}/image/${page}/${size}`,{headers:this.header()})
  }


  private header(){
    return{
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token.getToken().access_token}`
    }
  }

  public toBase64(file: File) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }

}
