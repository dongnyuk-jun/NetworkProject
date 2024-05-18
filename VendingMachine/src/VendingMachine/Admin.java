package VendingMachine;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class Admin implements Runnable
{
	int id;
	private String password;
	String [] basicName = {"물", "커피", "이온음료", "고급커피", "탄산음료", "특화음료" };
	int [] basicPrice = {450, 500, 550, 700, 750, 800};
	VendingMachine vendingMachine;
	Sell sell;
	
	public Admin(VendingMachine vendingMachine, Sell sell)  
	{
		this.vendingMachine = vendingMachine;
		this.sell = sell;
	}
	
	
	public String getPassword()
	{
		return password;
	}

	
	public void changePassword(String newPassword)  
	{
		BufferedWriter bw = null;

		password = newPassword;
		try
		{
			bw = new BufferedWriter(new FileWriter("./manager/password.txt"));
			bw.write(Integer.toString(id) + "\n");
			bw.write(password);
			bw.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void run()  
	{
		// TODO Auto-generated method stub
		BufferedReader readerPassword = null;
		
		try
		{
			readerPassword = new BufferedReader(new FileReader("./manager/password.txt"));
			String bf;
			
			try {
				bf = readerPassword.readLine();
				id = Integer.parseInt(bf);
				bf = readerPassword.readLine();
				password = bf;
				System.out.println(id);
				System.out.println(password);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			BufferedWriter bw;
			try
			{
				Random random = new Random();
				random.setSeed(System.currentTimeMillis());
				bw = new BufferedWriter(new FileWriter("./manager/password.txt"));
				id = random.nextInt();
				password = "12345678!";
				bw.write(Integer.toString(id) + "\n");
				bw.write(password);
				bw.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		BufferedReader readerBeverage = null;
		try
		{
			StringTokenizer st;
			readerBeverage = new BufferedReader(new FileReader("./manager/beverage.txt"));
			for(int i = 0; i < 6; i++)
			{
					st = new StringTokenizer(readerBeverage.readLine());
					String name = st.nextToken();
					int count = Integer.parseInt(st.nextToken());
					int price = Integer.parseInt(st.nextToken());
					
					Item item = new Item(name, count, price);
					sell.addItems(item);
			}
			try
			{
				readerBeverage.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		catch (FileNotFoundException e)
		{
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("./manager/beverage.txt"));
				for(int i = 0; i < 6; i++)
				{
					bw.write(basicName[i] + "\t10\t" + basicPrice[i] + "\n");
				}
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try
			{
				bw.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (IOException e)
		{
			
		}
	}
}
