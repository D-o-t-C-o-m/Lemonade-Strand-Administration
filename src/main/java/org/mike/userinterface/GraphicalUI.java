package org.mike.userinterface;

import javax.swing.*;
import java.awt.*;
public class GraphicalUI{
public GraphicalUI(){
	JFrame frame = new JFrame("Lemonade Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300,300);


	JMenuBar menu = new JMenuBar();


	JMenu menu1 = new JMenu("Manager");
	menu.add(menu1);
	JMenuItem manager1 = new JMenuItem("Manager Supplier");
	JMenuItem manager2 = new JMenuItem("Manage Products");
	JMenuItem manager3 = new JMenuItem("Manage Recipes");
	menu1.add(manager1);
	menu1.add(manager2);
	menu1.add(manager3);

	JMenu menu2 = new JMenu("Reports");
	menu.add(menu2);
	JMenuItem report1 = new JMenuItem("Daily Sales Report");
	JMenuItem report2 = new JMenuItem("Out of Stock Report");
	menu2.add(report1);
	menu2.add(report2);
	JPanel panel = new JPanel();



	JButton button1 = new JButton("Create an Order");
	button1.addActionListener((event) -> System.exit(0));
	frame.getContentPane().add(BorderLayout.NORTH, menu);
	frame.getContentPane().add(button1,BorderLayout.CENTER);

	frame.setVisible(true);
}
}
