package VendingMachine;

import java.io.*;
import java.net.*;

public class VendingMachine
{
	int id;				//���Ǳ� �ĺ���ȣ
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
				socket = new Socket(address, port);		//������ ����
			
				out = socket.getOutputStream();
				fin = new FileInputStream("./manager/beverage.txt");
				
				byte [] buffer = new byte[4096];
				int bytesRead;
				while((bytesRead = fin.read(buffer)) != -1)
				{
					out.write(buffer, 0, bytesRead);
				}
				System.out.println("���� �Ϸ�");
				sell = new Sell(this);
				Thread sellThread = new Thread(sell);
				sellThread.start();
				break;
			}
			catch (UnknownHostException e)
			{
				System.err.println("ȣ��Ʈ�� ã�� �� ����...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (IOException e)
			{
				System.err.println("������ ������ �� ����...");
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
