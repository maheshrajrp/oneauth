import axios from "axios";

export const axiosIns = axios.create({
  baseURL: (window as any).apiBaseUrl,
});