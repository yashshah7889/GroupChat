package chatting.application;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {

	private Socket socket;
	
	public static Vector client = new Vector();
	
	public Server(Socket s) {
		try{
			System.out.println("New Client Got Connected  " );
			this.socket=s;
		}catch(Exception e) {}
	}
	
	@Override
	public void run() {
		try{
			BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			client.add(writer);
			
			while(true) {
				String data=reader.readLine().trim();
				
				for(int i=0;i<=client.size();i++) {
					try {
						BufferedWriter bw= (BufferedWriter) client.get(i);
						bw.write(data);
						bw.write("\r\n");
						bw.flush();
					}catch (Exception e) {}
				}
			}
		}catch(Exception e) {}
		
	}
	public static void main(String ars[]) throws Exception {
		ServerSocket ss= new ServerSocket(3002);
		while(true) {
			Socket s= ss.accept();
			Server server= new Server(s); 
			Thread thread= new Thread(server);
			thread.start();
		}
	}
}
