package four.quiz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class MS extends JFrame   {
	JButton b, btt;
	Container cp;
	JLabel laFont;
	JTextField tfName;
	String nameG;
	ImageIcon reset;
	JPanel jp, j;

	MS(){
		laFont = new JLabel();
		tfName = new JTextField(20);
		init();
	}
	void init(){
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		j = new JPanel(new FlowLayout());
		j.add(laFont);
		j.add(tfName);
		cp.add(j);
		jp = new JPanel(new BorderLayout());
		cp.add(jp,BorderLayout.SOUTH);
		b = new JButton("Game Start");
		j.add(b);
		laFont.setFont(new Font("맑은고딕",Font.BOLD,16));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		laFont.setVerticalAlignment(JLabel.CENTER);
		Border oBorder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10);
		laFont.setBorder(oBorder);
		laFont.setBorder(eBorder);
		laFont.setText("Enter your name");
		
		reset = new ImageIcon("img\\reset.png");
		JToolBar bar =new JToolBar();
		btt = new JButton(null,reset);
		btt.setBorderPainted(false);//외각선 없애주기
		btt.setContentAreaFilled(false);//내용영역채우기 없애주기
		btt.setFocusPainted(false); //눌러도 테두리 없는거
		btt.setToolTipText("문제를 다시 셋팅합니다.");

		bar.add(btt);
		jp.add(btt,BorderLayout.EAST);//여기까지가 tool나오게하기
		
		
		//이벤트 처리
		ActionListener msH = new MSHandler(this);
		b.addActionListener(msH);
		btt.addActionListener(msH);
		tfName.addActionListener(msH);

	    setUI();
	}
	
	void setUI(){
		setTitle("이름(팀) 입력창");
		pack();
		setSize(280,200);
		setVisible(true);
		//setLocation(200,100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String [] args) {
		new MS();
	}
}