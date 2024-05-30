/*
package GUI;

import VendingMachine.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Machine extends JFrame
{
	Amount amount = new Amount();
	
	public Machine()
	{
		JLabel amountLabel = new JLabel("잔액 ");
		JTextField amountText = new JTextField(amount.getAmount());
		
		add(amountLabel);
		add(amountText);
		
		setSize(new Dimension(1920, 1080));		//gui 창 크기 1920x1080
		
		JPanel mainPanel = new JPanel();
		
		ImageIcon [] img = new ImageIcon[5];
		
		img[0] = new ImageIcon("./image/10won.jpg");
		img[1] = new ImageIcon("./image/50won.jpg");
		img[2] = new ImageIcon("./image/100won.jpg");
		img[3] = new ImageIcon("./image/500won.jpg");
		img[4] = new ImageIcon("./image/1000won.jpg");
		
		JButton [] btn = new JButton[5];
		for (int i = 0; i < 5; i++)
		{
			btn[i] = new JButton(img[i]);
			btn[i].addActionListener(new ButtonListener(i));
			mainPanel.add(btn[i]);
		}
		
		JButton returnBtn = new JButton("잔액 반환");
		returnBtn.addActionListener(new returnBtnListener());
		mainPanel.add(returnBtn);
		
		add(mainPanel);
		setVisible(true);
	}
	
	public static void main(String[] argv)
	{
		new Machine();
	}
	
	class ButtonListener implements ActionListener
	{
		int num = -1;
		public ButtonListener(int num)
		{
			this.num = num;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String note = "";
			// TODO Auto-generated method stub
			switch(num)
			{
			case 0:
				note = amount.charge(10);
				break;
			case 1:
				note = amount.charge(50);
				break;
			case 2:
				note = amount.charge(100);
				break;
			case 3:
				note = amount.charge(500);
				break;
			case 4:
				note = amount.charge(1000);
				break;
			}
			System.out.println(note);
			System.out.println("잔액: " + amount.getAmount());
		}
	}
	
	class returnBtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			String note = "";
			note = amount.change();
			System.out.println(note);
		}
	}
}
*/

///*
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
		
		panelCard.add(new SalesMenu(sell, layoutCard, panelCard), "sales");
		panelCard.add(new PasswordMenu(layoutCard, panelCard, admin), "password");
		panelCard.add(new AdminMenu(layoutCard, panelCard, sell), "manager");
		panelCard.add(new ChangePwMenu(layoutCard, panelCard, admin), "changePw");
		
		add(panelCard);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setVisible(true);
	}

}
/*
문제점
코드가 없는 것이 있음(-)
근데 의약품 코드가 처방전의 primary key임
*/