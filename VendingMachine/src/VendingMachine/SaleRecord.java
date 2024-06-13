package VendingMachine;

import java.io.*;
import java.util.*;
import java.time.*;

public class SaleRecord
{
	Vector<Item> items = new Vector<Item>();
	Vector<Integer> selling = new Vector<Integer>();
	Vector<allRecord> all = new Vector<allRecord>();
	String text = new String();

	public SaleRecord(Vector<Item> items)
	{
		this.items = items;
		for(int i = 0; i < 6; i++)
		{
			selling.add(0);
		}
	}
	
	public void recordSell(int index)
	{
		selling.set(index, selling.get(index) + 1);
		
		boolean a = true;
		for(int i = 0; i < all.size(); i++)
		{
			if(all.get(i).date.equals(LocalDate.now().toString()))
			{
				if(i % 6 == index)
					all.get(i).setPrice(all.get(i).getPrice() + items.get(i % 6).getPrice());
				a = false;
			}
		}
		if(a)
		{
			for(int i = 0; i < 6; i++)
				all.add(new allRecord(LocalDate.now().toString(), items.get(i).getName(), (selling.get(i) * items.get(i).getPrice())));
		}
		saveRecord();
	}
	
	public void saveRecord()
	{
		LocalDate now = LocalDate.now();
		String text = new String();
		for(int i = 0; i < 6; i++)
		{
			text += now + "\t" + items.get(i).getName() + "\t" + items.get(i).price * selling.get(i) + "\n";
		}
		
		BufferedWriter itemWriter = null;
		try
		{
			itemWriter = new BufferedWriter(new FileWriter("./manager/record.txt"));
			itemWriter.write(text);
			itemWriter.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveAllRecord();
	}
	
	public void readAllRecord()
	{
		LocalDate now = LocalDate.now();
		StringTokenizer st;
		String line;
		int i = 0;
		try
		{
			BufferedReader itemReader = new BufferedReader(new FileReader("./manager/AllRecord.txt"));
			while((line = itemReader.readLine()) != null)
			{				
				st = new StringTokenizer(line);
				String date = st.nextToken();
				String name = st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				all.add(new allRecord(date, name, price));
				
				if(date.equals(now.toString()))
				{
					selling.set(i, price / items.get(i++).getPrice());
					saveRecord();
				}
			}
		}
		catch (IOException | NoSuchElementException e)
		{
			
		}
	}
	
	public void saveAllRecord()
	{
		text = "";
		
		for(int i = 0; i < all.size(); i++)
		{
			text += all.get(i).date + "\t" + all.get(i).name + "\t" + all.get(i).price + "\n";
		}
		
		try
		{
			BufferedWriter itemWriter = new BufferedWriter(new FileWriter("./manager/AllRecord.txt"));
			itemWriter.write(text);
			itemWriter.close();
		}
		catch (IOException | NoSuchElementException e)
		{
			
		}
	}
}

class allRecord
{
	String date;
	String name;
	int price;
	
	public allRecord(String date, String name, int price)
	{
		this.date = date;
		this.name = name;
		this.price = price;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public String getName()
	{
		return name;
	}
	public int getPrice()
	{
		return price;
	}
}
