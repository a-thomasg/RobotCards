package Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordManager {
	
	private List<String> uniqueWordList = new ArrayList<String>();
	private int uniqueWordListSize = 0;
	
	public WordManager() {
		
		
		
	}
	
	public void updateSaveFiles() {		
		updateBirthdayCards();
		updateBirthdayWordList1();
		updateBirthdayWordList2();
	}
	
	private void updateBirthdayWordList2() {
		// TODO Auto-generated method stub
		
	}

	private void updateBirthdayWordList1() {
		File birthdayFile = null;
		BufferedReader br = null;
		
		try {
			birthdayFile = new File("assets\\birthdaycards.txt");
			br = new BufferedReader(new FileReader(birthdayFile));
		} catch (Exception e) {
			
		}
		String s = "";
		String cardStr = "";
		long wordCount = 0;
		try  {
			while((s = br.readLine()) != null) {
				String[] var = s.replaceAll("\\t", " +t ").replaceAll("[^a-zA-Z'(+\\- ]", " ").toLowerCase().split("\\r?\\n");// \\s+|\\r?\\n
				for(int i=0;i<var.length+1;i++) {
					if(i>=var.length) {
						cardStr += " +n";
						saveToFile("assets/formattedCardSaveFile.txt", cardStr, wordCount);
					} else {
						cardStr += " " + var[i];
					}
					if(uniqueWordList.isEmpty()) {
						uniqueWordList.add(var[i]);
					} else {
						jLoop:
						for(int j=0;j<uniqueWordList.size();j++) {
							if(var[i] == uniqueWordList.get(j)) {
								break jLoop;
							} else {
								if(j==uniqueWordList.size()-1) {
									uniqueWordList.add(var[i]);
								}
							}
						}
					}
					wordCount++;
				}
			}
			if(!uniqueWordList.isEmpty()) {
				uniqueWordListSize = uniqueWordList.size();
				for(int k=0;k<uniqueWordList.size();k++) {
					saveToFile("assets/uniqueWordSaveFile.txt", uniqueWordList.get(k), k);
					if(k==uniqueWordList.size()-1) {
//						uniqueWordList.removeAll(uniqueWordList);
					}
				}
			}
		} catch (IOException e) {
			
		}
		
		File formattedCardFile = null;
		BufferedReader brOne1 = null;
		BufferedReader brOne2 = null;
		
		try {
			formattedCardFile = new File("assets\\formattedCardSave.txt");
			brOne1 = new BufferedReader(new FileReader(formattedCardFile));
			brOne2 = new BufferedReader(new FileReader(formattedCardFile));
		} catch (Exception e) {
			
		}
		String oneWord1 = "";
		String oneWord2 = "";
		long word1 = 0;
		for(int i=0;i<uniqueWordListSize;i++) {
			String temp1 = getWordAt(i, "assets/uniqueWordSaveFile.txt");
			try  {
				while((oneWord1 = brOne1.readLine()) != null) {
					String[] var1 = oneWord1.split("\\s+|\\r?\\n");
					for(int j=0;j<var1.length;j++) {
						if(temp1 == var1[j] && temp1 != null) {
							if(j+1 >= var1.length) {
								
							} else {
								temp1 += "," + var1[j];
							}
							if(j==var1.length-1 && i==uniqueWordListSize-1) {
								saveToFile("assets/word1SaveFile.txt", temp1, i);
							}
						}
					}
				}
			} catch (IOException e) {
				
			}
		}
	}

	public void updateBirthdayCards() {

	}
	
	private String getWordAt(int index, String fileName) {
		File f = null;
		BufferedReader br = null;
		
		try {
			f = new File(fileName);
			br = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			
		}
		
		String s = "";
		String temp = null;
		int count = 0;
		try {
			while((s = br.readLine()) != null) {
				String[] var = s.split("\\s+\\r?\\n");
				for(int i=0;i<var.length;i++) {
					if(count==index) {
						temp = var[i];
					}
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public void saveToFile(String file, String str, long line) {
		SaveManager save = new SaveManager(file, str, line);
		save.save();
	}
}
