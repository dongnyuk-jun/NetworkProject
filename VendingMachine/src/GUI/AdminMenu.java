package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import VendingMachine.Sell;

public class AdminMenu extends JPanel
{
	public JButton [] btnNameItem; 
	public JButton [] btnPriceItem;
	public AdminMenu(CardLayout layoutCard, JPanel panelCard, Sell sell)
	{
		setLayout(new FlowLayout());
		
		//������ �޴� panel
		JPanel panelEmpty = new JPanel();		
		JPanel panelCollect = new JPanel();		
		JPanel panelName = new JPanel();		
		JPanel panelPrice = new JPanel();		
		JPanel panelMoney = new JPanel();		
		JPanel panelChangeName = new JPanel();
		JPanel panelChangePrice = new JPanel();
		
		//card panel ����
		CardLayout layoutAdmin = new CardLayout();
		JPanel panelAdmin = new JPanel(layoutAdmin);
		panelAdmin.setPreferredSize(new Dimension(1900, 800));
		
		//paneladmin�� ������ �޴� panel ����
		panelAdmin.add(panelEmpty, "empty");
		panelAdmin.add(panelCollect, "collect");
		panelAdmin.add(panelName, "name");
		panelAdmin.add(panelPrice, "price");
		panelAdmin.add(panelMoney, "money");
		panelAdmin.add(panelChangeName, "changeName");
		panelAdmin.add(panelChangePrice, "changePrice");
		
		add(panelAdmin);
		
		//���� ���� ����
		JTextField textCollect = new JTextField();
		JButton btnCollectOK = new JButton("Ȯ��");

		//�̸� ���� ���� ����
		btnNameItem = new JButton[6];
		ImageIcon [] imgItem = new ImageIcon[6];
		JTextField textNewName = new JTextField();
		
		//���� ���� ���� ����
		btnPriceItem = new JButton[6];	//�̸� ���� �� ����ȭ�� ���� public ����
		JTextField textNewPrice = new JTextField();
		
		//���� ��Ȳ ���� ����
		ImageIcon [] imgMoney = new ImageIcon[5];
		JLabel [] labelMoney = new JLabel[5];
		JTextField [] textMoney = new JTextField[5];
		
		for(int i = 0; i < 6;i++)
			imgItem[i] = new ImageIcon("./image/item" + i + ".jpg");
		
		////////////////////////////////////////////////////////////////////////////		
		
		textCollect.setFont(new Font("�������", Font.BOLD, 15));
		panelCollect.add(textCollect);
		
		btnCollectOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "empty");
				re();
			}
		});
		panelCollect.add(btnCollectOK);
		
		
		
		////////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < 6; i++)
		{
			btnNameItem[i] = new JButton(sell.items.get(i).getName());
			btnNameItem[i].setIcon(imgItem[i]);
			
			btnNameItem[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String originName = e.getActionCommand();
					sell.setOriginName(originName);
					textNewName.setText(originName);
					layoutAdmin.show(panelAdmin, "changeName");
					re();
				}
			});
			panelName.add(btnNameItem[i]);
		}

		textNewName.setPreferredSize(new Dimension(400, 30));
		panelChangeName.add(textNewName);
		
		JButton btnOK = new JButton("Ȯ��");
		btnOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String newName = textNewName.getText();
				sell.setNewName(newName);
				sell.changeItemName();
				textNewName.setText("");
				layoutAdmin.show(panelAdmin, "empty");
				sell.salesmenu.revalidate();
				sell.salesmenu.repaint();
				re();
			}
		});
		panelChangeName.add(btnOK);
		
		//////////////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < 6; i++)
		{
			btnPriceItem[i] = new JButton(sell.items.get(i).getName());
			btnPriceItem[i].setIcon(imgItem[i]);
			
			btnPriceItem[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String originPrice = e.getActionCommand();
					sell.setOriginName(originPrice);
					textNewPrice.setText(Integer.toString(sell.getItemPrice(originPrice)));
					layoutAdmin.show(panelAdmin, "changePrice");
					re();
				}
			});
			panelPrice.add(btnPriceItem[i]);
		}
		
		textNewPrice.setPreferredSize(new Dimension(400, 30));
		panelChangePrice.add(textNewPrice);
		
		JButton btnOk = new JButton("Ȯ��");
		btnOk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String newPrice = textNewPrice.getText();
				if(newPrice.chars().allMatch(Character::isDigit))
				{
					sell.setNewPrice(newPrice);
					sell.changeItemPrice();
					textNewPrice.setText("");
					layoutAdmin.show(panelAdmin, "empty");
					re();
				}
				else
				{
					textNewPrice.setText("���ڸ� �Է����ּ���.");
				}
			}
		});
		panelChangePrice.add(btnOk);
		
		//////////////////////////////////////////////////////////////////////////


		
		//////////////////////////////////////////////////////////////////////////
		
		
		for(int i = 0; i < 5; i++)
		{
			imgMoney[i] = new ImageIcon("./image/money" + i + ".jpg");
			labelMoney[i] = new JLabel(imgMoney[i]);
			textMoney[i] = new JTextField();
			panelMoney.add(labelMoney[i]);
		}
		for(int i = 0; i < 5; i++)
		{
			panelMoney.add(textMoney[i]);
			textMoney[i].setPreferredSize(new Dimension(300, 30));
		}
		
		//////////////////////////////////////////////////////////////////////////
		
		
		JButton btnSales = new JButton("�Ǹ� �޴�");
		btnSales.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				layoutCard.show(panelCard, "sales");
				
				sell.salesmenu.revalidate();
				sell.salesmenu.repaint();
				re();
			}
			
		});
		add(btnSales);
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnCollect = new JButton("����");
		btnCollect.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "collect");
				textCollect.setText("���ݵǾ����ϴ�.");
				re();
			}		
		});
		add(btnCollect);
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnRefill = new JButton("���ä���");
		btnRefill.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sell.refill();
				re();
			}		
		});
		add(btnRefill);
		
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnName = new JButton("�̸� ����");
		btnName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "name");
				re();
			}
		});
		add(btnName);
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnPrice = new JButton("���� ����");
		btnPrice.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "price");
				re();
			}
		});
		add(btnPrice);
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnMoney = new JButton("ȭ�� ��Ȳ");
		
		btnMoney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "money");
				for(int i = 0; i < 5; i++)
					textMoney[i].setText(Integer.toString(sell.money.getNowMoney(i)));
				re();
			}
		});
		add(btnMoney);
		
		//////////////////////////////////////////////////////////////////////////

		
		JButton btnChangePw = new JButton("��й�ȣ ����");
		btnChangePw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				layoutCard.show(panelCard, "changePw");
				re();
			}
			
		});
		add(btnChangePw);
		
		//////////////////////////////////////////////////////////////////////////
		
		
		setVisible(true);
	}
	
	void re()
	{
		
		revalidate();
		repaint();
	}
}
