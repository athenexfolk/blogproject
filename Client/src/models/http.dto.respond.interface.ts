interface IUserResDto {
    username: string;
    email: string;
    imgId?: number;
    create_at: Date;
    modified_at: Date;
    imageEntity?: ImageResDto;
}

interface ImageResDto {
    imgId: number
    username: string
    name: string
    alt: string
    url: string
}

interface ITokenResDto {
    access_token: string
    refresh_token: string
}

export { IUserResDto, ITokenResDto}