package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AdminMenu extends JPanel
{
	public AdminMenu(CardLayout layoutCard, JPanel panelCard)
	{
		setLayout(new FlowLayout());
		
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
		
		JButton btnCollect = new JButton("����");
		add(btnCollect);
		
		JButton btnName = new JButton("�̸� ����");
		add(btnName);
		
		JButton btnPrice = new JButton("���� ����");
		add(btnPrice);
		
		JButton btnMoney = new JButton("ȭ�� ��Ȳ");
		add(btnMoney);

		
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
		
		setVisible(true);
	}
}
