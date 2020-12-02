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
			pln("������"+port+"�� ��Ʈ���� �����");
			s=ss.accept();
			pln("Client("+s.getInetAddress().getHostAddress()+")���Ἲ��");
			
			readyIO();
			start();
			listen();
		}catch(IOException ie){
			pln("ie:"+ie);
		}
	}
	public void run(){ //�����忡 �ִ� �޼ҵ带 �������̵� (�Ҷ� ������ ���ƾ��� =������ Ŭ������ �ۺ�)
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
