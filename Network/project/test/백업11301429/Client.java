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
	String ip = "192.168.0.74"; int port = 5555; //접속하는 게임서버 주소
	MWHandler mwh;

	// 서버저장정보컬렉션, 여기도있어야한다. cc >> 속에 닉네임,스코어,색깔
	LinkedHashMap<String,ClientCharacter> clientCList = new LinkedHashMap<String,ClientCharacter>();
	String nickName ="GUEST";int score = 0;
	ClientCharacter cc;
	
	ImageIcon[] color = new ImageIcon[4];
	
	
	OutputStream os; DataOutputStream dos;	// 소켓에 전달하는 정보
	InputStream is; DataInputStream dis;	// 소켓에서 받는 정보
	
	

	Client(MWHandler mwh){	//MWH에서 로그인버튼 누르면 >>
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
	// 버튼 이벤트처리와 관련된 메소드
	void sendNickName(){	//로그인화면의 로그인버튼클릭 밑 엔터 #002
		try{
			dos.writeUTF("#002"+nickName);	//입장메세지 #002
			dos.flush();
		}catch(IOException ie){}
	}
	void sendMsg(){	//게임 시작전 텍스트필드 #001
		try{
			dos.writeUTF("#001"+nickName+">> "+mwh.msg);	//일반메세지 #001
			dos.flush();
		}catch(IOException ie){
		}
	}
	void readyMsg(){	//게임 화면 레디버튼 #003 레디메세지
		try{
			dos.writeUTF("#003"+1);
			dos.flush();
		}catch(IOException ie){
		}
	}
	void noreadyMsg(){	//레디버튼  레디 풀었을때.#004
      try{
         dos.writeUTF("#004"+-1);
         dos.flush();
      }catch(IOException ie){}
    }
	void changeMode(){	//게임 시작전 서버 모드 변경 #005
		try{
			dos.writeUTF("#005");
			dos.flush();
		}catch(IOException ie){}
	}
	void checkAnswer(){		//게임이 시작했을 때, 텍스트 필드 게임메세지 #006
		mwh.mw.tShowChat.append("내차례야\n");
		//서버에 저장된 정답리스트와 입력된 메세지를 비교한다.
		//정답이면 score++, turnOutMsg();
		//정답이 아니면 turnOutMsg();
		mwh.mw.tInputChat.setText("");
	}
	void giveTurn(){	//게임 중 5초초과, 정답맞췄을 때. #007
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
	void exitMsg(){		//게임 화면 나가기버튼, 이건 ioexception에서 처리할수도 있다. #005 퇴장메세지
		
	}
	//

	//스레드#2 >> 
	public void run(){
		acceptInfo();
	}
	
	int turn = 0;		//턴 순서.
	void acceptInfo(){	//OCM에서 정보전달받는 메소드 >> 받은 정보를 화면에 출력.
		String temp = null;String proto = null;String msgfromS = null;
		MainWindow mw = mwh.mw; //클래스로 뺄 때 조심해야함.
		int cNumber = 0;	//접속자 수==클라이언트사이즈 
		 
		try{
			while(true){
				temp = dis.readUTF();
				proto = temp.substring(0,4);
				msgfromS = temp.substring(4);
				//여기서부터 프로토콜 처리 
				if(proto.equals("@002")){	//@002 접속자 수
					cNumber = Integer.parseInt(msgfromS);
				}
				
				if(proto.equals("@003")){	//@003 입장했을 때, 클라 컬렉션에 저장
					String i = msgfromS.substring(0,1);
					int index = Integer.parseInt(i);
					String n = msgfromS.substring(1);
					pln(i+" "+n);
					if(!clientCList.containsKey(n)){
						clientCList.put(n, new ClientCharacter(n,index,score,color[index]));
					}
					//System.out.println(clientCList.size());
				}
				if(proto.equals("@004")){	//@004 접속한 캐릭터 표시
					//System.out.println(clientCList.get(nickName).index+" "+cNumber);
					if(clientCList.get(nickName).index == cNumber){
						Set<String> keys = clientCList.keySet();
						for(String key: keys) mw.pC.add(clientCList.get(key));
					}else if(clientCList.get(nickName).index < cNumber){
						mw.pC.add(clientCList.get(msgfromS));
					}
					mw.revalidate(); mw.repaint();
				}
				if(proto.equals("@005")){	//@005 접속하는 캐릭터 표시
					mw.tShowChat.append(msgfromS+"님꼐서 게임서버에 접속\n");
				}
				
				if(proto.equals("@006")){	//@006 레디메세지
					mw.tShowChat.append(msgfromS+"님께서 레디\n");
				}
				if(proto.equals("@007")){	//@007 레디메세지
					mw.tShowChat.append(msgfromS+"님께서 노레디\n");
				}
				if(proto.equals("@008")){	//@008 게임시작메세지
					mw.tShowChat.append(msgfromS+"\n");
					try{
						Thread.sleep(3000);
						mw.tShowChat.append("시작\n");
						GameMode.gameMode = GameMode.NOTMYTURN;
						changeMode();
						this.new GameTimer().start();	//#3 스레드
					}catch(InterruptedException ite){}
				}
				if(proto.equals("@009")){
					turn = Integer.parseInt(msgfromS);
					
					if(clientCList.get(nickName).index == turn){
						GameMode.gameMode = GameMode.MYTURN;
					}else{
						GameMode.gameMode = GameMode.NOTMYTURN;
					}
					System.out.println(clientCList.get(nickName).index+"인덱스 "+turn+"턴 "+GameMode.gameMode+"모드");
					
				}
				if(proto.equals("@001")){	//@001 표시할 메세지
					pln(msgfromS);
					mw.tShowChat.append(msgfromS+"\n");	
				}
				//여기까지
			}
		}catch(IOException ie){
		}finally{
			closeAll();
		}
	}
	class GameTimer extends Thread{	//클라의 메소드를 이용하기위해 내부클로
		public void run(){
			cc = clientCList.get(nickName);
			int index = cc.index;	//무조건 자기 인덱스
			pln(index+"자기인덱스"+turn+"현재턴");
			int timeInit = 30; int timeTurn = 5;			//총시간, 턴시간
			
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
					mwh.mw.laTime.setText(timeInit+""); //라벨에 표시
					mwh.mw.laSec.setText((timeTurn-1)+"");
					timeInit -= 1; timeTurn -= 1;
					if(timeTurn == 1){
						//System.out.println(turn+"턴"+GameMode.gameMode+"모드");
						timeTurn = 5;
						if(index == turn){
							giveTurn();
						}
						//System.out.println("#");
						
					}
					if(timeInit <= 0){
						mwh.mw.tShowChat.append("타임아웃\n");
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
	void closeAll(){ //연결객체들 닫기
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
