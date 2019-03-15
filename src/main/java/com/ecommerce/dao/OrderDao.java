package com.ecommerce.dao;

import org.springframework.stereotype.Repository;
import com.ecommerce.model.Order;

@Repository
public interface OrderDao {
	
	public Order placeanorder(Order order);
	
	

}


