import { Observable } from "rxjs";
import { Authen, JWTResponse, UserRegister, User, ProfileUpdate} from "src/models/user.request.model"

export interface IUserService{
    login(auth:Authen) : Observable<JWTResponse>
    register(userRegister:UserRegister) : Observable<User>
    showMyProfile() : Observable<User>
    showProfile(username:String) : Observable<User>
    updateMyProfile(updateData:ProfileUpdate) : Observable<User>
}