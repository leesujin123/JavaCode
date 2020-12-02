import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

//choosable ������ �� �ִ� 
class A extends JFrame implements ActionListener {
	Container cp;
	JButton bOpen;
	JButton bSave;
	JButton bClear;
	JTextPane tp;
	JScrollPane sp;
	JPanel p;
	String fPath = "C:/LSJ/Network/day03";
	final static int FC_OPEN = 0;
	final static int FC_SAVE = 1;

	A(){
		tp = new JTextPane(); //�ؽ�Ʈâ
		sp = new JScrollPane(tp);//��ũ��â
		p = new JPanel();
		bOpen = new JButton("Open"); //����
		bSave = new JButton("Save"); //����
		bClear = new JButton("Clear");//����
		bOpen.addActionListener(this);
		bSave.addActionListener(this);
		bClear.addActionListener(this);

		init();
	}
	void init(){
		cp = getContentPane();

        p.setLayout(new GridLayout(1, 2)); //���ٿ� ��ĭ
		p.add(bOpen);
		p.add(bSave);
        cp.add(p, BorderLayout.NORTH); //��ư�� ���� �÷��ִ°�
		cp.add(sp);//��ũ��â 
		cp.add(bClear, BorderLayout.SOUTH);//������ �����ִ°�

		setUI();
	}
 	void setUI(){
		setTitle("JFileChooser Test");
		setSize(400, 300);
		setVisible(true);
		setLocation(300, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void showFC(int mode){
		JFileChooser jfc = new JFileChooser(fPath);
		FileNameExtensionFilter filter 
			= new FileNameExtensionFilter("���Ͽ���(txt/java)", "txt", "java");
		jfc.addChoosableFileFilter(filter);

        int option = -1;
		if(mode == FC_OPEN){
			option = jfc.showOpenDialog(this);
		}else{ //FC_SAVE
			option = jfc.showSaveDialog(this);	
		}
		//pln("option: " + option);

	/*	if(option == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();	
			//pln("f: " + f);
			if(mode == FC_OPEN){
				readF(f);
			}else{
				writeF(f);
			}
		}
	}
	void readF(File f){ //file -> tp

	}
	void writeF(File f){ //tp -> file
	}
	/*void showFC(){
		JFileChooser jfc = new JFileChooser(fPath);
		jfc.showOpenDialog(this);
		//jfc.showSaveDialog(this);
	}
	void showFC1(){
		JFileChooser jfc = new JFileChooser(fPath);
		//jfc.showOpenDialog(this);
		jfc.showSaveDialog(this);
	}*/
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bOpen){
			showFC(FC_OPEN);
		}else if(obj == bSave){
			showFC(FC_SAVE);
		}else{
			tp.setText("");
			tp.requsetFocus();//����� Ŀ�� �ٽð����ְ� �ϴ°� �Ⱦ��� â�� ������ �����
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new A();
	}
}
