
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Register
interface UserRegister {
    username: string
    password: string
    email: string
  }
  
interface User {
    username: string
    email: string
    create_at: string
  }
  
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Login
interface Authen{
    username:string,
    password:string
}

interface JWTRespose {
    access_token: string
    refresh_token: string
  }
  
  
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Update profile
interface ProfileUpdate {
    email: string
    password: string
    confirmPassword: string
  }
  

export {
    UserRegister,
    User,
    Authen,
    JWTRespose,
    ProfileUpdate
}