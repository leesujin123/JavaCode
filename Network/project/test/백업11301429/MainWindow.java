import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class MainWindow extends JFrame {
	Color clear = new Color(255, 0, 0, 0);
	Font maple = new Font("메이플스토리", Font.BOLD, 100);
	Font maple2 = new Font("메이플스토리", Font.BOLD, 50);
	Font maple3 = new Font("메이플스토리", Font.BOLD, 20);
	

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
	ImageIcon start,exit;
	void loginComp(){
		p1 = new JPanel();p1.setBackground(clear);
		lTitle = new JLabel("<html><br>키  워  드    워  리  어</html>");
		lTitle.setFont(maple); lTitle.setForeground(Color.WHITE);
		Border oBorder = lTitle.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,0);
		lTitle.setBorder(oBorder); lTitle.setBorder(eBorder);
		
		p2 = new JPanel();p2.setBackground(clear);
		lName = new JLabel("<html><br><br>What is your name?</html>");
		lName.setFont(maple2);lName.setForeground(Color.WHITE);
		
		p3 = new JPanel();p3.setBackground(clear);
		tfName = new JTextField(40);

		start = new ImageIcon("img/start.png");
		exit = new ImageIcon("img/exit.png");
		p4 = new JPanel(); p4.setBackground(clear); p4.setOpaque(false);
		bStart = new JButton(start);
		bStart.setBorderPainted(false);
		bStart.setContentAreaFilled(false);
		bStart.setFocusPainted(false);
		bStart.setOpaque(false);
		bOut = new JButton(exit);
		bOut.setBorderPainted(false);
		bOut.setContentAreaFilled(false);
		bOut.setFocusPainted(false);
		bOut.setOpaque(false);

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
	JPanel pC1,pC,pC6,pTopicAnswer;
	JLabel laPers,laTime,laSec; //상단 라벨
	JLabel laTopic,laAnswer;	//2번째 창 라벨
	JLabel laName1,laName2,laName3,laName4; // 3번째 창 라벨
	JLabel laBlank1,laBlank2;	// 3번째 창 양 옆 공백
	JButton bReady,bExit; // 상단 버튼
	JTextArea tShowChat; JTextField tInputChat; //하단 채팅창
	JScrollPane sP;
	ImageIcon ready,exit1;
	ImageIcon tOpic,aNswer;

	void gameComp(){
		pg1 = new JPanel(); pg2=new JPanel();pg3 = new JPanel();pg4 = new JPanel(new GridLayout(2,1));
		
		pC1 = new JPanel();
		pC = new JPanel(new GridLayout(1,4));
		pC6 = new JPanel();
		
		laPers = new JLabel("<html>Player&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laPers.setFont(maple2); laPers.setForeground(Color.WHITE); 
		laTime = new JLabel("<html>Total time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laTime.setFont(maple2); laTime.setForeground(Color.WHITE); 
		laSec = new JLabel("<html>Input time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
		laSec.setFont(maple2); laSec.setForeground(Color.WHITE); 

		tOpic = new ImageIcon("img/topicbar1.png");
		aNswer = new ImageIcon("img/chatbox1.png");

		pTopicAnswer = new JPanel(new GridLayout(2,1));
		laTopic = new JLabel(tOpic); laAnswer = new JLabel(aNswer); laBlank1 = new JLabel("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ"); laBlank2 = new JLabel("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		laBlank1.setFont(maple3); laBlank1.setVisible(false); laBlank2.setFont(maple3); laBlank2.setVisible(false);
		laTopic.setFont(maple3); laTopic.setForeground(Color.WHITE); laTopic.setHorizontalAlignment(JLabel.CENTER);
		laAnswer.setFont(maple3); laAnswer.setForeground(Color.WHITE); laAnswer.setHorizontalAlignment(JLabel.CENTER);

		ImageIcon ready = new ImageIcon("img/ready1.png");
		ImageIcon exit1 = new ImageIcon("img/exit1.png");
		bReady = new JButton(ready); bReady.setBorderPainted(false); bReady.setBackground(clear);
		bReady.setContentAreaFilled(false); bReady.setFocusPainted(false); bReady.setOpaque(false);
		bExit = new JButton(exit1); bExit.setBorderPainted(false); bExit.setBackground(clear);
		bExit.setContentAreaFilled(false); bExit.setFocusPainted(false); bExit.setOpaque(false);

		tShowChat = new JTextArea(); tInputChat = new JTextField(20);
		tShowChat.setEditable(false);
		sP = new JScrollPane(tShowChat);
		sP.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar src = (JScrollBar) e.getSource();
                src.setValue(src.getMaximum());
            }     
        });

		pg4.add(sP);

		pg1.add(laPers);pg1.add(laTime);pg1.add(laSec);pg1.add(bReady);	pg1.add(bExit);
		pg1.setBackground(clear); pg1.setOpaque(false);

		pg2.setLayout(new BorderLayout());
		pTopicAnswer.add(laTopic,BorderLayout.NORTH);pTopicAnswer.add(laAnswer,BorderLayout.CENTER); pTopicAnswer.setBackground(clear);
		pg2.add(laBlank1,BorderLayout.WEST);pg2.add(pTopicAnswer,BorderLayout.CENTER); pg2.add(laBlank2,BorderLayout.EAST); pg2.setBackground(clear);
		pg3.setLayout(new BorderLayout());
		pC1.setBackground(clear);pC.setBackground(clear);pC6.setBackground(clear);
		pg3.add(pC1,BorderLayout.WEST);pg3.add(pC);pg3.add(pC6,BorderLayout.EAST); pg3.setBackground(clear);

		//pg4.add(tShowChat);	
		pg4.add(tInputChat); pg4.setBackground(clear);
		pBG.setLayout(new GridLayout(4,1));
		pg1.setLayout(new FlowLayout(FlowLayout.LEFT,55,0));
		pBG.add(pg1);pBG.add(pg2);pBG.add(pg3);pBG.add(pg4);
		
		makeBGPanel();
		cp.add(pBG);
		revalidate();repaint();

		tInputChat.addActionListener(mwH);
		bExit.addActionListener(mwH);
		bReady.addActionListener(mwH);
	}



	void setUI(){
		setTitle("PLAY"); 
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setSize(1000,1000);
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

