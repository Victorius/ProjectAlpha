package packages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Matka {
	private ServerSocket  server= null;
	private Socket sock = null;
	private int waitrate = 0;
	
	public Matka(){
		if(server==null)
			try{
				server = new ServerSocket(12345);
			}catch(IOException e){
				System.out.println("Error on creating a new ServerSocket");
				e.printStackTrace();
			}
	}
	
	public boolean b0rNot2b(){
		while(true)
			try{
				System.out.println("Waiting for client...");
				if(server!=null)
					sock = server.accept();
				System.out.println("Connected");
				while(processing(sock.getOutputStream(), sock.getInputStream(),0));
			}catch(IOException e){
				System.out.println("Connection is failed");
				return false;
			}
	}
	
	public void giveAFoodGetAnswer(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String messageFromClient = in.readLine();
			System.out.println(messageFromClient);
		}catch(IOException e){}
	}
	/**
	 * 
	 * @param out
	 * @param in
	 * 1 - Show the next message in console
	 * 2 - Multiply the next two digits, Answer result to client
	 * 3 - Echo to client
	 * -1 - abort connection with client
	 * @return boolean
	 */
	public boolean processing(OutputStream out, InputStream in,int operation){
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			PrintWriter toClient= new PrintWriter(out,true);
			toClient.println("Hello,Dude!\n" +
					"Choose one menu item and send me number of this operation:\n" +
					"1 - Show next message on server\n" +
					"2 - Multiply two numbers and  recceive result of this operation\n" +
					"3 - Ping server\n" +
					"-1 or 0 - abort connectin with server.\n" +
					"Thank you dude. :)");
			Integer first_command = Integer.parseInt(bf.readLine());
			switch(first_command){
				case 1:
					System.out.println(bf.readLine());
					toClient.println("Message is writen");
					break;
				case 2:
					toClient.println("Please, input first number to multipling");
					Integer f = Integer.parseInt(bf.readLine());
					toClient.println("Please, input second number to multipling");
					Integer s = Integer.parseInt(bf.readLine());
					toClient.println("Result of multipling is "+ new Integer(f*s));
					break;
				case 3:
					toClient.println("ping me,Baby");
					break;
				case -1:
				case 0:
					sock.close();
					return false;
				default:
					throw new NumberFormatException("Wrong choise,Baby!");
			}
		}catch(IOException e){
			return false;
		}catch(NumberFormatException e){
			PrintWriter toClient= new PrintWriter(out,true);
			toClient.println("Wrong format of message. Try again.");
			waitrate++;
			return processing(out, in,0);
		}
		return true;
		
	}
	public static void main(String[] args){
		Matka matka = new Matka();
		System.out.println("Server starts");
		matka.b0rNot2b();
		
	}
	
	
}
