package VendingMachine;

import java.io.*;
import java.util.*;

import GUI.Machine;

public class Sell implements Runnable
{
	VendingMachine vendingMachine;
	public Vector<Item> items = new Vector<Item>();
	Vector<Item> updateItems = new Vector<Item>();
	Machine machine;
	Admin admin;
	public Money money;
	
	String originName, newName;
	String newPrice;
	
	public Sell(VendingMachine vendingMachine)
	{
		this.vendingMachine = vendingMachine;
		money = new Money();
		admin = new Admin(vendingMachine, this);
		
		
		Thread adminThread = new Thread(admin);
		adminThread.start();
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		machine = new Machine(this, admin);
	}

	public void addItems(Item newItem)
	{
		newItem.soldout = false;
		items.add(newItem);
	}
	
	public int getItemPrice(String name)
	{
		int value = 0;
		for(int i = 0; i < 6; i++)
		{
			if(items.get(i).getName().equals(name))
				value = items.get(i).getPrice();
		}
		return value;
	}
	
	public void setOriginName(String origin)
	{
		originName = origin;
	}
	
	public void setNewName(String newName)
	{
		this.newName = newName;
	}
	
	public void changeItemName()
	{
		admin.changeItemName(originName, newName);
	}
	
	public void setNewPrice(String newPrice)
	{
		this.newPrice = newPrice;
	}	
	
	public void changeItemPrice()
	{
		admin.changeItemPrice(originName, newPrice);
	}
	
	public boolean sellItem(String value)
	{
		if(findItem(value).count > 0)
		{
			try
			{
				String txtItemInfo = "";
				BufferedReader itemReader = new BufferedReader(new FileReader("./manager/beverage.txt"));
				StringTokenizer st;
				
				for(int i = 0; i < 6; i++)
				{
					st = new StringTokenizer(itemReader.readLine());
					String name = st.nextToken();
					String count = st.nextToken();
					String price = st.nextToken();
					if(value.equals(name))
					{
						int tmpCount = Integer.parseInt(count);
						if(findItem(value).soldout == false)
						{
							tmpCount--;
							money.useMoney(findItem(value).price);
						}
						if(tmpCount == 0)
							findItem(value).soldout = true;
						count = Integer.toString(tmpCount);
					}
					txtItemInfo += name + "\t" + count + "\t" + price + "\n";
				}
				itemReader.close();
				
				BufferedWriter itemWriter = new BufferedWriter(new FileWriter("./manager/beverage.txt"));
				itemWriter.write(txtItemInfo);
				itemWriter.close();
				
			} catch (FileNotFoundException e)
			{
				
			} catch (IOException e)
			{
				
			}
		}
		
		return findItem(value).soldout;
	}
	
	Item findItem(String name)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getName().equals(name))
				return items.get(i);
		}
		return null;
	}
}
