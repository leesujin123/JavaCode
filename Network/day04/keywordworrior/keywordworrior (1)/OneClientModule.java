import java.net.*;
import java.io.*;
import java.util.*;

class OneClientModule extends Thread{
	Server server;
	Socket s;
	InputStream is; DataInputStream dis;	//소켓에서 받는 정보
	OutputStream os; DataOutputStream dos;	//소켓에 전달하는 정보
	String msg;

	OneClientModule(Server server){
		this.server = server;
		this.s = server.s;
		connect();
	}
	void connect(){
		try{
			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
	}

	public void run(){
		acceptData();
	}

	void acceptData(){
		try{
			server.nickName = dis.readUTF();
			server.pln(server.nickName+"님께서 게임서버에 접속하였습니다");
			while(true){
				msg = dis.readUTF();
				//명령어 모드변경 프로토콜!!
				broadcast();	//일반메세지 #001
			}
		}catch(IOException ie){
		}
	}

	void broadcast(){
		Set<String> keys = server.clientMap.keySet();
		Iterator<String> iter = keys.iterator();
		try{
			while( iter.hasNext()){
				String key = iter.next();
				OneClientModule ocm = server.clientMap.get(key);
				ocm.dos.writeUTF(msg);
				ocm.dos.flush();
			}
			server.pln(msg);
		}catch(IOException io){}
	}
	
}
