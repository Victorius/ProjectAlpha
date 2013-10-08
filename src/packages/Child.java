package packages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Child {
	private Socket socket = null;
	
	public Child(){
		
	}
	
	public boolean findMatka(){
		try{
			socket= new Socket("192.168.1.3",12345);
		}catch(IOException e){
			return false;
		}
		return true;		
	}
	
	public void getFoodFromMatka(){
		try{
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(),true);
			out.println("Hello,Matka");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void TalkWithMatka(){
		BufferedReader bf2=null;
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
			bf2 = new BufferedReader(new InputStreamReader(System.in));
			boolean flag=true;
			while(flag){
				String answerFromServer = new String(getanswer(bf));
				System.out.println(answerFromServer);
				String answerFromClient = bf2.readLine();
				if(answerFromClient.equals("-1") || answerFromClient.equals("0"))
					flag = false;
				toServer.println(answerFromClient);
			}
		}catch(IOException e){
			System.out.println("Connection is aborted. Do you want try again? Yes - 1, No - 0.");			
			try {
				if(Integer.parseInt(bf2.readLine())==1)
					TalkWithMePlease();				
			} catch (NumberFormatException e1) {
				System.out.println("Wrong format of message. Sorry.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void TalkWithMePlease(){
		if(this.findMatka())
			this.TalkWithMatka();
		else
			System.out.println("");
	}
	public char[] getanswer(BufferedReader bf) throws IOException{
		char[] result=null;
		ArrayList<Character> ch = new ArrayList<Character>();
		char c;
		while((c=(char) bf.read())!=13){
			ch.add(c);
		}
		result = new char[ch.size()];
		for(int i=0;i<ch.size();i++)
			result[i]=ch.get(i);
		return result;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Child ch = new Child();
		boolean flag = true;
		do{
			System.out.println("Do you want to connect to Matka? Yes - 1, No - 0");
			String a = bf.readLine();
			if(a.equals("1")){
				ch.TalkWithMePlease();				
			}
			else
				flag = false;
			
		}while(flag);
	}
}
