package four.quiz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;

class Game extends JFrame{
	QBack qback;
	Container c;
	JPanel pN,pS,pC;
	JPanel pN1,pN2;
	JLabel laName, laScore, laT;
	JButton bRe, bPass, bExit;
	JButton bSB,bM;
	Clip clip;
	ImageIcon normalIcon3 = new ImageIcon("img\\sound.png");
	ImageIcon selectedIcon = new ImageIcon("img\\mute.png");
	
	//JButton qB[] = new JButton[20];
	Vector<JButton> vqB = new Vector<JButton>();
	
	final int TIME_START = 15;
	int timeI = 2000; String timeS;
	int scoreI; String scoreS;
	String nameG;

	Color bgc = new Color(59, 89, 182);
	Color pbgc = new Color(237,239,236);
	Color txtc = new Color(45,48,54);

	//JButton text;
	Game(String nameG, QBack qback){	//컴포넌트 컨테이너 생성
		this.qback = qback;
		this.nameG = nameG;
		c = getContentPane();
		pN = new JPanel(new GridLayout(1,2));
		
		pN1 = new JPanel(new GridLayout(1,2));
		laName = new JLabel("<html>Player<br>"+nameG+"</html>");
		scoreI = 0;
		scoreS = Integer.toString(scoreI);
		laScore = new JLabel("<html>Score<br>"+scoreS+"</html>");
		
		pN2 = new JPanel(new GridLayout(1,3));
		bM = new JButton(null,normalIcon3);
		ImageIcon normalIcon4 = new ImageIcon("img\\board.png");
		bSB = new JButton(null,normalIcon4);
		
		//timeI = 15;
		//timeS = Integer.toString(timeI); 
		laT = new JLabel();

		pC = new JPanel(new CardLayout());
		makeButton(); // 문제버튼 얹기

		pS = new JPanel(new BorderLayout());
		ImageIcon normalIcon1 = new ImageIcon("img\\Restart.png");
		bRe = new JButton(null,normalIcon1);
		bPass = new JButton("Pass");
		ImageIcon normalIcon2 = new ImageIcon("img\\exit.png");
		bExit = new JButton(null,normalIcon2);
		
		pN.add(pN1);
		pN.add(pN2);
		pN1.add(laName);pN1.add(laScore);
		pN2.add(laT);
		pN2.add(bM);
		pN2.add(bSB);
		
		pS.add(bRe, BorderLayout.WEST);
		pS.add(bExit, BorderLayout.EAST);
		pS.add(bPass);

		c.add(pN, BorderLayout.NORTH);
		c.add(pS, BorderLayout.SOUTH);
		c.add(pC);
		
		designC();
		listenerSet();
		setUI();
		makeMusic();
		new IntTime().start();
	}
	
	void makeButton(){ //#1 문제버튼 만들기(현재 배열로 햇는데, 컬렉션으로 바꿔야할듯)
		
		int size = qback.filteredList.size();
		Random r = new Random();
		Vector<Integer> v = new Vector<Integer>();
		aa:
		for(int i=0;i<size;i++){
			int j = r.nextInt(size);
			for(int k=0;k<v.size();k++){
				if(v.get(k) == j){
					i-=1; continue aa;
				}
			}
			v.add(j);
			//System.out.println(v.get(i));
		}
		for(int i=0;i<size;i++){
			JButton b = new JButton();
			b.setBackground(bgc);
			b.setForeground(Color.WHITE);
			b.setFocusPainted(false);
			b.setFont(new Font("굴림체", Font.BOLD, 50));
			
			int j = v.get(i);
			String quiz = qback.filteredList.get(j);
			b.setText(quiz);
			pC.add(b);
			vqB.add(b);			
			//System.out.println(vqB.get(i));
		}
	}
		
		

			/*qB[i] = new JButton();
			qB[i].setBackground(bgc);
			qB[i].setForeground(Color.WHITE);
			qB[i].setFocusPainted(false);
			qB[i].setFont(new Font("굴림체", Font.BOLD, 20));
			pC.add(qB[i]);*/
	
	void designC(){	//여기서 컴포넌트 꾸며주세요~~
		pN.setBackground(bgc);

		pN1.setBackground(pbgc);
		Border oBN = laName.getBorder();
		Border eBorder = new EmptyBorder(20, 10, 20, 10);
		CompoundBorder cBN = new CompoundBorder(oBN, eBorder);
		laName.setBorder(cBN);
		laName.setFont(new Font("Tahoma", Font.BOLD, 15));
		laName.setBackground(pbgc);
		laName.setForeground(txtc);
		laName.setOpaque(true);
		Border oBS = laScore.getBorder();
		CompoundBorder cBS = new CompoundBorder(oBS, eBorder);
		laScore.setBorder(cBS);
		laScore.setFont(new Font("Tahoma", Font.BOLD, 15));
		laScore.setBackground(pbgc);
		laScore.setForeground(txtc);
		laScore.setOpaque(true);
		
		pN2.setBackground(pbgc);
		laT.setFont(new Font("Tahoma", Font.BOLD, 25));
		laT.setHorizontalAlignment(JLabel.CENTER);
		bM.setBackground(pbgc);
		bM.setBorderPainted(false);//외곽선 제거
		bM.setFocusPainted(false);//클릭시 테두리제거
		bM.setOpaque(true);//투명
		bSB.setFont(new Font("Tahoma", Font.BOLD, 15));
		bSB.setBackground(pbgc);
		bSB.setForeground(txtc);
		bSB.setBorderPainted(false);
		bSB.setFocusPainted(false);
		bSB.setOpaque(true);

		pC.setBackground(bgc);
		
		bRe.setBackground(pbgc);
		bPass.setFont(new Font("Tahoma", Font.BOLD, 30));
		bPass.setBackground(pbgc);
		bPass.setForeground(txtc);
		bExit.setBackground(pbgc);
	}
	
	void listenerSet(){
		ActionListener gameH = new GameHandler(this,qback);
		
		bSB.addActionListener(gameH);
		bM.addActionListener(gameH);
		bPass.addActionListener(gameH);
		bRe.addActionListener(gameH);
		bExit.addActionListener(gameH);
		for(JButton item: vqB) item.addActionListener(gameH);
		/*for(int i=0;i<qB.length;i++){
			qB[i].addActionListener(gameH);
		}*/
	}
	void recordScore(){
		File dir = new File("txtFile");
		if(!dir.exists()) dir.mkdir();
		File scoreFile = new File(dir,"score.txt");
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(scoreFile, true);
			pw = new PrintWriter(fw, true);
			pw.print("  "+nameG+":  "+scoreS+"\n");
		}catch(IOException ie){
		}finally{
			try{
				if(pw!=null) pw.close();
				if(fw!=null) fw.close();
			}catch(IOException ie){}
		}
	}
	class IntTime extends Thread{
		public void run(){
			try{			
				Thread.sleep(1000);
				laT.setText("3");
				Thread.sleep(1000);
				laT.setText("2");
				Thread.sleep(1000);
				laT.setText("1");
				Thread.sleep(1000);
				laT.setText("START!");
				Thread.sleep(1000);
				timeI = TIME_START;
				timeS = Integer.toString(TIME_START);
				laT.setText(timeS);
			}catch(InterruptedException iie){}
			clip.start();
			while(true){
				try{
					Thread.sleep(1000);
					timeI -= 1;
					timeS = Integer.toString(timeI);
					//System.out.println(timeS);
					if(timeI == 10) laT.setFont(new Font("Tahoma", Font.BOLD, 45));
					laT.setText(timeS);
					if(timeI == 0) break;
				}catch(InterruptedException iie){
				}
			}
			//text = new JButton("Time Out");
			laT.setFont(new Font("Tahoma", Font.BOLD, 15));
			laT.setText("Time Over");
			validate();
			clip.stop();
			//System.out.println("저장");
			qback.saveFilteredFile(); //지워진 문제리스트를 필터파일에 다시 저장한다.
			recordScore(); // 이름과 스코어를 "txtFile/score.txt"에 저장한다
		}
	}
	void makeMusic(){
		try{
			File bgm = new File("music/bgm.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
			clip = AudioSystem.getClip();
			clip.open(ais);		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
	void setUI(){
		setTitle("PLAY");
		setSize(600, 600);
		setVisible(true);
		//setLocation(350, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	public static void main(String[] args) {

		//new Game("신동오");
	}
}
