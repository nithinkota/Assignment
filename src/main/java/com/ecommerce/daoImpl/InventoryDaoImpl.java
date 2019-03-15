package com.ecommerce.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.ecommerce.dao.InventoryDao;
import com.ecommerce.model.Inventory;


@Service
public class InventoryDaoImpl implements InventoryDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	
	//saving products
	public void saveProduct(Inventory inventory) {
		
		//sample products to test
		
		Inventory obj1=new Inventory();
		obj1.setProductName("Pen");
		obj1.setProductCost(5);
		obj1.setAvailableProductCount(10);
		mongoTemplate.insert(obj1, "Inventory");
		
		Inventory obj2=new Inventory();
		obj2.setProductName("Pencil");
		obj2.setProductCost(3);
		obj2.setAvailableProductCount(15);
		mongoTemplate.insert(obj2, "Inventory");
		
		//uncomment below line to store new product
		
		//mongoTemplate.insert(inventory,"Inventory");

	}

}

