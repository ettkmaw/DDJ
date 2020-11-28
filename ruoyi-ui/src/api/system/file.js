import request from '@/utils/request'

// 查询minio文件列表
export function listFile(query) {
  return request({
    url: '/system/file/list',
    method: 'get',
    params: query
  })
}

// 查询minio文件详细
export function getFile(fileId) {
  return request({
    url: '/system/file/' + fileId,
    method: 'get'
  })
}

// 新增minio文件
export function addFile(data) {
  return request({
    url: '/system/file',
    method: 'post',
    data: data
  })
}

// 修改minio文件
export function updateFile(data) {
  return request({
    url: '/system/file',
    method: 'put',
    data: data
  })
}

// 删除minio文件
export function delFile(fileId) {
  return request({
    url: '/system/file/' + fileId,
    method: 'delete'
  })
}

// 导出minio文件
export function exportFile(query) {
  return request({
    url: '/system/file/export',
    method: 'get',
    params: query
  })
}
//获取桶列表
export function getBuckets() {
  return request({
    url: '/system/file/listBuckets',
    method: 'get'
  })
}
