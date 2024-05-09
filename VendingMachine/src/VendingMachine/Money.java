package VendingMachine;

import java.io.*;
import java.util.*;

public class Money
{
	Vector<Integer> money = new Vector<Integer>();
	Vector<Integer> input = new Vector<Integer>();
	Vector<Integer> tmp = new Vector<Integer>();
	int balance;
	
	public Money()
	{
		balance = 0;
		for(int i = 0; i < 5; i++)
		{
			input.add(0);
		}
		
		BufferedReader moneyReader = null;
		try
		{
			StringTokenizer st;
			moneyReader = new BufferedReader(new FileReader("./manager/money.txt"));
			for(int i = 0; i < 5; i++)
			{
				st = new StringTokenizer(moneyReader.readLine());
				String m = st.nextToken();
				money.add(Integer.parseInt(m));
			}
			moneyReader.close();
		} catch (FileNotFoundException e)
		{
			
		} catch (IOException e)
		{
			
		}
	}

	public int getBalance()
	{
		return balance;
	}
	
	public boolean inputMoney(int id)
	{
		BufferedReader moneyReader = null;
		BufferedWriter moneyWriter = null;
		try
		{
			StringTokenizer st;
			moneyReader = new BufferedReader(new FileReader("./manager/money.txt"));
			String txtMoney = "";
			
			for(int i = 0; i < 5;i++)
			{
				st = new StringTokenizer(moneyReader.readLine());
				String count = st.nextToken();
				if(id == i)
				{
					int tmpCount = Integer.parseInt(count);
					tmpCount++;
					count = Integer.toString(tmpCount);
				}
				txtMoney += count + "\n";
			}
			moneyReader.close();
			
			
			moneyWriter = new BufferedWriter(new FileWriter("./manager/money.txt"));
			moneyWriter.write(txtMoney);
			
			moneyWriter.close();
		} catch (FileNotFoundException e)
		{
			
		} catch (IOException e)
		{
			
		}
		
		input.set(id, input.get(id) + 1);
		switch (id)
		{
		case 0:
			balance += 10;
			break;
		case 1:
			balance += 50;
			break;
		case 2:
			balance += 100;
			break;
		case 3:
			balance += 500;
			break;
		case 4:
			balance += 1000;
			break;
		}
		
		if(id == 4)
		{
			if(input.get(id) == 5)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void useMoney(int price)
	{
		
		BufferedReader moneyReader = null;
		BufferedWriter moneyWriter = null;
		try
		{
			StringTokenizer st;
			moneyReader = new BufferedReader(new FileReader("./manager/money.txt"));
			String txtMoney = "";
			
			for(int i = 0; i < 5;i++)
			{
				st = new StringTokenizer(moneyReader.readLine());
				String count = st.nextToken();
				
				tmp.add(Integer.parseInt(count));
			}
			moneyReader.close();
			
			if(price / 1000 > 0)
			{
				if(tmp.get(4) > price / 1000)
				{
					tmp.set(4, tmp.get(4) - price / 1000);
					balance -= price / 1000 * 1000;
					price %= 1000;
				}
				else
				{
					balance -= tmp.get(4) * 1000;
					tmp.set(4, 0);
				}
			}
			if(price / 500 > 0)
			{
				if(tmp.get(3) > price / 500)
				{
					tmp.set(3, tmp.get(3) - price / 500);
					balance -= price / 500 * 500;
					price %= 500;
				}
				else
				{
					balance -= tmp.get(3) * 500;
					tmp.set(3, 0);
				}
			}
			if(price / 100 > 0)
			{
				if(tmp.get(2) > price / 100)
				{
					tmp.set(2, tmp.get(2) - price / 100);
					balance -= price / 100 * 100;
					price %= 100;
				}
				else
				{
					balance -= tmp.get(2) * 100;
					tmp.set(2, 0);
				}
			}
			if(price / 50 > 0)
			{
				if(tmp.get(1) > price / 50)
				{
					tmp.set(1, tmp.get(1) - price / 50);
					balance -= price / 50 * 50;
					price %= 50;
				}
				else
				{
					balance -= tmp.get(1) * 50;
					tmp.set(1, 0);
				}
			}
			if(price / 10 > 0)
			{
				if(tmp.get(0) > price / 10)
				{
					tmp.set(0, tmp.get(0) - price / 10);
					balance -= price / 10 * 10;
					price %= 10;	
				}
				else
				{
					balance -= tmp.get(0) * 10;
					tmp.set(0, 0);
				}
			}
			
			for(int i = 0; i < 5; i++)
			{
				txtMoney += tmp.get(i).toString() + "\n";
			}
			
			moneyWriter = new BufferedWriter(new FileWriter("./manager/money.txt"));
			moneyWriter.write(txtMoney);
			
			moneyWriter.close();
		} catch (FileNotFoundException e)
		{
			
		} catch (IOException e)
		{
			
		}
	}
	
	public void returnMoney()
	{
		BufferedReader moneyReader = null;
		try
		{
			StringTokenizer st;
			moneyReader = new BufferedReader(new FileReader("./manager/money.txt"));
			
			for(int i = 0; i < 5;i++)
			{
				st = new StringTokenizer(moneyReader.readLine());
				String count = st.nextToken();
				
				money.set(i, Integer.parseInt(count));
			}
			moneyReader.close();
		}
		catch (IOException e)
		{
			
		}
		
		while(balance != 0)
		{
			//
			if(money.get(4) >= balance / 1000)
			{
				money.set(4, money.get(4) - balance / 1000);
				balance %= 1000;
			}
			else if(money.get(3) >= balance / 500)
			{
				money.set(3, money.get(3) - balance / 500);
				balance %= 500;
			}
			else if(money.get(2) >= balance / 100)
			{
				money.set(2, money.get(2) - balance / 100);
				balance %= 100;
			}
			else if(money.get(1) >= balance / 50)
			{
				money.set(1, money.get(1) - balance / 50);
				balance %= 50;
			}
			else if(money.get(0) >= balance / 10)
			{
				money.set(0, money.get(0) - balance / 10);
				balance %= 10;
			}
		}

		BufferedWriter moneyWriter = null;
		String txtMoney = "";
		for(int i = 0; i < 5; i++)
		{
			txtMoney += money.get(i).toString() + "\n";
		}
		
		try
		{
			moneyWriter = new BufferedWriter(new FileWriter("./manager/money.txt"));
			moneyWriter.write(txtMoney);
			moneyWriter.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
