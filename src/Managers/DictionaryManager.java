package Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManager {
	
	public List<String> dictionaryList = new ArrayList<String>();
	
	public DictionaryManager() {
		
	}
	
	public void evaluateWords() {
		loadWords();
	}
	
	public void loadWords(){

		File dictionaryFile = null;
		BufferedReader br = null;
		
		try {
			dictionaryFile = new File("assets\\formatedDictionary.txt");
			br = new BufferedReader(new FileReader(dictionaryFile));
		} catch (Exception e) {
			
		}
		
		String s;
		try {
			while((s = br.readLine()) != null) {
				String[] var = s.split("\\r?\\n");
				
				if(var[0]=="--end--") {
					break;
				}
				dictionaryList.add(var[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
