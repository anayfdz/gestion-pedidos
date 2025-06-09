import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface OrderItem {
  productId: number;
  quantity: number;
}

export interface OrderRequest {
  clientId: number;
  products: OrderItem[];
}

export interface Order {
  id: string;
  customerId: number;
  items: OrderItem[];
  total: number;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) {}

  createOrder(orderRequest: OrderRequest): Observable<Order> {
    return this.http.post<Order>(this.baseUrl, orderRequest);
  }

  getOrderById(id: string): Observable<Order> {
    return this.http.get<Order>(`${this.baseUrl}/${id}`);
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.baseUrl);
  }

}
