import java.awt.*;
import java.awt.event.*; //import java.awt.*;�� �ٸ�����  {������ �ٸ���..? ����� ����}
import javax.swing.*; //javax�� Ȯ���� : �ٸ���Ű���� 

//���1 : ����Ŭ���� <��Ⱦ��µ� �̷��� �����ʿ��ֳ�?�ؼ� ���2��>
class A1 extends JFrame{ //�����̳� 
	class A1Handler implements ActionListener { //����Ŭ���� 
		public void actionPerformed(ActionEvent e){//ActionEvent e �̺�Ʈ ��ü = ��ư�̱� ����
			//System.out.println("�̺�Ʈ ����");
			b.setText("��ư Ŭ����!!( by ���� ����Ŭ����1)"); //�̸��ִ� ����Ŭ������ ������ ���� �ٲ� = �̺�Ʈ �����Ǽ� 
		
			//(JButton) bb = e.grtSource();
			//b.setText("��ư Ŭ����!!( by ���� ����Ŭ����2)"); //�̹���� ���� 
		}
	}
	JButton b; //UI������Ʈ
	void init(){
		b = new JButton("�ڹ��� ��ư");
		add(b);
		ActionListener listener = new A1Handler();
		b.addActionListener(listener); //�̺�Ʈ �����ϱ� ���� �����������
		setUI();

		/*setTitle("GTV 1.0"); //��µǴ°�
		setSize(300,200); //â ������
		setVisible(true);
		setLocation(200,100); //0.0����
		setResizable(false); //â ���� 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //â ������ ������Ʈ�� ����
		*/
	}
	
	void setUI(){
		setTitle("GTV 1.0"); //��µǴ°�
		setSize(300,200); //â ������
		setVisible(true);//���� ���̰��س��� false�� ���� �Ⱥ��� 
		setLocation(200,100); //0.0����

		setResizable(false); //â ���� false�� ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //â ������ ������Ʈ�� ����
	}
	public static void main(String args[]){
		A1 a1 = new A1();
		a1.init();
	
	}
} 