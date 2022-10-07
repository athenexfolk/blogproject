// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// POST Thumbnail
export interface Post {
    id: number;
    title: string;
    create_at: Date;
    user_id: string;
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// POST Thumbnail Pagination
export interface PostPagination {
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
export interface PostContent {
    id: number
    title: string
    content: string
    create_at: string
    modified_at: string
    user_id: string
}

