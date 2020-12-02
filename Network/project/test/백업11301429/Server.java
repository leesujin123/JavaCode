import java.net.*;
import java.io.*;
import java.util.*;

class Server{
	ServerSocket ss; int port = 5555; //���Ӽ����� ��Ʈ����
	Socket s;
	

	LinkedHashMap<String,OneClientModule> clientMap = new LinkedHashMap<String,OneClientModule>();
	OneClientModule ocm; String nickName;
	
	LinkedHashMap<String,ClientCharacter> clientCList = new LinkedHashMap<String,ClientCharacter>();
	int score; ClientCharacter cc;//Ű���� nickName, ���ӿ��� ��� Ŭ�� �����ϴ� ���� >> ������ �����. ocm�� �ٸ���ü.
	int readyN = 0;
	int turn =0;	// �ϰ��� Ʈ����
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
