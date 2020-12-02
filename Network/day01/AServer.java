import java.io.*;
import java.net.*;

class  AServer extends Thread{
	ServerSocket ss;
	Socket s;
	int port = 2000;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	PrintStream ps = System.out;

	AServer(){
		try{
			ss=new ServerSocket(port);
			pln("서버가"+port+"번 포트에서 대기중");
			s=ss.accept();
			pln("Client("+s.getInetAddress().getHostAddress()+")연결성공");
			
			readyIO();
			start();
			listen();
		}catch(IOException ie){
			pln("ie:"+ie);
		}
	}
	public void run(){ //쓰레드에 있는 메소드를 오버라이딩 (할때 제한자 같아야함 =쓰레드 클래스는 퍼블릭)
		speak();
	}
	void readyIO(){
		try{
			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
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
	public static void main(String[] args) 
	{
		new AServer();
	}
}
