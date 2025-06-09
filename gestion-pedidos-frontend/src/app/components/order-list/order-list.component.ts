import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderService, Order } from '../../services/order.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent {
  orderId: string = '';
  order: Order | null = null;
  loading = false;
  error = '';

  constructor(private orderService: OrderService) {}

  buscarOrden() {
    this.order = null;
    this.error = '';
    if (!this.orderId) {
      this.error = 'Por favor ingresa un ID de orden.';
      return;
    }
    this.loading = true;
    this.orderService.getOrderById(this.orderId).subscribe({
      next: (order) => {
        this.order = order;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'No se encontró la orden o hubo un error.';
        this.loading = false;
      }
    });
  }
} 