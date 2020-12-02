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
		laFont.setFont(new java.awt.Font("�������",Font.BOLD,20));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		laFont.setVerticalAlignment(JLabel.CENTER);
		Border oBorder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10);
		laFont.setBorder(oBorder);
		laFont.setBorder(eBorder);
		laFont.setText("Enter your name~!");

		try{ //�������
			reset = new ImageIcon(ImageIO.read(new File("Reset.png")));
		}catch(IOException ie){}
		JToolBar bar =new JToolBar();
		btt = new JButton(reset);
		btt.setBorderPainted(false);//�ܰ��� �����ֱ�
		btt.setContentAreaFilled(false);//���뿵��ä��� �����ֱ�
		btt.setFocusPainted(false); //������ �׵θ� ���°�
		btt.setToolTipText("�̰� Filtered file��ư�Դϴ�.");

		bar.add(btt);
		add(btt);//��������� tool�������ϱ�

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