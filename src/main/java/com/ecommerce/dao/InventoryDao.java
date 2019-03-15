package com.ecommerce.dao;

import org.springframework.stereotype.Repository;
import com.ecommerce.model.Inventory;

@Repository
public interface InventoryDao {
	
	public void saveProduct(Inventory inventory);

}

