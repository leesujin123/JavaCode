package four.quiz;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class MSHandler implements ActionListener{
	MS ms;
	TreeSet<String> tempSet = new TreeSet<String>();
	
	MSHandler(MS ms){
		this.ms = ms;
	}
	
	void readOriginal(){
		 FileReader fr = null;
		 BufferedReader br = null;
		 File f = null;
		 String line = "";
		 try{
			 fr = new FileReader("txtFile/original.txt");
			 br = new BufferedReader(fr);
			 while((line = br.readLine()) != null){
				 if(line != null) line = line.trim();
				 if(line.length() != 0) {
					 //P.pln(line);	
					 tempSet.add(line);
				 }
			 }	
			 saveStartFile();
		 }catch(FileNotFoundException fe){
			 System.out.println("파일을 못 찾음");
		 }catch(IOException ie){
		 }finally{
			 try{
				 if(br != null) br.close();
				 if(fr != null) fr.close();
			 }catch(IOException ie){}
		 }
	}
	void saveStartFile(){
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter("txtFile/filtered.txt");
			pw = new PrintWriter(fw, true);
			for(String item: tempSet){
				pw.println(item);
			}
		}catch(IOException ie){
		}finally{
			try{
				if(pw != null) pw.close();
				if(fw != null) fw.close();
			}catch(IOException ie){}
		}
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == ms.b || obj == ms.tfName){
			ms.nameG = ms.tfName.getText();
			//System.out.println(ms.nameG); // Game에 넘겨줘야함
			QBack qback = new QBack(); //문제 컬렉션에 저장
			new Game(ms.nameG, qback);
			ms.dispose();
		}else if(obj == ms.btt){
			readOriginal();
			JOptionPane.showMessageDialog(null,"셋팅이 완료되었습니다.");

		}
	}
}
