import java.awt.event.*;
import javax.swing.*;

class MWHandler implements ActionListener{
	MainWindow mw;
	MWHandler(MainWindow mw){
		this.mw = mw;
	}
	String nickN, msg;

	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		//로그인창에서의 이벤트처리
		if(obj == mw.bStart||obj == mw.tfName){
			nickN = mw.tfName.getText();
			//System.out.println(nickN);
			mw.client = new Client(this);//서버에접속(); >> 소켓을 연결한다, nickName 을 전달한다 >> 1.Client.java >> MainWindow.java >> MWHandler.java >> Client.java (정보의 흐름)
			mw.removeComp();
			mw.gameComp();
			mw.tInputChat.grabFocus();
		}else if(obj == mw.bOut){
			//옵션페인을 출력하여 나가는것을 확인한다
			System.exit(-1);
		}

		//게임창에서의 이벤트처리
		if(obj == mw.tInputChat){
			msg = mw.tInputChat.getText();
			//System.out.println(msg);
			mw.client.sendMsg();
			mw.tInputChat.setText("");
		}
		
	}

}
