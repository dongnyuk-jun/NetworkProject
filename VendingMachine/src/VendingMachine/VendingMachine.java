package VendingMachine;

import java.io.*;
import java.net.*;

public class VendingMachine
{
	int id;				//자판기 식별번호
	Sell sell;
	
	Socket socket;
	OutputStream out;
	FileInputStream fin;
	
	public static void main(String [] args) throws IOException
	{
		//1
		new VendingMachine("127.0.0.1", 1345);
	}
	
	VendingMachine(String address, int port) throws IOException
	{
		while(true)
		{
			try
			{
				socket = new Socket(address, port);		//서버에 연결
			
				out = socket.getOutputStream();
				fin = new FileInputStream("./manager/beverage.txt");
				
				byte [] buffer = new byte[4096];
				int bytesRead;
				while((bytesRead = fin.read(buffer)) != -1)
				{
					out.write(buffer, 0, bytesRead);
				}
				System.out.println("전송 완료");
				sell = new Sell(this);
				Thread sellThread = new Thread(sell);
				sellThread.start();
				break;
			}
			catch (UnknownHostException e)
			{
				System.err.println("호스트를 찾을 수 없음...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (IOException e)
			{
				System.err.println("서버에 연결할 수 없음...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			finally
			{
				
			}
		}
	}
}
