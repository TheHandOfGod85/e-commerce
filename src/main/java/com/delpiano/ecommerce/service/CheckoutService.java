package com.delpiano.ecommerce.service;

import com.delpiano.ecommerce.dto.Purchase;
import com.delpiano.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
  PurchaseResponse placeOrder(Purchase purchase);

}
