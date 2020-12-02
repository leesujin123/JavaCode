import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MS extends JFrame   {
	JButton b;
	Container cp;
	JLabel laFont;
	JTextField tfName;
	String nameG;

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
		laFont.setFont(new java.awt.Font("맑은고딕",Font.BOLD,16));
		laFont.setHorizontalAlignment(JLabel.CENTER);
		laFont.setVerticalAlignment(JLabel.CENTER);
		Border oBorder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20,10,20,10);
		laFont.setBorder(oBorder);
		laFont.setBorder(eBorder);
		laFont.setText("이름을 입력해 주세요~!");
		//이벤트 처리
		ActionListener msH = new MSHandler(this);
		b.addActionListener(msH);
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