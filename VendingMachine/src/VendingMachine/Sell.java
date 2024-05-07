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
	
	public boolean sellItem(int id)
	{
		if(items.get(id).count > 0)
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
					if(id == i)
					{
						int tmpCount = Integer.parseInt(count);
						if(items.get(id).soldout == false)
						{
							tmpCount--;
							money.useMoney(items.get(id).price);
						}
						if(tmpCount == 0)
							items.get(id).soldout = true;
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
		
		return items.get(id).soldout;
	}
}
