package VendingMachine;

public class Item
{
	String name;
	int price;
	int count;
	boolean soldout = false;
	
	public Item(String name, int count, int price)
	{
		this.name = name;
		this.price = price;
		this.count = count;
		
		if(count == 0)
			soldout = true;
	}
	
	public void supplemet(int supple)
	{
		count += supple;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public int getPrice()
	{
		return price;
	}
}