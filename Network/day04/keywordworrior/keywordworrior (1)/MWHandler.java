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
		//�α���â������ �̺�Ʈó��
		if(obj == mw.bStart||obj == mw.tfName){
			nickN = mw.tfName.getText();
			//System.out.println(nickN);
			mw.client = new Client(this);//����������(); >> ������ �����Ѵ�, nickName �� �����Ѵ� >> 1.Client.java >> MainWindow.java >> MWHandler.java >> Client.java (������ �帧)
			mw.removeComp();
			mw.gameComp();
			mw.tInputChat.grabFocus();
		}else if(obj == mw.bOut){
			//�ɼ������� ����Ͽ� �����°��� Ȯ���Ѵ�
			System.exit(-1);
		}

		//����â������ �̺�Ʈó��
		if(obj == mw.tInputChat){
			msg = mw.tInputChat.getText();
			//System.out.println(msg);
			mw.client.sendMsg();
			mw.tInputChat.setText("");
		}
		
	}

}
