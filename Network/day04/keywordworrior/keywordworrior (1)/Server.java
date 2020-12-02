import java.net.*;
import java.io.*;
import java.util.*;

class Server{
	ServerSocket ss; int port = 5555; //게임서버와 포트정보
	Socket s;

	LinkedHashMap<String,OneClientModule> clientMap = new LinkedHashMap<String,OneClientModule>();
	OneClientModule ocm; String nickName;
	//문제정답지; 파일읽어서 컬렉션에 저장

	Server(){
		
		try{
			ss = new ServerSocket(port);
			pln("서버 대기중.....");
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
