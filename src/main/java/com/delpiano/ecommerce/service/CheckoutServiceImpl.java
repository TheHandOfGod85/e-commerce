package com.delpiano.ecommerce.service;

import com.delpiano.ecommerce.dao.CustomerRepository;
import com.delpiano.ecommerce.dto.Purchase;
import com.delpiano.ecommerce.dto.PurchaseResponse;
import com.delpiano.ecommerce.entity.Customer;
import com.delpiano.ecommerce.entity.Order;
import com.delpiano.ecommerce.entity.OrderItem;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService{
  private CustomerRepository customerRepository;

  @Autowired
  public CheckoutServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  @Transactional
  public PurchaseResponse placeOrder(Purchase purchase) {
    // retrieve the order info from dto
    Order order = purchase.getOrder();
    // generate tracking number
    String orderTrackingNumber = generateOrderTrackingNumber();
    order.setOrderTrackingNumber(orderTrackingNumber);
    // populate order with orderItems
    Set<OrderItem> orderItems = purchase.getOrderItems();
    orderItems.forEach(item-> order.add(item));
    // populate order with billingAddress and shippingAddress
    order.setBillingAddress(purchase.getBillingAddress());
    order.setShippingAddress(purchase.getShippingAddress());
    // populate customer with order
    Customer customer = purchase.getCustomer();
    customer.add(order);
    // save to the database
    customerRepository.save(customer);
    // return the response
    return new PurchaseResponse(orderTrackingNumber);
  }

  private String generateOrderTrackingNumber() {

    // generate a radon UUID number (UUID)
    return UUID.randomUUID().toString();
  }
}
