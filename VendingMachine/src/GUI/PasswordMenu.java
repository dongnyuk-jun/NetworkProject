package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import VendingMachine.Admin;

public class PasswordMenu extends JPanel
{
	public PasswordMenu(CardLayout layoutCard, JPanel panelCard, Admin admin)
	{
		setLayout(new BorderLayout());
		
		makeBorderBlank(this, "North");
		makeBorderBlank(this, "South");
		JLabel labelEast = new JLabel();
		labelEast.setPreferredSize(new Dimension(500, 30));
		add("East", labelEast);
		
		JLabel labelPw = new JLabel("비밀번호: ");
		labelPw.setPreferredSize(new Dimension(250, 30));
		add("West", labelPw);
		JPasswordField textPw = new JPasswordField();
		textPw.selectAll();
		textPw.setEchoChar('*');
		textPw.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JPasswordField p = (JPasswordField)e.getSource();
				char [] charPw = p.getPassword();
				String checkPw = String.valueOf(charPw);
				
				if(checkPw.equals(admin.getPassword()))
				{
					layoutCard.show(panelCard, "manager");
				}
				else
				{
					layoutCard.show(panelCard, "sales");
				}
				p.setText("");
			}
			
		});
		
		add("Center", textPw);
	}	
	
	void makeBorderBlank(JPanel panel, String s)
	{
		JLabel labelBlank = new JLabel();
		labelBlank.setPreferredSize(new Dimension(900, 500));
		panel.add(s, labelBlank);
	}
}
