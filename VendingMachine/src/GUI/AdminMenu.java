package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AdminMenu extends JPanel
{
	public AdminMenu(CardLayout layoutCard, JPanel panelCard)
	{
		setLayout(new FlowLayout());
		
		JPanel panelCollect = new JPanel();
		
		JPanel panelName = new JPanel();
		
		JPanel panelPrice = new JPanel();
		
		JPanel panelMoney = new JPanel();
		
		JPanel panelChangeName = new JPanel();
		

		CardLayout layoutAdmin = new CardLayout();
		JPanel panelAdmin = new JPanel(layoutAdmin);
		
		panelAdmin.add(panelCollect, "collect");
		panelAdmin.add(panelName, "name");
		panelAdmin.add(panelPrice, "price");
		panelAdmin.add(panelMoney, "money");
		panelAdmin.add(panelChangeName, "changeName");
		
		add(panelAdmin);
		

		JButton [] btnNameItem = new JButton[6];
		ImageIcon [] imgNameItem = new ImageIcon[6];
		for(int i = 0; i < 6; i++)
		{
			imgNameItem[i] = new ImageIcon("./image/item" + i + ".jpg");
			btnNameItem[i] = new JButton(Integer.toString(i));
			btnNameItem[i].setIcon(imgNameItem[i]);
			
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
		
		

		JTextField textNewName = new JTextField();
		textNewName.setPreferredSize(new Dimension(400, 30));
		panelChangeName.add(textNewName);
		
		JButton btnOK = new JButton("확인");
		panelChangeName.add(btnOK);
		
		
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
		btnName.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				layoutAdmin.show(panelAdmin, "name");
			}
		});
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
