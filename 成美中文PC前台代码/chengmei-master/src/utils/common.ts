export const link = process.env.NODE_ENV === 'development' ? 'http://www.chengmei.org.cn:8088' : ''
export const ellipsis = (value: any, num=100) => {
    if (!value) return "";
    if (value.length > num) {
      return value.slice(0, num) + "...";
    }
    return value;
  }
