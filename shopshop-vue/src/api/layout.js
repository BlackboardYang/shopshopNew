import httpInstance from '@/api/index'

export function getCategoryAPI () {
    return httpInstance({
        url: '/home/category/head'
    })
}