import java.awt.*;
import java.awt.event.*; //import java.awt.*;랑 다른거임  {폴더가 다른거..? 비슷한 느낌}
import javax.swing.*; //javax는 확장팩 : 다른패키지임 

//방법1 : 내부클래스 <몇변안쓰는데 이렇게 만들필요있나?해서 방법2번>
class A1 extends JFrame{ //컨테이너 
	class A1Handler implements ActionListener { //내부클래스 
		public void actionPerformed(ActionEvent e){//ActionEvent e 이벤트 객체 = 버튼뽑기 가능
			//System.out.println("이벤트 감지");
			b.setText("버튼 클릭됨!!( by 유명 내부클래스1)"); //이름있는 내부클래스라서 유명임 글자 바뀜 = 이벤트 감지되서 
		
			//(JButton) bb = e.grtSource();
			//b.setText("버튼 클릭됨!!( by 유명 내부클래스2)"); //이방법도 가능 
		}
	}
	JButton b; //UI컴포런트
	void init(){
		b = new JButton("자바의 버튼");
		add(b);
		ActionListener listener = new A1Handler();
		b.addActionListener(listener); //이벤트 감지하기 위해 리쓴해줘야함
		setUI();

		/*setTitle("GTV 1.0"); //출력되는거
		setSize(300,200); //창 사이즈
		setVisible(true);
		setLocation(200,100); //0.0지정
		setResizable(false); //창 고정 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 나갈때 프롬프트도 나가
		*/
	}
	
	void setUI(){
		setTitle("GTV 1.0"); //출력되는거
		setSize(300,200); //창 사이즈
		setVisible(true);//눈에 보이게해놓음 false면 눈에 안보임 
		setLocation(200,100); //0.0지정

		setResizable(false); //창 고정 false면 못움직임
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 나갈때 프롬프트도 나가
	}
	public static void main(String args[]){
		A1 a1 = new A1();
		a1.init();
	
	}
} 