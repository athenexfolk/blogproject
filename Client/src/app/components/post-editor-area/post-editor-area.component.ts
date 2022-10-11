
import { Component, OnInit, ViewChild, TemplateRef, ViewContainerRef, AfterViewInit, Inject, ComponentFactoryResolver, ComponentRef} from '@angular/core';
import { AddTextComponent } from '../tools/add-text/add-text.component';
@Component({
  selector: 'app-post-editor-area',
  templateUrl: './post-editor-area.component.html',
  styleUrls: ['./post-editor-area.component.css']
})
export class PostEditorAreaComponent implements OnInit {

  public bodyEditor?:HTMLElement

  @ViewChild("mainBody", {static:false, read: ViewContainerRef}) target!:ViewContainerRef;
  private componentRef!: ComponentRef<any>
  private main_body?:ViewContainerRef;

  addText(event:Event){
    console.log("add text");
    let childComponent = this.resolver.resolveComponentFactory(AddTextComponent)
    this.componentRef = this.target.createComponent(childComponent)
  }
  constructor(
    private resolver: ComponentFactoryResolver
  ) { }

  ngOnInit(): void {
    this.bodyEditor = document.getElementById('main-body')!;
  }

  

}
