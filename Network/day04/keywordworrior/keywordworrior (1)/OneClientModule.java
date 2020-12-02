import java.net.*;
import java.io.*;
import java.util.*;

class OneClientModule extends Thread{
	Server server;
	Socket s;
	InputStream is; DataInputStream dis;	//���Ͽ��� �޴� ����
	OutputStream os; DataOutputStream dos;	//���Ͽ� �����ϴ� ����
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
			server.pln(server.nickName+"�Բ��� ���Ӽ����� �����Ͽ����ϴ�");
			while(true){
				msg = dis.readUTF();
				//��ɾ� ��庯�� ��������!!
				broadcast();	//�Ϲݸ޼��� #001
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
