import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostEditorAreaComponent } from './post-editor-area.component';

describe('PostEditorAreaComponent', () => {
  let component: PostEditorAreaComponent;
  let fixture: ComponentFixture<PostEditorAreaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostEditorAreaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostEditorAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
