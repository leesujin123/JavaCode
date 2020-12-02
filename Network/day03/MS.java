import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class MS extends JFrame {
	JButton b, btt;
	Container cp;
	JLabel laFont;
	JTextField tfName;
	String nameG;
	ImageIcon reset;

	MS(){
		laFont = new JLabel();
		tfName = new JTextField(20);
		init();
	}
	void init(){
		cp = getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(laFont);
		cp.add(tfName);
		b = new JButton("Game Start");

		add(b);
		laFont.setFont(new java.awt.Font("맑은고딕",Font.BOLD,20));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		laFont.setVerticalAlignment(JLabel.CENTER);
		Border oBorder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10);
		laFont.setBorder(oBorder);
		laFont.setBorder(eBorder);
		laFont.setText("Enter your name~!");

		try{ //여기부터
			reset = new ImageIcon(ImageIO.read(new File("Reset.png")));
		}catch(IOException ie){}
		JToolBar bar =new JToolBar();
		btt = new JButton(reset);
		btt.setBorderPainted(false);//외각선 없애주기
		btt.setContentAreaFilled(false);//내용영역채우기 없애주기
		btt.setFocusPainted(false); //눌러도 테두리 없는거
		btt.setToolTipText("이건 Filtered file버튼입니다.");

		bar.add(btt);
		add(btt);//여기까지가 tool나오게하기

		ActionListener msH = new MSHandler(this);
		b.addActionListener(msH);
		btt.addActionListener(msH);
		tfName.addActionListener(msH);

	    setUI();
	}
	void setUI(){
		setEnabled(true);
		setTitle("Name(team) input window");
		pack();
		setSize(280,200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String [] args) {
		new MS();
	}
}