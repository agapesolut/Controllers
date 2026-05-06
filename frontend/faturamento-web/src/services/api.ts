import axios, { type AxiosResponse } from "axios";

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL ?? "http://localhost:8080/api",
});

export interface ApiEnvelope<T> {
  success: boolean;
  message: string;
  data: T;
}

export async function unwrapApiData<T>(
  request: Promise<AxiosResponse<ApiEnvelope<T>>>,
): Promise<T> {
  const response = await request;
  return response.data.data;
}
