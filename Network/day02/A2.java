import java.awt.*;
import java.awt.event.*; //import java.awt.*;�� �ٸ�����  {������ �ٸ���..? ����� ����}
import javax.swing.*; //javax�� Ȯ���� : �ٸ���Ű���� 

//���2 : ����Ŭ���� ���� �����ϰ� ���¹� --> Ŭ���� �̸��� ���°��� ��ü�̸��� ���°� �ƴ�
class A2 extends JFrame{ //�����̳� 
	JButton b; //UI������Ʈ
	void init(){
		b = new JButton("�ڹ��� ��ư");
		add(b);

		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.setText("��ư Ŭ����!!( by ��(��)�� Ŭ����)");
			}
		});
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
		A2 a2 = new A2();
		a2.init();
	
	}
} 