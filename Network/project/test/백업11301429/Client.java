import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


class Client extends Thread{
	Socket s; 
	String ip = "192.168.0.74"; int port = 5555; //�����ϴ� ���Ӽ��� �ּ�
	MWHandler mwh;

	// �������������÷���, ���⵵�־���Ѵ�. cc >> �ӿ� �г���,���ھ�,����
	LinkedHashMap<String,ClientCharacter> clientCList = new LinkedHashMap<String,ClientCharacter>();
	String nickName ="GUEST";int score = 0;
	ClientCharacter cc;
	
	ImageIcon[] color = new ImageIcon[4];
	
	
	OutputStream os; DataOutputStream dos;	// ���Ͽ� �����ϴ� ����
	InputStream is; DataInputStream dis;	// ���Ͽ��� �޴� ����
	
	

	Client(MWHandler mwh){	//MWH���� �α��ι�ư ������ >>
		this.mwh = mwh;
		this.nickName = mwh.nickN;
		color[0] = new ImageIcon("img/redhead1.png");color[1] = new ImageIcon("img/bluehead1.png");
		color[2] = new ImageIcon("img/greenhead1.png");color[3] = new ImageIcon("img/pinkhead1.png");
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
	// ��ư �̺�Ʈó���� ���õ� �޼ҵ�
	void sendNickName(){	//�α���ȭ���� �α��ι�ưŬ�� �� ���� #002
		try{
			dos.writeUTF("#002"+nickName);	//����޼��� #002
			dos.flush();
		}catch(IOException ie){}
	}
	void sendMsg(){	//���� ������ �ؽ�Ʈ�ʵ� #001
		try{
			dos.writeUTF("#001"+nickName+">> "+mwh.msg);	//�Ϲݸ޼��� #001
			dos.flush();
		}catch(IOException ie){
		}
	}
	void readyMsg(){	//���� ȭ�� �����ư #003 ����޼���
		try{
			dos.writeUTF("#003"+1);
			dos.flush();
		}catch(IOException ie){
		}
	}
	void noreadyMsg(){	//�����ư  ���� Ǯ������.#004
      try{
         dos.writeUTF("#004"+-1);
         dos.flush();
      }catch(IOException ie){}
    }
	void changeMode(){	//���� ������ ���� ��� ���� #005
		try{
			dos.writeUTF("#005");
			dos.flush();
		}catch(IOException ie){}
	}
	void checkAnswer(){		//������ �������� ��, �ؽ�Ʈ �ʵ� ���Ӹ޼��� #006
		mwh.mw.tShowChat.append("�����ʾ�\n");
		//������ ����� ���丮��Ʈ�� �Էµ� �޼����� ���Ѵ�.
		//�����̸� score++, turnOutMsg();
		//������ �ƴϸ� turnOutMsg();
		mwh.mw.tInputChat.setText("");
	}
	void giveTurn(){	//���� �� 5���ʰ�, ��������� ��. #007
		try{
			dos.writeUTF("#007");
			dos.flush();
		}catch(IOException ie){}
		
	}
	
	void sendExitMsg(){
		try{
			dos.writeUTF("#008");
			dos.flush();
		}catch(IOException ie){}
	}
	void exitMsg(){		//���� ȭ�� �������ư, �̰� ioexception���� ó���Ҽ��� �ִ�. #005 ����޼���
		
	}
	//

	//������#2 >> 
	public void run(){
		acceptInfo();
	}
	
	int turn = 0;		//�� ����.
	void acceptInfo(){	//OCM���� �������޹޴� �޼ҵ� >> ���� ������ ȭ�鿡 ���.
		String temp = null;String proto = null;String msgfromS = null;
		MainWindow mw = mwh.mw; //Ŭ������ �� �� �����ؾ���.
		int cNumber = 0;	//������ ��==Ŭ���̾�Ʈ������ 
		 
		try{
			while(true){
				temp = dis.readUTF();
				proto = temp.substring(0,4);
				msgfromS = temp.substring(4);
				//���⼭���� �������� ó�� 
				if(proto.equals("@002")){	//@002 ������ ��
					cNumber = Integer.parseInt(msgfromS);
				}
				
				if(proto.equals("@003")){	//@003 �������� ��, Ŭ�� �÷��ǿ� ����
					String i = msgfromS.substring(0,1);
					int index = Integer.parseInt(i);
					String n = msgfromS.substring(1);
					pln(i+" "+n);
					if(!clientCList.containsKey(n)){
						clientCList.put(n, new ClientCharacter(n,index,score,color[index]));
					}
					//System.out.println(clientCList.size());
				}
				if(proto.equals("@004")){	//@004 ������ ĳ���� ǥ��
					//System.out.println(clientCList.get(nickName).index+" "+cNumber);
					if(clientCList.get(nickName).index == cNumber){
						Set<String> keys = clientCList.keySet();
						for(String key: keys) mw.pC.add(clientCList.get(key));
					}else if(clientCList.get(nickName).index < cNumber){
						mw.pC.add(clientCList.get(msgfromS));
					}
					mw.revalidate(); mw.repaint();
				}
				if(proto.equals("@005")){	//@005 �����ϴ� ĳ���� ǥ��
					mw.tShowChat.append(msgfromS+"�Բ��� ���Ӽ����� ����\n");
				}
				
				if(proto.equals("@006")){	//@006 ����޼���
					mw.tShowChat.append(msgfromS+"�Բ��� ����\n");
				}
				if(proto.equals("@007")){	//@007 ����޼���
					mw.tShowChat.append(msgfromS+"�Բ��� �뷹��\n");
				}
				if(proto.equals("@008")){	//@008 ���ӽ��۸޼���
					mw.tShowChat.append(msgfromS+"\n");
					try{
						Thread.sleep(3000);
						mw.tShowChat.append("����\n");
						GameMode.gameMode = GameMode.NOTMYTURN;
						changeMode();
						this.new GameTimer().start();	//#3 ������
					}catch(InterruptedException ite){}
				}
				if(proto.equals("@009")){
					turn = Integer.parseInt(msgfromS);
					
					if(clientCList.get(nickName).index == turn){
						GameMode.gameMode = GameMode.MYTURN;
					}else{
						GameMode.gameMode = GameMode.NOTMYTURN;
					}
					System.out.println(clientCList.get(nickName).index+"�ε��� "+turn+"�� "+GameMode.gameMode+"���");
					
				}
				if(proto.equals("@001")){	//@001 ǥ���� �޼���
					pln(msgfromS);
					mw.tShowChat.append(msgfromS+"\n");	
				}
				//�������
			}
		}catch(IOException ie){
		}finally{
			closeAll();
		}
	}
	class GameTimer extends Thread{	//Ŭ���� �޼ҵ带 �̿��ϱ����� ����Ŭ��
		public void run(){
			cc = clientCList.get(nickName);
			int index = cc.index;	//������ �ڱ� �ε���
			pln(index+"�ڱ��ε���"+turn+"������");
			int timeInit = 30; int timeTurn = 5;			//�ѽð�, �Ͻð�
			
			if(index == turn) GameMode.gameMode = GameMode.MYTURN;
			
			while(true){
				
				if(index == turn){
					cc.laName.setForeground(Color.RED);
				}else{
					cc.laName.setForeground(Color.WHITE);
				}
				mwh.mw.revalidate();mwh.mw.repaint();
				try{
					
					Thread.sleep(1000);
					mwh.mw.laTime.setText(timeInit+""); //�󺧿� ǥ��
					mwh.mw.laSec.setText((timeTurn-1)+"");
					timeInit -= 1; timeTurn -= 1;
					if(timeTurn == 1){
						//System.out.println(turn+"��"+GameMode.gameMode+"���");
						timeTurn = 5;
						if(index == turn){
							giveTurn();
						}
						//System.out.println("#");
						
					}
					if(timeInit <= 0){
						mwh.mw.tShowChat.append("Ÿ�Ӿƿ�\n");
						break;
					}
					
				}catch(InterruptedException ite){}
			}
			mwh.readymode = 0;
			GameMode.gameMode = GameMode.BEFORE;
			turn = 0;
			sendExitMsg();
			timeInit =30; mwh.mw.laTime.setText(timeInit+"");
			timeTurn = 5; mwh.mw.laSec.setText((timeTurn-1)+"");
			cc.laName.setForeground(Color.WHITE);
			pln(GameMode.gameMode+" ");
			
		}
	}

	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	void closeAll(){ //���ᰴü�� �ݱ�
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){
		}
	}
	public static void main(String[] args){
		//new Client();
	}
}
