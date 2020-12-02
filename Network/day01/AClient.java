import java.io.*;
import java.net.*;

class AClient extends Thread{
	Socket s;
	String ip ="192.168.0.23";
	int port =2000;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	PrintStream ps = System.out;

	AClient(){
		try{
			s= new Socket(ip, port);
			pln("서버연결 성공");
			
			readyIO();
			start();
			speak();
		}catch(UnknownHostException ne){
		}catch(IOException ie){
			pln("서버("+ip+")를 네트워크에서 찾을 수 없음");
		}
	}
	void readyIO(){
		try{
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			is = s.getInputStream();
			dis = new DataInputStream(is);
		}catch(IOException ie){
		}
	}
	void speak(){
		try{
			String line = "";
			while ((line =br.readLine()) != null){
				dos.writeUTF(line);
				dos.flush();
			}
		}catch(IOException ie){
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void listen(){
		try{
			String line = "";
			while((line=dis.readUTF()) !=null){
				ps.println("Client>>"+line);
			}
		}catch(IOException ie){
		}
	}
	public void run(){
		listen();
	}
	public static void main(String[] args) 
	{
		new AClient();
	}
}
