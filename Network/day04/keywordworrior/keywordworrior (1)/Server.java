import java.net.*;
import java.io.*;
import java.util.*;

class Server{
	ServerSocket ss; int port = 5555; //���Ӽ����� ��Ʈ����
	Socket s;

	LinkedHashMap<String,OneClientModule> clientMap = new LinkedHashMap<String,OneClientModule>();
	OneClientModule ocm; String nickName;
	//����������; �����о �÷��ǿ� ����

	Server(){
		
		try{
			ss = new ServerSocket(port);
			pln("���� �����.....");
			while(true){
				s = ss.accept();
				ocm = new OneClientModule(this);
				ocm.start();
				clientMap.put(nickName,ocm);
				
			}
		}catch(IOException ie){}		
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) 
	{
		new Server();
	}
}
