package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Order;
import com.ecommerce.response.ApiResponseBody;


@RequestMapping("/order")
@RestController
public class OrderController {
	
	@Autowired
	OrderDao orderDao;
	
	
	
	

	
	
	
	


	@CrossOrigin(origins = "*")
	@PostMapping("/placeanorder")
	public ApiResponseBody placeanorder(@RequestBody Order order) {
		ApiResponseBody responsebody;
		responsebody = new ApiResponseBody();
		try {
			Order orderObj = orderDao.placeanorder(order);
			if (orderObj.getResponseMessage().equals("Success")) {
				responsebody.setData(orderObj);
				responsebody.setMessage("Order placed SuccessFully");
			}

			responsebody.setMessage(orderObj.getResponseMessage());
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

