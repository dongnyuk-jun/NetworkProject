package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Pattern;

import javax.swing.*;

import VendingMachine.Admin;

public class ChangePwMenu extends JPanel
{
	public ChangePwMenu(CardLayout layoutCard, JPanel panelCard, Admin admin)
	{
		setLayout(new BorderLayout());
		

		makeBorderBlank(this, "North");
		makeBorderBlank(this, "South");
		JLabel labelEast = new JLabel();
		labelEast.setPreferredSize(new Dimension(500, 30));
		add("East", labelEast);
		
		JLabel labelChangePw = new JLabel("변경할 비밀번호");
		add(labelChangePw, "West");
		JPasswordField textChangePw = new JPasswordField();
		textChangePw.selectAll();
		textChangePw.setEchoChar('*');
		textChangePw.setToolTipText("숫자와 특수문자를 각각 한 개 이상씩 포함하여 주십시오.");
		textChangePw.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JPasswordField p = (JPasswordField)e.getSource();
				char [] charPw = p.getPassword();
				String newPw = String.valueOf(charPw);
				
				if(checkPw(newPw))
				{
					admin.changePassword(newPw);
					
					layoutCard.show(panelCard, "sales");
				}
				p.setText("");
				
			}
			
		});
		add(textChangePw, "Center");
	}
	
	void makeBorderBlank(JPanel panel, String s)
	{
		JLabel labelBlank = new JLabel();
		labelBlank.setPreferredSize(new Dimension(900, 500));
		panel.add(s, labelBlank);
	}
	
	static boolean checkPw(String pw)
	{
		boolean isS = false;
		boolean isN = false;
		boolean is8 = false;
		
		if(pw.length() > 7)
			is8 = true;
		
		for(int i = 0; i < pw.length(); i++)
		{
			if(String.valueOf(pw.charAt(i)).matches("[^a-zA-z\\s]"))
			{
				if(Character.isDigit(pw.charAt(i)))
					isN = true;
				else
					isS = true;
			}
		}
		
		return isN && isS && is8;
	}
}
