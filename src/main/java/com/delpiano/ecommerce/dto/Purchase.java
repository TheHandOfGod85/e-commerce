package com.delpiano.ecommerce.dto;

import com.delpiano.ecommerce.entity.Address;
import com.delpiano.ecommerce.entity.Customer;
import com.delpiano.ecommerce.entity.Order;
import com.delpiano.ecommerce.entity.OrderItem;
import java.util.Set;
import lombok.Data;

@Data
public class Purchase {
  private Customer customer;
  private Address shippingAddress;
  private Address billingAddress;
  private Order order;
  private Set<OrderItem> orderItems;

}
