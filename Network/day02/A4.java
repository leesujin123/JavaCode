import java.awt.*;
import java.awt.event.*; //import java.awt.*;랑 다른거임  {폴더가 다른거..? 비슷한 느낌}
import javax.swing.*; //javax는 확장팩 : 다른패키지임 

//방법4 : 제3의 클래스 만들기
class A4 extends JFrame{ //컨테이너 
	JButton b; //UI컴포런트
	void init(){
		b = new JButton("자바의 버튼");
		add(b);
		ActionListener listener = new A4Handler(this);
		b.addActionListener(listener); //이벤트 감지하기 위해 리쓴해줘야함
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
	public static void main(String args[]){
		A4 a4 = new A4();
		a4.init();
	}
} 
/*class A4Handler implements ActionListener { //제3의클래스 
	A4 a4;
	A4Handler(A4 a4){  //A4로 나를 가져다 쓰고 나도 this로 A4Handler 사용가능 
		this.a4=a4;
	}
		public void actionPerformed(ActionEvent e){
			a4.b.setText("버튼 클릭됨!!( by 제3클래스2)");  
		}
	}*/