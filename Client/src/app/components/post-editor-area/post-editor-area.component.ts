
import { Component, OnInit, ViewChild, ViewContainerRef, ComponentFactoryResolver, ComponentRef} from '@angular/core';
import { AddTextComponent } from '../tools/add-text/add-text.component';

declare var tinymce: any;
@Component({
  selector: 'app-post-editor-area',
  templateUrl: './post-editor-area.component.html',
  styleUrls: ['./post-editor-area.component.css']
})
export class PostEditorAreaComponent implements OnInit {

  public bodyEditor?:HTMLElement

  @ViewChild("mainBody", { read: ViewContainerRef }) target!: ViewContainerRef;

  child_unique_key: number = 0;

  componentsReferences = Array<ComponentRef<AddTextComponent>>()

  // @ViewChild("mainBody", {static:false, read: ViewContainerRef}) target!:ViewContainerRef;
  
  // private componentRef!: ComponentRef<any>

  constructor(
    private resolver: ComponentFactoryResolver
  ) { }

  addText(){
    let componentFactory = this.resolver.resolveComponentFactory(AddTextComponent)
    let childComponentRef = this.target.createComponent(componentFactory)
    let childComponent = childComponentRef.instance
    childComponent.unique_key = ++this.child_unique_key
    childComponent.parentRef = this
    this.componentsReferences.push(childComponentRef)
    console.log(this.componentsReferences);
    console.log(this.target);
    
  }

  remove(key: number) {
    if (this.target.length < 1) return;

    let componentRef = this.componentsReferences.filter(
      x => x.instance.unique_key == key
    )[0];
    
    console.log(componentRef.instance);
    

    let vcrIndex: number = this.target.indexOf(componentRef as any);
    
    // removing component from container
    this.target.remove(vcrIndex);

    // removing component from the list
    this.componentsReferences = this.componentsReferences.filter(
      x => x.instance.unique_key !== key
    );
  }

  ngOnInit(): void {
    this.bodyEditor = document.getElementById('main-body')!;
  }

}
