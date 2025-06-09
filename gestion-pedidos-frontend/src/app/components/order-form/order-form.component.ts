import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrderService, OrderRequest, OrderItem } from '../../services/order.service';

@Component({
  selector: 'app-order-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent {
  clientId!: number;
  products: OrderItem[] = [{ productId: 0, quantity: 1 }];

  constructor(private orderService: OrderService) {}

  addProduct() {
    this.products.push({ productId: 0, quantity: 1 });
  }

  removeProduct(index: number) {
    this.products.splice(index, 1);
  }

  submitOrder() {
    const orderRequest: OrderRequest = {
      clientId: this.clientId,
      products: this.products.filter(p => p.productId > 0 && p.quantity > 0)
    };

    this.orderService.createOrder(orderRequest).subscribe({
      next: order => {
        alert(`Orden creada con ID: ${order.id}`);
      },
      error: err => {
        console.error(err);
        alert('Error creando la orden');
      }
    });
  }
}
