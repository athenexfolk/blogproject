// Register
interface UserRegister {
    username: string
    password: string
    email: string
}

 //User 
interface User {
    username: string
    email: string
    create_at: string
}
  
// Login
interface Authen{
    username:string,
    password:string
}

// JWT
interface JWTResponse {
    access_token: string
    refresh_token: string
}
  
// Update profile
interface ProfileUpdate {
    email: string
    password: string
    old_password: string
}
  
export {
    UserRegister,
    User,
    Authen,
    JWTResponse,
    ProfileUpdate
}