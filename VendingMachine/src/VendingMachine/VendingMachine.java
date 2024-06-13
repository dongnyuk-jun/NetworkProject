package VendingMachine;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.StringTokenizer;

public class VendingMachine
{
	int id;				//자판기 식별번호
	Sell sell;
	
	Socket socket;
	OutputStream out;
	FileInputStream fin;
	
	public static void main(String [] args) throws IOException
	{
		File path = new File("./manager");
		if(!path.exists() && !path.isDirectory())
			path.mkdir();
		
		new VendingMachine("127.0.0.1", 1345);
	}
	
	
	
	VendingMachine(String address, int port) throws IOException
	{
		read();
		sendFiles();
		sell = new Sell(this);
		Thread sellThread = new Thread(sell);
		sellThread.start();
	}
	
	public void sendFiles() {
		while (true)
		{
			try
			{
				Socket socket = new Socket("127.0.0.1", 1345); // 서버에 연결
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				// ID 전송
				String id = "-1"; // 예시 ID
				BufferedReader readerPassword = null;
				
				try
				{
					readerPassword = new BufferedReader(new FileReader("./manager/password.txt"));
					String bf;
					
					try
					{
						id = readerPassword.readLine();
					}
					catch(IOException e)
					{
						System.out.println(123);
					}
				}
				catch (IOException e)
				{
					
				}
				out.writeUTF(id);
				
				File file1 = new File("./manager/password.txt");
				File file2 = new File("./manager/money.txt");
				File file3 = new File("./manager/beverage.txt");
				File file4 = new File("./manager/AllRecord.txt");
				// 파일 전송
				/*
				if(!file1.exists())
				if(!file2.exists())
				if(!file3.exists())
				if(!file4.exists())
				*/

				sendFile(out, file1);
				sendFile(out, file2);
				sendFile(out, file3);
				sendFile(out, file4);
				

				out.close();
				socket.close();
				break;
			}
			catch(UnknownHostException e)
			{
				System.err.println("호스트를 찾을 수 없음...");
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
			catch (IOException e)
			{
				System.err.println("서버에 연결할 수 없음...");
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	private void sendFile(DataOutputStream out, File file) throws IOException
	{
		FileInputStream fin = new FileInputStream(file);
		long fileSize = file.length();

		out.writeUTF(file.getName());
		out.writeLong(fileSize);

		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = fin.read(buffer)) != -1)
		{
			out.write(buffer, 0, bytesRead);
		}
		fin.close();
	}
	
	void read()
	{
		BufferedReader readerPassword = null;
		String password;
		String [] basicName = {"물", "커피", "이온음료", "고급커피", "탄산음료", "특화음료" };
		int [] basicPrice = {450, 500, 550, 700, 750, 800};
		
		try
		{
			readerPassword = new BufferedReader(new FileReader("./manager/password.txt"));
			String bf;
			
			try {
				bf = readerPassword.readLine();
				id = Integer.parseInt(bf);
				bf = readerPassword.readLine();
				password = bf;
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			BufferedWriter bw1, bw2;
			try
			{
				Random random = new Random();
				random.setSeed(System.currentTimeMillis());
				bw1 = new BufferedWriter(new FileWriter("./manager/password.txt"));
				id = random.nextInt();
				password = "12345678!";
				bw1.write(Integer.toString(id) + "\n");
				bw1.write(password);
				bw1.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try
			{
				bw2 = new BufferedWriter(new FileWriter("./manager/money.txt"));
				bw2.write("10\n10\n10\n10\n10\n");
				bw2.close();
			}
			catch (IOException e1)
			{
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
		
		BufferedReader readerRecord = null;
		try
		{
			readerBeverage = new BufferedReader(new FileReader("./manager/AllRecord.txt"));
		}
		catch (FileNotFoundException e)
		{
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("./manager/AllRecord.txt"));
				bw.write("");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

