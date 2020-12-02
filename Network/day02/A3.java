import java.awt.*;
import java.awt.event.*; //import java.awt.*;랑 다른거임  {폴더가 다른거..? 비슷한 느낌}
import javax.swing.*; //javax는 확장팩 : 다른패키지임 

//방법3 : 내가 이벤트가 되는 방법 
class A3 extends JFrame implements ActionListener { //컨테이너 
	JButton b; //UI컴포런트
	void init(){
		b = new JButton("자바의 버튼");
		add(b);
		b.addActionListener(this);

		setUI();
	}
	void setUI(){
		setTitle("GTV 1.0"); //출력되는거
		setSize(300,200); //창 사이즈
		setVisible(true);//눈에 보이게해놓음 false면 눈에 안보임 
		setLocation(200,100); //0.0지정

		setResizable(false); //창 고정 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 나갈때 프롬프트도 나가
	}
	public void actionPerformed(ActionEvent e){
				b.setText("버튼 클릭됨!!( by 자신의 클래스)");
			}
	public static void main(String args[]){
		A3 a3 = new A3();
		a3.init();
	}
} 