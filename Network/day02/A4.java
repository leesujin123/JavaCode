import java.awt.*;
import java.awt.event.*; //import java.awt.*;�� �ٸ�����  {������ �ٸ���..? ����� ����}
import javax.swing.*; //javax�� Ȯ���� : �ٸ���Ű���� 

//���4 : ��3�� Ŭ���� �����
class A4 extends JFrame{ //�����̳� 
	JButton b; //UI������Ʈ
	void init(){
		b = new JButton("�ڹ��� ��ư");
		add(b);
		ActionListener listener = new A4Handler(this);
		b.addActionListener(listener); //�̺�Ʈ �����ϱ� ���� �����������
		setUI();
	}
	
	void setUI(){
		setTitle("GTV 1.0"); //��µǴ°�
		setSize(300,200); //â ������
		setVisible(true);//���� ���̰��س��� false�� ���� �Ⱥ��� 
		setLocation(200,100); //0.0����

		setResizable(false); //â ���� 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //â ������ ������Ʈ�� ����
	}
	public static void main(String args[]){
		A4 a4 = new A4();
		a4.init();
	}
} 
/*class A4Handler implements ActionListener { //��3��Ŭ���� 
	A4 a4;
	A4Handler(A4 a4){  //A4�� ���� ������ ���� ���� this�� A4Handler ��밡�� 
		this.a4=a4;
	}
		public void actionPerformed(ActionEvent e){
			a4.b.setText("��ư Ŭ����!!( by ��3Ŭ����2)");  
		}
	}*/