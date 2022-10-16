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
interface IPostsPageResDto {
  content: IPostResDto[]
  pageable: Pageable
  last: boolean
  totalElements: number
  totalPages: number
  size: number
  number: number
  sort: Sort
  first: boolean
  numberOfElements: number
  empty: boolean
}


interface IImagePageResDto {
  content: ImageEntity[]
  pageable: Pageable
  last: boolean
  totalElements: number
  totalPages: number
  size: number
  number: number
  sort: Sort
  first: boolean
  numberOfElements: number
  empty: boolean
}

interface IPostResDto {
  id: number
  username: string
  imgId?: number
  title: string
  shortContent:string 
  create_at: Date
  userEntity: UserEntity
  imageEntity: ImageEntity
}

interface UserEntity {
  username: string
  email: string
  imgId: number
  create_at:Date 
  modified_at:Date 
  imageEntity:number 
}

interface ImageEntity {
  imgId?: number
  username?: string
  name?: string
  alt?: string
  url?: string
}

interface Pageable {
  sort: Sort
  offset: number
  pageNumber: number
  pageSize: number
  unpaged: boolean
  paged: boolean
}

interface Sort {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}



export { IUserResDto, ITokenResDto, IPostsPageResDto, IPostResDto, ImageEntity, IImagePageResDto }