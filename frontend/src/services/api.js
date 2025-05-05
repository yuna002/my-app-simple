import axios from 'axios';

// 設定 API 基本網址
const api = axios.create({
  baseURL: 'http://localhost:8080/api',  // 這裡是你的後端 API 路徑
  headers: {
    'Content-Type': 'application/json',
  },
});

// 取得所有幣別資料
export const getCurrencies = () => api.get('/currency');

// 取得單筆幣別資料
export const getCurrency = (code) => api.get(`/currency/${code}`);

// 新增幣別資料
export const addCurrency = (currency) => api.post('/currency', currency);

// 更新幣別資料
export const updateCurrency = (currency) => api.put(`/currency/${currency.code}`, currency);

// 刪除幣別資料
export const deleteCurrency = (code) => api.delete(`/currency/${code}`);
