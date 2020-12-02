import java.awt.event.*;
import javax.swing.*;

class MWHandler implements ActionListener{
	MainWindow mw;
	Client client;	//MWH���� ������� Ŭ��ü�� �̿��ϱ����ؼ� ����� ����.
	MWHandler(MainWindow mw){
		this.mw = mw;
	}
	String nickN, msg;
	int readymode=0;
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		//�α���â������ �̺�Ʈó��
		if(obj == mw.bStart||obj == mw.tfName){
			
			//������: �г����� �ߺ��� ȸ���ؾ���
			nickN = mw.tfName.getText();
			//System.out.println(nickN);
			mw.removeComp();
			mw.gameComp();
			client = new Client(this);//����������(); >> ������ �����Ѵ�, nickName �� �����Ѵ� >> 1.Client.java >> MainWindow.java >> MWHandler.java >> Client.java (������ �帧)
			mw.tInputChat.grabFocus();
			
		}else if(obj == mw.bOut){
			//�ɼ������� ����Ͽ� �����°��� Ȯ���Ѵ�
			System.exit(-1);
		}

		//����â������ �̺�Ʈó��
		if(obj == mw.tInputChat){
			if(GameMode.gameMode == GameMode.BEFORE){
				msg = mw.tInputChat.getText();
				client.sendMsg();
				mw.tInputChat.setText("");
			}else if(GameMode.gameMode == GameMode.NOTMYTURN){
				mw.tShowChat.append("�� ���ʰ� �ƴϾ�\n");
				mw.tInputChat.setText("");
			}else if(GameMode.gameMode == GameMode.MYTURN){
				client.checkAnswer();
			}
		}else if(obj==mw.bReady){
			if(GameMode.gameMode == GameMode.BEFORE){
				if(readymode == 0){
					client.readyMsg();
					readymode = 1;//����
				}else if(readymode==1){
					client.noreadyMsg();
					readymode = 0;
				}
			}else{/*�ݾƳ���*/}
		}else if(obj==mw.bExit){
			if(GameMode.gameMode == GameMode.BEFORE){
				System.exit(-1);
			}else{/*�켱 �ݾƳ���, ���߿� ���������� �� ���ߴܵǴ°� �ϸ� ����*/}
		}
		
	}

}
