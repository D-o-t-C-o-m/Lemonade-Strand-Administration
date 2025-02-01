package org.mike;

import org.mike.exceptions.IDNotUniqueException;
import org.mike.repository.*;
import org.mike.service.LemonadeService;
import org.mike.service.OrderService;
import org.mike.service.ProductService;
import org.mike.service.SupplierService;
import org.mike.userinterface.GraphicalUI;
import org.mike.userinterface.TextAreaOutputStream;
import org.mike.userinterface.UserInterface;
import org.mike.validators.ProductValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
public static void main(String[] args) throws IOException, IDNotUniqueException {

	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
	SupplierService supplierService = new SupplierService(supplierRepository);

	ProductFileRepository productRepository = new ProductFileRepository("products.csv");
	ProductValidator productValidator = new ProductValidator();
	ProductService productService = new ProductService(productRepository, productValidator, supplierService);

	LemonadeFileRepository lemonadeRepository = new LemonadeFileRepository("lemonade.csv");
	LemonadeRecipeFileRepository lemonadeRecipeRepository = new LemonadeRecipeFileRepository("lemonade-recipes.csv",productRepository,lemonadeRepository,supplierRepository);
	LemonadeService lemonadeService = new LemonadeService(lemonadeRecipeRepository,lemonadeRepository);

	OrderFileRepository orderFileRepository = new OrderFileRepository("orders.csv", lemonadeService);
	OrderService orderService = new OrderService(orderFileRepository, lemonadeService, productService);

	UserInterface userInterface = new UserInterface(productService, supplierService, lemonadeService, orderService);

	JTextArea textArea = new JTextArea(20, 50);
	textArea.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(textArea);

	JTextField inputField = new JTextField(50);
	inputField.setEditable(true);


	JFrame frame = new JFrame("Lemonade Stand Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(new BorderLayout());
	frame.add(scrollPane, BorderLayout.CENTER);
	frame.add(inputField, BorderLayout.SOUTH);
	frame.pack();
	frame.setVisible(true);


	TextAreaOutputStream taos = new TextAreaOutputStream(textArea);
	System.setOut(new java.io.PrintStream(taos, true));


	System.out.println("Welcome to the Lemonade Stand Administration App.");


	inputField.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			String command = inputField.getText().trim();

			inputField.setText("");

			processCommand(command);

			System.out.println("You entered: " + command);
		}
	});
	GraphicalUI graphicalUI = new GraphicalUI();
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();

}
private static void processCommand(String command) {
	// This is where you process the command entered by the user.
	// You can define your own commands and their behavior here.

	if (command.equalsIgnoreCase("help")) {
		System.out.println("Available commands: help, exit, etc.");
	} else if (command.equalsIgnoreCase("exit")) {
		System.out.println("Exiting application...");
		System.exit(0);  // Exit the program
	} else {
		System.out.println("Unknown command: " + command);
	}
}
}
