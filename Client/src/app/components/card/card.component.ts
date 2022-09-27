import { Component, Input } from '@angular/core';
import { Post } from 'src/models/post';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent  {

  @Input() post!:Post ;

  constructor() { /* Simple constructor */  }

}
