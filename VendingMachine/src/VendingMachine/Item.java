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
	
	public void supplemnet(int supple)
	{
		count += supple;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public int getPrice()
	{
		return price;
	}
}