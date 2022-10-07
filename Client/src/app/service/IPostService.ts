import { Observable } from "rxjs";
import { PostContent, PostPagination } from "src/models/post.request.model";

export interface IPostService{
    getPostById(postID:string) : Observable<PostContent>
    getPostThumbnailPagination(page:number, size:number) : Observable<PostPagination>
    getMyProfile():any
}