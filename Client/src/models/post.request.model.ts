// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// POST Thumbnail
interface Post {
    id: number;
    title: string;
    create_at: Date;
    user_id: string;
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// POST Thumbnail Pagination
interface PostPagination {
    content: Post[]
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

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// PostContent
interface PostContent {
    id: number
    title: string
    content: string
    create_at: Date
    modified_at: Date
    user_id: string
}


export {
    Post,
    PostPagination,
    Pageable,
    PostContent
}