package su.ui.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.border.*;

class B extends JFrame {
	Container cp;
	ImageIcon ii;
	JLabel laImg;
	JLabel laFont;
	B(){
		laImg = new JLabel();
		laFont = new JLabel();
	}
	void init(){
		loadImg();
		setLalmg(); //순서상관없지만 가독성 add보다 먼저
		laImg.setIcon(ii);
		cp = getContentPane();
		cp.add(laImg);
		
		laFont.setText("정말 지구를 뿌실만큼 귀엽네요!!!!");
		cp.add(laFont,BorderLayout.NORTH);

	    setUI();
	}
	void setLalmg(){
		//(1)아이콘 셋팅
		laImg.setIcon(ii);
		//(2) 폰트셋팅
		laFont.setFont(new java.awt.Font("맑은고딕",Font.BOLD,16));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		//left , center, righgt (대문자임)
		laFont.setVerticalAlignment(JLabel.BOTTOM);
		//top , center, bottom (대문자임)
		//(3)보더셋팅
		Border oborder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10); //EmptyBorder 반시계방향  위 왼 아래 오
		CompoundBorder cBorder = new CompoundBorder(oborder,eBorder);
		laFont.setBorder(cBorder);
	}
	void loadImg(){
	/*방법1  try{
			File f = new File("imgs/브루니.gif");
			BufferedImage bi = ImageIO.read(f);
			ii = new ImageIcon(bi);
		}catch(IOException ie){
			pln("이미지 없음 ") //이미지가 없으면 없다고 말해줄수 있어서 좋음 
		}*/
		//방법 2
		ii = new ImageIcon(getClass().getResource("imgs/브루니.gif"));
	}
	void setUI(){
		setTitle("JFileChooser Test");
		pack();
		setVisible(true);
		//setLocation(300, 100);
		setLocationRelativeTo(null); //창 그냥 정중앙에 띄어줘
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new B().init();
	}
}
