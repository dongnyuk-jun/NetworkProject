package GUI;

import VendingMachine.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Machine extends JFrame
{	
	public Machine(Sell sell, Admin admin)
	{
		CardLayout layoutCard = new CardLayout();
		JPanel panelCard = new JPanel(layoutCard);
		
		sell.salesmenu = new SalesMenu(sell, layoutCard, panelCard);
		sell.pwmenu = new PasswordMenu(layoutCard, panelCard, admin);
		sell.adminmenu = new AdminMenu(layoutCard, panelCard, sell);
		sell.changepwmenu = new ChangePwMenu(layoutCard, panelCard, admin);
		
		panelCard.add(sell.salesmenu, "sales");
		panelCard.add(sell.pwmenu, "password");
		panelCard.add(sell.adminmenu, "manager");
		panelCard.add(sell.changepwmenu, "changePw");
		
		add(panelCard);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setVisible(true);
	}

}