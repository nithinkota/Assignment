package com.ecommerce.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Account;
import com.ecommerce.model.Inventory;
import com.ecommerce.model.Order;
import com.ecommerce.repo.AccountRepo;
import com.ecommerce.repo.InventoryRepo;

@Service
public class OrderDaoImpl implements OrderDao{
	
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	InventoryRepo inventoryRepo;

	@Autowired
	AccountRepo accountRepo;
   
	
   
	public Order placeanorder(Order order) {

		//getting customer account details
		Account account = accountRepo.findByEmail(order.getAccountantEmail());
		
		
        //getting product details
		Optional<Inventory> optionalOrder = inventoryRepo.findById(order.getProductId());

		if (optionalOrder.isPresent()) {

			//checking product available quantity is greaterthan or equal to customer required quantity 
			if (order.getProductQuantity() <= optionalOrder.get().getAvailableProductCount()) {

				//calculating cost
				double orderCost = order.getProductQuantity() * optionalOrder.get().getProductCost();
				order.setOrderCost(orderCost);

				//checking payment type
				if (order.getPaymentType().equalsIgnoreCase("onlinepayment")) {

					if (order.getPaymentStatus().equalsIgnoreCase("Success")) {

						//creating oder
						createOrder(order, account);

						order.setResponseMessage("Success");
						return order;
					} else if (order.getPaymentStatus().equalsIgnoreCase("Failure")) {
						order.setResponseMessage("Payment Failed.Please try again.");
					}

				} 
				//cash on delivery
				else if (order.getPaymentType().equalsIgnoreCase("cod")) {
					
					//creating oder
					createOrder(order, account);
					order.setResponseMessage("Success");
					return order;
				}

			} else {
				order.setResponseMessage("Currently your required quantity of this product is not available");
				return order;
			}

		} else {
			order.setResponseMessage("Currently this product not available");
			return order;
		}

		return null;
	}

	
	
	public Order createOrder(Order order, Account account) {
	
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 
		
		//generating orderID
		String orderId = UUID.randomUUID().toString();
		String ordermessage="Your order has been placed successfully with orderID "+orderId 
					+".\nPlease use orderID to view or manage your order.\nThank you"; 
		
		order.setOrderId(orderId);
		order.setAccountantEmail(account.getAccountantEmail());
		order.setAccountantName(account.getAccountantName());
		order.setOrderDate(formatter.format(new Date()));
		order.setOrderStatus("Order placed  Successfully");
		
		//order placing
		mongoTemplate.insert(order, "Order");
		
        //decreasing product quantity available after  placing order successfully
		Query productQuery = new Query(Criteria.where("productId").is(order.getProductId()));
		Update update = new Update();
		update.inc("availableProductCount", -order.getProductQuantity());
		mongoTemplate.updateFirst(productQuery, update, Inventory.class, "Inventory");




    //sending mail to customer asynchronously regarding order details 
    sendmail(account.getAccountantEmail(),"Order Conformation",ordermessage);


		
		
		return order;

	}

	
	public void sendmail(String toEmail, String subject, String text)  {

		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {

				try {

					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom("nithinroyal946@gmail.com");
					message.setTo(toEmail);
					message.setSubject(subject);
					message.setText(text);
					javaMailSender.send(message);

				} catch (Exception e) {

				}
			}
		});

	}
	
	

}
