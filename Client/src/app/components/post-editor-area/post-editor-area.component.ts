
import { Component, OnInit} from '@angular/core';

declare var tinymce: any;
@Component({
  selector: 'app-post-editor-area',
  templateUrl: './post-editor-area.component.html',
  styleUrls: ['./post-editor-area.component.css']
})
export class PostEditorAreaComponent implements OnInit {

  public title:String = ``;
  public data:any = []

  constructor() { }

  sendData(e:Event){
    let box = document.getElementById("post-text")!
    let boxChildren = box.childNodes

    boxChildren.forEach( el => {
      let rawData = {
        "tag": el.nodeName,
        "text": el.textContent
      }
      this.data.push(rawData)
      console.log(this.data)
    })
    console.log(this.data);
    
  }

  newLine(e:Event){
    let box = document.getElementById("post-text")!
    let p = document.createElement("p")
    p.setAttribute("contentEditable","True")
    p.setAttribute("style","border: 2px black;caret-color: grey;resize: none;font-size: 1em;")
    p.addEventListener("focus", ()=>{
      p.style.outline = "none"
      p.style.border = "none"
      p.style.boxShadow = "none"
    })
    box.appendChild(p)
    p.focus()
  }

  boldText(){
    let text = "<b>"+this.selectText()+"<b>"
    
  }
  selectText():String{
    let selection:any;
    if (window.getSelection) {
      selection = window.getSelection();
    } else if (selection) {
      selection = selection.createRange();
    }
    return selection.toString()
    
  }

  ngOnInit(): void {
  }

}
