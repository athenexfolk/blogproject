import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PostEditorAreaComponent } from '../../post-editor-area/post-editor-area.component';

@Component({
  selector: 'app-add-text',
  templateUrl: './add-text.component.html',
  styleUrls: ['./add-text.component.css']
})
export class AddTextComponent implements OnInit {

  public unique_key!: number;
  public parentRef!:PostEditorAreaComponent;
  
  remove_me() {
    console.log(this.unique_key)
    this.parentRef.remove(this.unique_key)
  }
  constructor() { }

  ngOnInit(): void {

  }

}
