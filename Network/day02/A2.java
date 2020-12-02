import java.awt.*;
import java.awt.event.*; //import java.awt.*;랑 다른거임  {폴더가 다른거..? 비슷한 느낌}
import javax.swing.*; //javax는 확장팩 : 다른패키지임 

//방법2 : 내부클래스 말고 간단하게 쓰는법 --> 클래스 이름이 없는거지 객체이름이 없는건 아님
class A2 extends JFrame{ //컨테이너 
	JButton b; //UI컴포런트
	void init(){
		b = new JButton("자바의 버튼");
		add(b);

		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.setText("버튼 클릭됨!!( by 익(무)명 클래스)");
			}
		});
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
		A2 a2 = new A2();
		a2.init();
	
	}
} 