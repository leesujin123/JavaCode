package four.quiz;

import java.io.*;
import java.util.*;

class QBack{
	Vector<String> filteredList = new Vector<String>();
	String fname = "txtFile/filtered.txt";
	QBack(){
		inputQ();
	}
	void inputQ(){    //필터.txt에서 컬렉션으로 입력받기 >> 게임이 시작될 때(스타트버튼이나,엔터)
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		try{
			fr = new FileReader(fname);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				if(line != null) line = line.trim();
				if(line.length() != 0){
					filteredList.add(line);
				}
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){
		}
	}
	
	void removeQ(){}	//정답을 맞췄을 때, 문제컬렉션에서 문제를 제거하는 메소드 >> 게임핸들러에서 수행함.
	
	void saveFilteredFile(){		//제거한 문제리스트를 다시 저장
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(fname);
			pw = new PrintWriter(fw, true);

			for(String item: filteredList){
				pw.print(item+"\n");
				//System.out.print(item);
			}
		}catch(IOException ie){
		}finally{
			try{
				if(pw != null) pw.close();
				if(fw != null) fw.close();
			}catch(IOException ie){}
		}
	}
	
	void scoreRead(){} //스코어보드에 점수표시 >> 다른곳에서하자

}
