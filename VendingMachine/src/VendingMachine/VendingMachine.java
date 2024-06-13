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
		new VendingMachine("127.0.0.1", 1345);
	}
	
	
	
	VendingMachine(String address, int port) throws IOException
	{
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

				// 파일 전송
				sendFile(out, "./manager/password.txt");
				sendFile(out, "./manager/money.txt");
				sendFile(out, "./manager/beverage.txt");
				sendFile(out, "./manager/AllRecord.txt");

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

	private void sendFile(DataOutputStream out, String filePath) throws IOException
	{
		File file = new File(filePath);
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
}

