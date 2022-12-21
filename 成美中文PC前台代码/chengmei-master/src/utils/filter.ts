export const numFilter = (value: any) => {
  if (!value) return '0.00';
  const intPart = Number(value).toFixed(0); //获取整数部分
  const intPartFormat = intPart.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'); //将整数部分逢三一断
  return intPartFormat;
}