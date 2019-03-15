package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dao.InventoryDao;
import com.ecommerce.model.Inventory;
import com.ecommerce.response.ApiResponseBody;

@RequestMapping("/inventory")
@RestController
public class InventoryController {
	
	
	@Autowired
	InventoryDao inventoryDao;
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/saveproduct")
	public ApiResponseBody saveproduct(@RequestBody Inventory inventory) {
		ApiResponseBody responsebody;
		responsebody = new ApiResponseBody();
		try {
			
			inventoryDao.saveProduct(inventory);
			
			
			responsebody.setMessage("Product saved SuccessFully");
			responsebody.setStatus(HttpStatus.OK);
			responsebody.setSuccess(true);


			return responsebody;
		} catch (Exception e) {
			responsebody.setMessage("Some Thing went wrong");
			responsebody.setStatus(HttpStatus.EXPECTATION_FAILED);

		}
		return responsebody;
	}

}


/**
HP
*/