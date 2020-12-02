import java.net.*;
import java.io.*;

class Client extends Thread{
	Socket s; 
	String ip = "192.168.0.129"; int port = 5555; //�����ϴ� ���Ӽ��� �ּ�
	MWHandler mwh;
	String nickName;
	OutputStream os; DataOutputStream dos;	// ���Ͽ� �����ϴ� ����
	InputStream is; DataInputStream dis;	// ���Ͽ��� �޴� ����

	Client(MWHandler mwh){	//MWH���� �α��ι�ư ������ >>
		this.mwh = mwh;
		this.nickName = mwh.nickN;
		connectToS();
		
	}
	
	void connectToS(){	
		try{
			s = new Socket(ip, port);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			is = s.getInputStream();
			dis = new DataInputStream(is);
			sendNickName();
			start();
		}catch(UnknownHostException ue){
		}catch(IOException ie){}
	}
	
	void sendNickName(){
		try{
			dos.writeUTF(nickName);
			dos.flush();
		}catch(IOException ie){}
	}
	void sendMsg(){	//MWH�� ��ȭ�� �ؽ�Ʈ�ʵ� ����>>
		try{
			dos.writeUTF(nickName+">> "+mwh.msg);
			dos.flush();
		}catch(IOException ie){}
	}
	

	public void run(){
		acceptInfo();
	}
	String msgfromS;
	void acceptInfo(){	//MW ����ȭ�� �ؽ�Ʈ���ο� ��� >>
		try{
			while(true){
				msgfromS = dis.readUTF();
				mwh.mw.tShowChat.append(msgfromS+"\n");	
			}
		}catch(IOException ie){}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
		
	public static void main(String[] args){
		//new Client();
	}
}
