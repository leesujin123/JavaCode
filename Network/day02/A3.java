import java.awt.*;
import java.awt.event.*; //import java.awt.*;�� �ٸ�����  {������ �ٸ���..? ����� ����}
import javax.swing.*; //javax�� Ȯ���� : �ٸ���Ű���� 

//���3 : ���� �̺�Ʈ�� �Ǵ� ��� 
class A3 extends JFrame implements ActionListener { //�����̳� 
	JButton b; //UI������Ʈ
	void init(){
		b = new JButton("�ڹ��� ��ư");
		add(b);
		b.addActionListener(this);

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
	public void actionPerformed(ActionEvent e){
				b.setText("��ư Ŭ����!!( by �ڽ��� Ŭ����)");
			}
	public static void main(String args[]){
		A3 a3 = new A3();
		a3.init();
	}
} 