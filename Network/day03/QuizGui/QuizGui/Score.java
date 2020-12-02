import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Score extends JFrame {
	Container c;
	JPanel pBG;
	JPanel p1;
	JPanel p2;
	JLabel la;
	JTextArea jt = new JTextArea();

	Score(){
		init();
	}
	void init(){
		c = getContentPane();
		
		pBG = new JPanel();
		pBG.setLayout(new BorderLayout());
		
		p1 = new JPanel();
		la = new JLabel();
		la.setText("SCORE");
		p1.add(la);

		p2 = new JPanel(new BorderLayout());
		jt = new JTextArea("이름    점수");
		jt.setEditable(false);
		p2.add(jt);
		
		pBG.add(p1, BorderLayout.NORTH);
		pBG.add(p2);
	
		c.add(pBG);
		designC();

		setUI();
	}
	void designC(){
		la.setFont(new Font("Tahoma", Font.BOLD, 20));
		Border eB1 = new EmptyBorder(20,20,20,20);
		Border eB2 = new EmptyBorder(1,20,20,20);
		Border oB1 = p1.getBorder();
		CompoundBorder cB1 = new CompoundBorder(oB1, eB1);
		p1.setBorder(cB1);

		Border oB2 = p2.getBorder();
		CompoundBorder cB2 = new CompoundBorder(oB2, eB2);
		p2.setBorder(cB2);
	}
	void setUI(){
		setTitle("SCORE BOARD");
		setSize(300, 350);
		setVisible(true);
		setLocation(900, 300);
		
		setResizable(false);
		
	}
	public static void main(String[] args){
		//new Score();
	}
}
