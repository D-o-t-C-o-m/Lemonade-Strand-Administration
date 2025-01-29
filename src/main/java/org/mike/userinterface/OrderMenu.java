package org.mike.userinterface;

import org.mike.domain.Order;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.OrderService;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class OrderMenu {
private OrderService orderService;
public OrderMenu(OrderService orderService) {
	this.orderService = orderService;
}
public void runOrderOption(Scanner scanner) {
	System.out.println("You want to create a new order.");

	System.out.print("Order id: ");
	int id = scanner.nextInt();

	System.out.print("Lemonade id: ");
	int lemonadeId = scanner.nextInt();

	System.out.print("Quantity: ");
	int quantity = scanner.nextInt();

	try {
		Order order = orderService.saveOrder(id, lemonadeId, quantity);
		System.out.printf("The order with ID=%s has been saved \n", order.getId());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the order: " + e.getMessage());
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}
}

}
