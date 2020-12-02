import java.awt.event.*;

class MSHandler implements ActionListener{
	MS ms;
	MSHandler(MS ms){
		this.ms = ms;
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == ms.b || obj == ms.tfName){
			ms.nameG = ms.tfName.getText();
			//System.out.println(ms.nameG); // Game에 넘겨줘야함
			new Game(ms.nameG);
			ms.dispose();
		}
	}
}
