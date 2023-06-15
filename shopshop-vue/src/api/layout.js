import httpInstance from '@/api/index'

export function getCategoryAPI(level, parentId) {
    return httpInstance.get("/home/category/cat-level", {
        params: {
            level: level,
            parent_id: parentId
        }
    });
}