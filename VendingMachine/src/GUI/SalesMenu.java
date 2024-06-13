package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import VendingMachine.Money;
import VendingMachine.Sell;

public class SalesMenu extends JPanel
{
	public Vector<String> itemName = new Vector<String>();

	public JButton [] btnItems = new JButton[6];
	ImageIcon [] imgItems = new ImageIcon[6];
	

	JButton [] btnMoney = new JButton[5];
	ImageIcon [] imgMoney = new ImageIcon[5];
	
	public SalesMenu(Sell sell, CardLayout layoutCard, JPanel panelCard)
	{
		for(int i = 0; i < sell.items.size(); i++)
		{
			itemName.add(sell.items.get(i).getName());
		}
		setLayout(new FlowLayout());
		
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(1900, 108));
		//panelTop.setBackground(Color.YELLOW);

		makeBlank(panelTop, new Dimension(500, 30));
		
		JLabel labelBalance = new JLabel("잔액");
		panelTop.add(labelBalance);
		
		JTextField textBalance = new JTextField(Integer.toString(sell.money.getBalance()) + "원");
		textBalance.setPreferredSize(new Dimension(100, 25));
		textBalance.setEditable(false);
		textBalance.setBackground(Color.WHITE);
		panelTop.add(textBalance);
		
		JButton btnReturn = new JButton("잔액 반환");
		btnReturn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				sell.money.returnMoney();
				textBalance.setText(Integer.toString(sell.money.getBalance()) + "원");
				for(int i = 0; i < 6; i++)
				{
					if(sell.items.get(i).getCount() > 0 && sell.items.get(i).getPrice() <= sell.money.getBalance())
						btnItems[i].setEnabled(true);
					else
						btnItems[i].setEnabled(false);
				}
			}
			
		});
		
		panelTop.add(btnReturn);

		makeBlank(panelTop, new Dimension(500, 30));
		
		JButton btnAdmin = new JButton("관리자 메뉴");
		btnAdmin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				layoutCard.show(panelCard, "password");
			}
		});
		
		
		panelTop.add(btnAdmin);
		
		add(panelTop);
		

		////////////////////////////////////////////////////////////////////////////
		
		Dimension panelSize = new Dimension(940, 920);
		
		JPanel panelItems = new JPanel();
		panelItems.setPreferredSize(panelSize);
		//panelItems.setBackground(Color.GREEN);

		for(int i = 0; i < 6; i++)
		{
			imgItems[i] = new ImageIcon("./image/item" + i + ".jpg");
			btnItems[i] = new JButton(itemName.get(i));
			btnItems[i].setIcon(imgItems[i]);
			btnItems[i].setBorderPainted(false);
			btnItems[i].setBackground(Color.DARK_GRAY);
			btnItems[i].setEnabled(false);
			btnItems[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					JButton btn = (JButton)e.getSource();
					String value = e.getActionCommand();
					if(sell.sellItem(value))
					{
						btn.setEnabled(false);
					}
					for(int i = 0; i < 6; i++)
					{
						if(sell.items.get(i).getCount() > 0 && sell.items.get(i).getPrice() <= sell.money.getBalance())
							btnItems[i].setEnabled(true);
						else
							btnItems[i].setEnabled(false);
					}
					textBalance.setText(Integer.toString(sell.money.getBalance()) + "원");
				}
			});
			
			panelItems.add(btnItems[i]);
		}
		
		add(panelItems);
		

		////////////////////////////////////////////////////////////////////////////
		
		JPanel panelMoney = new JPanel();
		panelMoney.setPreferredSize(panelSize);
		//panelMoney.setBackground(Color.BLUE);
		
		for(int i = 0; i < 5; i++)
		{
			imgMoney[i] = new ImageIcon("./image/money" + i + ".jpg");
			btnMoney[i] = new JButton(Integer.toString(i));
			btnMoney[i].setIcon(imgMoney[i]);
			btnMoney[i].setBackground(Color.DARK_GRAY);
			
			btnMoney[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					JButton btn = (JButton)e.getSource();
					String value = btn.getText();
					
					boolean checkId = sell.money.inputMoney(Integer.parseInt(value));
					if(sell.money.getBalance() < 7000)
					{
						if(checkId)
						{
							btn.setEnabled(false);
						}
						else
							btn.setEnabled(true);
					}
					else
					{
						for(int i = 0; i < 5; i++)
						{
							btnMoney[i].setEnabled(false);
						}
					}
					for(int i = 0; i < 6; i++)
					{
						if(sell.items.get(i).getCount() > 0 && sell.items.get(i).getPrice() <= sell.money.getBalance())
							btnItems[i].setEnabled(true);
						else
							btnItems[i].setEnabled(false);
					}

					textBalance.setText(Integer.toString(sell.money.getBalance()) + "원");
				}
			});
			
			panelMoney.add(btnMoney[i]);
		}
		
		add(panelMoney);
	}

	void makeBlank(JPanel panel, Dimension size)
	{
		JLabel labelBlank = new JLabel();
		labelBlank.setPreferredSize(size);
		panel.add(labelBlank);
	}
}
