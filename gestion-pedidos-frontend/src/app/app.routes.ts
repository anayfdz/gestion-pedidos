import { Routes } from '@angular/router';
import { OrderFormComponent } from './components/order-form/order-form.component';
import { OrderListComponent } from './components/order-list/order-list.component';

export const routes: Routes = [
    { path: '', redirectTo: 'crear-orden', pathMatch: 'full' },
    { path: 'crear-orden', component: OrderFormComponent },
    { path: 'obtener-ordenes', component: OrderListComponent }
];
