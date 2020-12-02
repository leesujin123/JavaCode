import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

class MS extends JFrame implements ActionListener {
	JButton bN, bS, bW, bE;
	JButton bPS, bPE, bPW, bPN; 
	JPanel p;
	Container cp;
	JButton b;
	ImageIcon iA;
	ImageIcon iB;
	ImageIcon iC;
	ImageIcon iD;

	void init(){
		p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		bPS = new JButton(iA);
		bPE = new JButton(iB);
		bPW = new JButton(iC);
		bPN = new JButton(iD);
        p.add(bPS, BorderLayout.SOUTH);
		p.add(bPE, BorderLayout.EAST);
		p.add(bPW, BorderLayout.WEST);
		p.add(bPN, BorderLayout.NORTH);
		bPN.addActionListener(this);
		bPE.addActionListener(this);
		bPW.addActionListener(this);
		bPS.addActionListener(this);

		bN = new JButton("북");
		bS = new JButton("남");
		bW = new JButton("서");
		bE = new JButton("동");
		cp = getContentPane();
		cp.add(bN, BorderLayout.NORTH); 
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);
		loadImg();
		b = new JButton();
		b.addActionListener(this);
		cp.add(b);
		cp.add(p);
		
		setUI();
	}

	void loadImg(){
		try{
			iA = new ImageIcon(ImageIO.read(new File("A.png")));
			iB = new ImageIcon(ImageIO.read(new File("B.png")));
			iC = new ImageIcon(ImageIO.read(new File("C.png")));
			iD = new ImageIcon(ImageIO.read(new File("D.png")));
		}catch(IOException ie){
		}
	}
	void setUI(){
		setTitle("Image Test");
		pack();
		setVisible(true);
		setLocation(200, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		JButton b = (JButton)obj;
		if(obj == ms.iA){
			q("과제 해결하셨나요???",iA);
		}else if(obj == ms.iB){
			q("놀고싶죠????",iB);
		}else if(obj == ms.iC){
			q("이해했어요...???",iC);
		}else{ 
			q("다같이 열심히 노력할 생각 있죠???",iD);
		}

		System.out.println(b.getLabel());
	}
 void q(String Q,ImageIcon img ){
		int answer = JOptionPane.showConfirmDialog(null, "지금 힘들어요??", "질문", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.QUESTION_MESSAGE, 
			iA);
		if(answer == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, "당신은 좋은사람입니다 항상 힘을 내세요~!");
		}else{
			JOptionPane.showMessageDialog(null, "항상 긍정적이고 밝은사람!!");
		}
	}
		void pln(String str){
		System.out.println(str);
		}	
	public static void main(String args[]){
		MS ms = new MS();
		ms.init();
	}
}

class MSHandler implements ActionListener 
{
	MS ms;
	MSHandler(MS ms){
		this.ms = ms;
	}
}
