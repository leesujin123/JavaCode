import java.awt.event.*;
import javax.swing.*;

class GameHandler implements ActionListener{
	Game g;
	GameHandler(Game g){
		this.g = g;
	}
	
	int qBnumber = 0; // 패스하거나 맞춘 문제 카운트
	public void actionPerformed(ActionEvent e){			//배열>>컬렉션 #1
		Object obj = e.getSource();
		//System.out.println(qBnumber);
		if(qBnumber == 20){
			//문제가 다 떨어지는 경우
			//타이머도 멈추고,게임이 종료되도록 한다. 
			//하지만 컬렉션으로 바꾸면, 로직이 달라지므로 공백
			if(obj == g.bSB & g.timeI ==0) new Score();
			if(obj == g.bRe & g.timeI ==0){
				new MS();
				g.dispose();
			}
			if(obj == g.bExit & g.timeI ==0) System.exit(-1);
		}else if(obj == g.bSB & g.timeI ==0){
			new Score();
		}else if(obj == g.bPass & g.timeI != 0){ //pass
			g.pC.remove(g.qB[qBnumber]);//시행착오:버튼을 지우려면 패널에서 지워야지
			g.revalidate();
			qBnumber +=1;
		}else if(obj == g.bRe & g.timeI ==0){
			new MS();
			g.dispose();						//창 자체를 지우는것
		}else if(obj == g.bExit & g.timeI ==0){
			System.exit(-1);
		}else if(obj == g.qB[qBnumber] & g.timeI!=0){ //정답
			g.pC.remove(g.qB[qBnumber]);						//패널에서 지워야해
			g.revalidate();
			qBnumber +=1;
			g.scoreI +=1;
			g.scoreS = Integer.toString(g.scoreI);
			g.laScore.setText("<html>Score<br>"+g.scoreS+"</html>");
		}
	}
}
