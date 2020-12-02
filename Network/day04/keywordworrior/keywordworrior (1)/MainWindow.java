import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class MainWindow extends JFrame {
	Color clear = new Color(255, 0, 0, 0);
	Client client;	//MWH에서 만들어준 클라객체를 이용하기위해서 멤버로 만듦.

	MainWindow(){
		cp = getContentPane();
		makeBGPanel();
		loginComp();
		setUI();
	}
	void makeBGPanel(){	// 배경화면 설정 >> pBG패널 위에 모든것 추가하세요~~~
		ImageIcon ii = new ImageIcon("img/bg.png");
		pBG = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(ii.getImage(), 0, 0, null);
				setOpaque(false);
				super.paint(g);
				}
		};
	}
	// 로그인화면 컴포넌트
	Container cp;
	JButton bStart, bOut;	
	JLabel lName, lTitle;
	JPanel pBG, p1, p2, p3, p4;
	JTextField tfName;
	ActionListener mwH;
	void loginComp(){
		p1 = new JPanel();p1.setBackground(clear);
		lTitle = new JLabel("<html><br>키보드 워리어</html>");
		lTitle.setFont(new Font("맑은고딕",Font.BOLD,70));
		Border oBorder = lTitle.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,0);
		lTitle.setBorder(oBorder); lTitle.setBorder(eBorder);
		
		p2 = new JPanel();p2.setBackground(clear);
		lName = new JLabel("<html><br><br>What your name?</html>");
		lName.setFont(new Font("맑은고딕",Font.BOLD,25));
		
		p3 = new JPanel();p3.setBackground(clear);
		tfName = new JTextField(40);

		p4 = new JPanel();p4.setBackground(clear);
		bStart = new JButton("             Game Start           ");
		bOut = new JButton("              Exit             ");

		p1.add(lTitle);p2.add(lName);p3.add(tfName);p4.add(bStart);p4.add(bOut);	

		pBG.setLayout(new GridLayout(4, 1));
		
		pBG.add(p1); pBG.add(p2); pBG.add(p3); pBG.add(p4); 
		cp.add(pBG);

		mwH = new MWHandler(this);
		tfName.addActionListener(mwH);
		bStart.addActionListener(mwH);
		bOut.addActionListener(mwH);
		
		
	}
	//

	void removeComp(){
		pBG.remove(p1);	pBG.remove(p2);	pBG.remove(p3);	pBG.remove(p4);
		revalidate();
		repaint();
	}
	// 게임화면 컴포넌트
	JPanel pg1,pg2,pg3,pg4;
	JPanel pC1,pC2,pC3,pC4,pC5,pC6,pTopicAnswer;
	JLabel laPers,laTime,laSec; //상단 라벨
	JLabel laTopic,laAnswer;	//2번째 창 라벨
	JLabel laName1,laName2,laName3,laName4; // 3번째 창 라벨
	JLabel laBlank1,laBlank2;	// 3번째 창 양 옆 공백
	JButton bReady,bExit; // 상단 버튼
	JTextArea tShowChat; JTextField tInputChat; //하단 채팅창
	JScrollPane sP;

	void gameComp(){
		pg1 = new JPanel(); pg2=new JPanel();pg3 = new JPanel();pg4 = new JPanel(new GridLayout(2,1));
		
		ImageIcon red = new ImageIcon("img/redhead1.png");ImageIcon blue = new ImageIcon("img/bluehead1.png");
		ImageIcon green = new ImageIcon("img/greenhead1.png");ImageIcon pink = new ImageIcon("img/pinkhead1.png");
		pC1 = new JPanel();
		pC2 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(red.getImage(), 0, 0, null);
				setOpaque(false);
				super.paint(g);
				}
		};
		pC3 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(blue.getImage(), 0, 0, null);
				setOpaque(false);
				super.paint(g);
				}
		};
		pC4 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(green.getImage(), 0, 0, null);
				setOpaque(false);
				super.paint(g);
				}
		};
		pC5 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(pink.getImage(), 0, 0, null);
				setOpaque(false);
				super.paint(g);
				}
		};
		pC6 = new JPanel();
		pC2.setVisible(true);pC3.setVisible(false);pC4.setVisible(false);pC5.setVisible(false);

		laName1 = new JLabel("권연주      점수:00"); laName1.setFont(new Font("굴림체",Font.BOLD, 20)); laName1.setForeground(Color.WHITE);
		laName2 = new JLabel("신동오         점수:00"); laName2.setFont(new Font("굴림체",Font.BOLD, 20)); laName2.setForeground(Color.WHITE);
		laName3 = new JLabel("이민용        점수:00"); laName3.setFont(new Font("굴림체",Font.BOLD, 20)); laName3.setForeground(Color.WHITE);
		laName4 = new JLabel("이수진        점수:00"); laName4.setFont(new Font("굴림체",Font.BOLD, 20)); laName4.setForeground(Color.WHITE);
		pC2.add(laName1); pC3.add(laName2); pC4.add(laName3); pC5.add(laName4);

		laPers = new JLabel("<html>Player&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laPers.setFont(new Font("맑은고딕",Font.BOLD,50)); laPers.setForeground(Color.WHITE); 
		laTime = new JLabel("<html>Total time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laTime.setFont(new Font("맑은고딕",Font.BOLD,50)); laTime.setForeground(Color.WHITE); 
		laSec = new JLabel("<html>Input time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laSec.setFont(new Font("맑은고딕",Font.BOLD,50)); laSec.setForeground(Color.WHITE); 

		pTopicAnswer = new JPanel(new BorderLayout());
		laTopic = new JLabel("자바"); laAnswer = new JLabel("클래스"); laBlank1 = new JLabel("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ"); laBlank2 = new JLabel("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		laBlank1.setFont(new Font("굴림체",Font.BOLD, 20)); laBlank2.setFont(new Font("굴림체",Font.BOLD, 20));
		laTopic.setFont(new Font("굴림체",Font.BOLD, 20)); laTopic.setForeground(Color.WHITE); laTopic.setHorizontalAlignment(JLabel.CENTER);
		laAnswer.setFont(new Font("굴림체",Font.BOLD, 20)); laAnswer.setForeground(Color.WHITE); laAnswer.setHorizontalAlignment(JLabel.CENTER);

		bReady = new JButton("             READY           ");
		bReady.setPreferredSize(new Dimension(170, 55));
		bExit = new JButton("              Exit             ");
		bExit.setPreferredSize(new Dimension(170, 55));

		tShowChat = new JTextArea(); tInputChat = new JTextField(20);
		tShowChat.setEditable(false);
		sP = new JScrollPane(tShowChat);
		pg4.add(sP);

		pg1.add(laPers);pg1.add(laTime);pg1.add(laSec);pg1.add(bReady);	pg1.add(bExit);
		pg1.setBackground(clear);

		pg2.setLayout(new BorderLayout());
		pTopicAnswer.add(laTopic,BorderLayout.NORTH);pTopicAnswer.add(laAnswer,BorderLayout.CENTER); pTopicAnswer.setBackground(clear);
		pg2.add(laBlank1,BorderLayout.WEST);pg2.add(pTopicAnswer,BorderLayout.CENTER); pg2.add(laBlank2,BorderLayout.EAST); pg2.setBackground(clear);
		pg3.setLayout(new GridLayout(1,6));
		pC1.setBackground(clear);pC6.setBackground(clear);
		pg3.add(pC1);pg3.add(pC2);pg3.add(pC3);	pg3.add(pC4); pg3.add(pC5); pg3.add(pC6); pg3.setBackground(clear);

		//pg4.add(tShowChat);
		pg4.add(tInputChat); pg4.setBackground(clear);
		pBG.setLayout(new GridLayout(4,1));
		pg1.setLayout(new FlowLayout(FlowLayout.LEFT,55,0));
		pBG.add(pg1);pBG.add(pg2);pBG.add(pg3);pBG.add(pg4);
		
		makeBGPanel();
		cp.add(pBG);
		revalidate();

		tInputChat.addActionListener(mwH);
		
	}


	void setUI(){
		setTitle("PLAY"); 
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	
	}
	public static void main(String[] args) 
	{
		new MainWindow();
	}
}

