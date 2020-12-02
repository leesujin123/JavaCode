package four.quiz;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class GameHandler implements ActionListener{
	Game g;
	QBack qback;
	int size;
	GameHandler(Game g, QBack qback){
		this.g = g;
		this.qback = qback;
		size = g.vqB.size();
	}
	
	int qBnumber = 0; // 패스하거나 맞춘 문제 카운트
	
	

	public void actionPerformed(ActionEvent e){			//배열>>컬렉션 #1
		
		Object obj = e.getSource();
		if(obj==g.bM){
			if(g.clip.isRunning()==false){//음악 안나오면
				g.clip.start();
				g.bM.setIcon(g.selectedIcon);
			}else if(g.clip.isRunning()==true){
				g.clip.stop();
				g.bM.setIcon(g.normalIcon3);
			
			}
		}

		if(g.vqB.size() == 0){
			System.out.println("RRR");
			//문제가 다 떨어지는 경우
			//타이머도 멈추고,게임이 종료되도록 한다. 
			//하지만 컬렉션으로 바꾸면, 로직이 달라지므로 공백
			if(obj == g.bSB) new Score();
			if(obj == g.bRe){
				//에 해당하는 메세지창 띄우자
				System.out.println("문제가 다 떨어졌어요!");
			}
			if(obj == g.bExit) System.exit(-1);
		}

		JButton qbutton = g.vqB.get(qBnumber);
		if(g.timeI == 0){
			
			if(obj == g.bSB){
				new Score();
			}else if(obj == g.bRe){
				new MS();
				g.dispose();						//창 자체를 지우는것
			}else if(obj == g.bExit){
				System.exit(-1);
			}
		}else if(g.timeI <= g.TIME_START) {
			if(obj == g.bPass){ //pass
				g.pC.remove(qbutton);//시행착오:버튼을 지우려면 패널에서 지워야지
				g.revalidate();
				qBnumber +=1;
			}else if(obj == qbutton){ //정답
				String correct = qbutton.getText();
				//System.out.println(correct);
				int remove = qback.filteredList.indexOf(correct);
				//System.out.println(remove);
				qback.filteredList.remove(remove);
				//System.out.println(qback.filteredList.size());
				
				g.pC.remove(qbutton);						//패널에서 지워야해
				g.revalidate();
			//맞춘 버튼의 스트링과 같은 문제벡터의 요소를 제거해야한다?
				qBnumber +=1;
				g.scoreI +=1;
				g.scoreS = Integer.toString(g.scoreI);
				g.laScore.setText("<html>Score<br>"+g.scoreS+"</html>");
			}
		}
		
	}
}
