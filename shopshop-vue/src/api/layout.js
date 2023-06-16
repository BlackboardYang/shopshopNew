import httpInstance from '@/api/index'

export function getCategoryAPI () {
    return httpInstance({
        url: '/home/category/cat-level'
    })
}


// export function getCategoryAPI() {
//     return httpInstance("/home/category/cat-level", {
//         params: {
//             level: level,
//             parent_id: parentId
//         }
//     });
// }