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




export { ILoginReqDto, IProfileReqDto, IProfileUpdateReqDto, IRegisterReqDto, }