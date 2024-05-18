package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import VendingMachine.Sell;

public class AdminMenu extends JPanel
{
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
		
		////////////////////////
		panelCollect.setBackground(Color.red);
		panelName.setBackground(Color.orange);
		panelPrice.setBackground(Color.yellow);
		panelMoney.setBackground(Color.green);
		panelChangeName.setBackground(Color.blue);
		/////////////////////////
		
		add(panelAdmin);
		
		//���� ���� ����
		JTextField textCollect = new JTextField();
		JButton btnCollectOK = new JButton("Ȯ��");

		//�̸� ���� ���� ����
		JButton [] btnNameItem = new JButton[6];
		ImageIcon [] imgItem = new ImageIcon[6];
		JTextField textNewName = new JTextField();
		
		//���� ���� ���� ����
		JButton [] btnPriceItem = new JButton[6];
		
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
			}
		});
		panelCollect.add(btnCollectOK);
		
		
		
		////////////////////////////////////////////////////////////////////////////
			
		for(int i = 0; i < 6; i++)
		{
			btnNameItem[i] = new JButton(Integer.toString(i));
			btnNameItem[i].setIcon(imgItem[i]);
			
			btnNameItem[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					layoutAdmin.show(panelAdmin, "changeName");
				}
			});
			panelName.add(btnNameItem[i]);
		}

		textNewName.setPreferredSize(new Dimension(400, 30));
		panelChangeName.add(textNewName);
		
		JButton btnOK = new JButton("Ȯ��");
		panelChangeName.add(btnOK);
		
		//////////////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < 6; i++)
		{
			btnPriceItem[i] = new JButton(Integer.toString(i));
			btnPriceItem[i].setIcon(imgItem[i]);
			
			btnPriceItem[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			panelPrice.add(btnPriceItem[i]);
		}
		
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
				textCollect.setText(Integer.toString(sell.money.collectMoney()) + "���� ���ݵǾ����ϴ�.");
			}		
		});
		add(btnCollect);
		
		//////////////////////////////////////////////////////////////////////////
		
		JButton btnName = new JButton("�̸� ����");
		btnName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "name");
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
			}
			
		});
		add(btnChangePw);
		
		//////////////////////////////////////////////////////////////////////////
		
		
		setVisible(true);
	}
}
