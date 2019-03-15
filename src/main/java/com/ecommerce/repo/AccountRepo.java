package com.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.ecommerce.model.Account;


public interface AccountRepo extends MongoRepository<Account,String> {
	
	
	
	
	   @Query("{accountantEmail:'?0'}")
	   public Account findByEmail(String accountantEmail);

}


