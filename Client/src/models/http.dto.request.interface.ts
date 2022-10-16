interface IRegisterReqDto {
    username: string
    password: string
    email?: string
    imgId?: number

}

interface ILoginReqDto {
    username: string
    password: string
}

interface IProfileReqDto {
    username: string
}

interface IProfileUpdateReqDto {
    password: string | null;
    old_password: string;
    email: string | null;
    imgId: number | null;
}

interface IPostAddReqDto {
    imgId: number | null
    title: string
    content: string
    shortContent: any
}

interface IPostUpdateReqDto {
    title: string
    content: string
  }

interface IImageUploadDto{
    image:File,
    alt:string,
    name:string
}




export { ILoginReqDto, IProfileReqDto, IProfileUpdateReqDto, IRegisterReqDto, IPostAddReqDto, IPostUpdateReqDto, IImageUploadDto}