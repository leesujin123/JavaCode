import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

class Game extends JFrame{
	Container c;
	JPanel pN,pS,pC;
	JPanel pN1,pN2;
	JLabel laName, laScore, laT;
	JButton bRe, bPass, bExit;
	JButton bSB, bCC;
	JButton qB[] = new JButton[20];
	
	int timeI; String timeS;
	int scoreI; String scoreS;

	Color bgc = new Color(59, 89, 182);
	Color pbgc = new Color(237,239,236);
	Color txtc = new Color(45,48,54);

	//JButton text;
	Game(String nameG){	//컴포넌트 컨테이너 생성
		c = getContentPane();
		pN = new JPanel(new GridLayout(1,2));
		
		pN1 = new JPanel(new GridLayout(1,2));
		laName = new JLabel("<html>Player<br>"+nameG+"</html>");
		scoreI = 0;
		scoreS = Integer.toString(scoreI);
		laScore = new JLabel("<html>Score<br>"+scoreS+"</html>");
		
		pN2 = new JPanel(new GridLayout(1,2));
		bSB = new JButton("Board");
		
		timeI = 15;
		timeS = Integer.toString(timeI); 
		laT = new JLabel(timeS);

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
		new IntTime().start();
	}
	void makeButton(){ //#1 문제버튼 만들기(현재 배열로 햇는데, 컬렉션으로 바꿔야할듯)
		
		for(int i=0;i<qB.length;i++){
			qB[i] = new JButton(Integer.toString(i));
			qB[i].setBackground(bgc);
			qB[i].setForeground(Color.WHITE);
			qB[i].setFocusPainted(false);
			qB[i].setFont(new Font("굴림체", Font.BOLD, 20));
			pC.add(qB[i]);
		}
	}
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
		bSB.setFont(new Font("Tahoma", Font.BOLD, 15));
		bSB.setBackground(pbgc);
		bSB.setForeground(txtc);

		pC.setBackground(bgc);
		
		bRe.setBackground(pbgc);
		bPass.setFont(new Font("Tahoma", Font.BOLD, 30));
		bPass.setBackground(pbgc);
		bPass.setForeground(txtc);
		bExit.setBackground(pbgc);
	}
	
	void listenerSet(){
		ActionListener gameH = new GameHandler(this);
		
		bSB.addActionListener(gameH);
		bPass.addActionListener(gameH);
		bRe.addActionListener(gameH);
		bExit.addActionListener(gameH);
		for(int i=0;i<qB.length;i++){
			qB[i].addActionListener(gameH);
		}
	}

	class IntTime extends Thread{
		public void run(){
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
			laT.setFont(new Font("Tahoma", Font.BOLD, 20));
			laT.setText("Time Out");
			validate();
		}
	}
	void setUI(){
		setTitle("PLAY");
		setSize(400, 400);
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
