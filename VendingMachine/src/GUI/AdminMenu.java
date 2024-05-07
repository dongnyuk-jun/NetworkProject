package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AdminMenu extends JPanel
{
	public AdminMenu(CardLayout layoutCard, JPanel panelCard)
	{
		setLayout(new FlowLayout());
		
		JButton btnSales = new JButton("판매 메뉴");
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
		
		JButton btnCollect = new JButton("수금");
		add(btnCollect);
		
		JButton btnName = new JButton("이름 변경");
		add(btnName);
		
		JButton btnPrice = new JButton("가격 변경");
		add(btnPrice);
		
		JButton btnMoney = new JButton("화폐 현황");
		add(btnMoney);

		
		JButton btnChangePw = new JButton("비밀번호 변경");
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
