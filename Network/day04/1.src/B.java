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
		setLalmg(); //������������� ������ add���� ����
		laImg.setIcon(ii);
		cp = getContentPane();
		cp.add(laImg);
		
		laFont.setText("���� ������ �ѽǸ�ŭ �Ϳ��׿�!!!!");
		cp.add(laFont,BorderLayout.NORTH);

	    setUI();
	}
	void setLalmg(){
		//(1)������ ����
		laImg.setIcon(ii);
		//(2) ��Ʈ����
		laFont.setFont(new java.awt.Font("�������",Font.BOLD,16));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		//left , center, righgt (�빮����)
		laFont.setVerticalAlignment(JLabel.BOTTOM);
		//top , center, bottom (�빮����)
		//(3)��������
		Border oborder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10); //EmptyBorder �ݽð����  �� �� �Ʒ� ��
		CompoundBorder cBorder = new CompoundBorder(oborder,eBorder);
		laFont.setBorder(cBorder);
	}
	void loadImg(){
	/*���1  try{
			File f = new File("imgs/����.gif");
			BufferedImage bi = ImageIO.read(f);
			ii = new ImageIcon(bi);
		}catch(IOException ie){
			pln("�̹��� ���� ") //�̹����� ������ ���ٰ� �����ټ� �־ ���� 
		}*/
		//��� 2
		ii = new ImageIcon(getClass().getResource("imgs/����.gif"));
	}
	void setUI(){
		setTitle("JFileChooser Test");
		pack();
		setVisible(true);
		//setLocation(300, 100);
		setLocationRelativeTo(null); //â �׳� ���߾ӿ� �����
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
