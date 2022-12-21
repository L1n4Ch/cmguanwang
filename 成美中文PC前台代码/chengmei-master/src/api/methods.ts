import requestPath from '@/utils/request'
import {PATH} from '@/api/path'

export async function BANNERLIST(): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.BANNERLIST);
}
//兴趣服务接口
export async function getService(): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.service);
}
// 捐款人信息
export async function addDocutionUser(obj:object):Promise<any> {
    const request = await requestPath();
    return request.post(PATH.docutionUser,obj);
}
// 加入我们
export async function addJoinUsPost(obj:object):Promise<any> {
    const request = await requestPath();
    return request.post(PATH.JoinUs,obj);
}
// 留言展示接口
export async function getShowMsg(obj:{[propsName:string] :any}): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.Msg+`?pageNum=${obj.pageNum}&pageSize=${obj.pageSize}`);
}
// 捐款查询接口
export async function getDonationList(obj:{[propsName:string] :any}): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.list+`?donationCompany=${obj.donationCompany}&pageNum=${obj.pageNum}&pageSize=${obj.pageSize}&donationTime=${obj.donationTime}`);
}
// 人才招聘
export async function getShowRecruits(obj:{[propsName:string] :any}): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.ruits+`?pageNum=${obj.pageNum}&pageSize=${obj.pageSize}`);
}
// 基础信息
export async function getBaseInfo(): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.info);
}

// 性别
export async function getSex(): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.sex);
}
// 服务期限
export async function getServiceTime(): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.Time);
}
// 文章接口
export async function getSArticleList(obj:{[propsName:string] :any}): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.list2+`?key=${obj.key}&menuId=${obj.menuId}&newStatus=${obj.newStatus}&pageNum=${obj.pageNum}&pageShow=${obj.pageShow}&pageSize=${obj.pageSize}&showDetail=${obj.showDetail}`);
}
// 文章详情获取接口
export async function getArticleDetail(obj:{[propsName:string] :any}): Promise<any> {
    const request = await requestPath();
    return request.get(PATH.detail+`?articleId=${obj.articleId}`);
}

export async function BASEINFO(): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.BASEINFO)
}
export async function donationList(data: {pageNum: any, pageSize: any,donationCompany: any}): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.donationList + '?pageNum=' + data.pageNum + '&pageSize=' + data.pageSize + '&donationCompany=' + data.donationCompany)
}
export async function bigEvent(): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.bigEvent)
}
export async function menuList(data: {parentId: any}): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.menuList + '?parentId=' + data.parentId)
}

export async function articleList(data: {menuId: any, newStatus?: any, pageNum?: any, pageShow?: any, pageSize?: any,showDetail?: any}): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.articleList + '?menuId=' + data.menuId + "&newStatus=" + data.newStatus + "&pageNum=" + data.pageNum + "&pageShow=" + data.pageShow + "&pageSize=" + data.pageSize + "&showDetail=" + data.showDetail)
}
export async function AiXinshowMsg(data: {pageNum?: any,pageSize?: any}): Promise<any> {
    const request = await requestPath()
    return request.get(PATH.AiXinshowMsg)
}

//获取文章
export async function getAPPLY(params:any): Promise<any> {
     const request = await requestPath()
     return request.get(PATH.APPLY,{params})
}
//查看文章详情
export async function getDETAILS(params:any): Promise<any> {
     const request = await requestPath()
     return request.get(PATH.DETAILS,{params})
}

//获取最新文章
export async function getNEWEST(params:any): Promise<any> {
     const request = await requestPath()
     return request.get(PATH.NEWEST,{params})
}

// 菜单获取接口
export async function getMenuList(obj:{[propsName:string] :any}) {
    const request = await requestPath();
    // return request.get(MenuLis.list+`?parentId=${obj.parentId}`);
    return request.get(PATH.menuList+`?parentId=${obj.parentId}`);
}
//留言添加接口
export async function PostaddDocutionUser(obj:object):Promise<any> {
    const request = await requestPath();
    return request.post(PATH.msg,obj);
}
//获取菜单
export async function getMENU(params:any): Promise<any> {
     const request = await requestPath()
     return request.get(PATH.MENU,{params})
}

//获取菜单
export async function addVISIT(params:any): Promise<any> {
     const request = await requestPath()
     return request.get(PATH.VISIT,{params})
}


// 菜单详情接口
export async function getMUNt(params:any) {
    const request = await requestPath()
    return request.get(PATH.MUNT,{params})
}


//导航点击调用
export async function addVISITS(params:any) {
    const request = await requestPath()
    return request.get(PATH.ADDVISITS,{params})
}
