import java.net.*;
import java.io.*;

class Client extends Thread{
	Socket s; 
	String ip = "192.168.0.129"; int port = 5555; //접속하는 게임서버 주소
	MWHandler mwh;
	String nickName;
	OutputStream os; DataOutputStream dos;	// 소켓에 전달하는 정보
	InputStream is; DataInputStream dis;	// 소켓에서 받는 정보

	Client(MWHandler mwh){	//MWH에서 로그인버튼 누르면 >>
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
	void sendMsg(){	//MWH의 겜화면 텍스트필드 엔터>>
		try{
			dos.writeUTF(nickName+">> "+mwh.msg);
			dos.flush();
		}catch(IOException ie){}
	}
	

	public void run(){
		acceptInfo();
	}
	String msgfromS;
	void acceptInfo(){	//MW 게임화면 텍스트패인에 출력 >>
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
