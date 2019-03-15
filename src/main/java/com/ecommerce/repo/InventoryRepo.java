package com.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Inventory;

@Repository
public interface InventoryRepo extends MongoRepository<Inventory,String>{

}

