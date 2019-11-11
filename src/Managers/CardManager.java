package Managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Constructors.Word;

public class CardManager {

//	Word word;
	
//	public List<List<String>> wordList = new ArrayList<List<String>>();
	public List<Word> wordList = new ArrayList<Word>();
	public List<Word> wordsList = new ArrayList<Word>();
	public List<String> cardList = new ArrayList<String>();
	String str;
	
	public List<String> allWordsList = new ArrayList<String>();
	public List<String> uniqueWordList = new ArrayList<String>();
	
	private List<String> testList = new ArrayList<String>();
	
	private List<String> newCardList = new ArrayList<String>();
	public String newCard = "";
	
	public CardManager() {
		
	}
	
	public void evaluateCards() {
		loadCards();
//		formatWords();
//		removeDuplicates();
		createWordAssociations();
//		generateCardN1();
//		generateCardN2();
		testPrint();
	}
	
	private void testPrint() {
		for(int i=0;i<100000;i++) {
			saveToFile("assets/word1SaveFile.txt", " Hello", i);
		}
	}
	public void saveToFile(String file, String str, long line) {
		SaveManager save = new SaveManager(file, str, line);
		save.save();
	}

	public void loadCards(){
		File birthdayFile = null;
		BufferedReader br = null;
		
		try {
			birthdayFile = new File("assets\\birthdaycards.txt");
			br = new BufferedReader(new FileReader(birthdayFile));
		} catch (Exception e) {
			
		}
		
		String s;
		try {
			while((s = br.readLine()) != null) {
				String[] var = s.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\r?\\n");
				
				for(int i=0;i<var.length;i++) {
					cardList.add(var[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(cardList);
	}
	
	public void formatWords() {
		File birthdayFile = null;
		BufferedReader br = null;
		
		try {
			birthdayFile = new File("assets\\birthdaycards.txt");
			br = new BufferedReader(new FileReader(birthdayFile));
		} catch (Exception e) {
			
		}
		String s;
		try  {
			while((s = br.readLine()) != null) {
				String[] var = s.replaceAll("[^a-zA-Z( ]", " ").toLowerCase().split("\\s+|\\r?\\n");
				for(int i=0;i<var.length;i++) {
					allWordsList.add(var[i]);
				}
			}
		} catch (IOException e) {
			
		}
//		System.out.println(allWordsList);

	}
	
	public void removeDuplicates() {
		uniqueWordList.add(allWordsList.get(0));
		for(int i=1;i<allWordsList.size();i++) {
			jLoop:
			for(int j=0;j<uniqueWordList.size();j++) {
				if(allWordsList.get(i).equals(uniqueWordList.get(j))) {
					break jLoop;
				} else {
					if(j==uniqueWordList.size()-1) {
						uniqueWordList.add(allWordsList.get(i));
					}
				}
			}
		}
		
//		System.out.println(individualWordList);
	}
	
	public void createWordAssociations() {
		for(int i=0;i<uniqueWordList.size();i++) {
			Word w = new Word(uniqueWordList.get(i));
			wordList.add(w);
		}
		
		for(int i=0;i<uniqueWordList.size();i++) {
			for(int j=0;j<cardList.size();j++) {
				String[] var = cardList.get(j).split("\\s+");
				for(int k=0;k<var.length;k++) {
					if(uniqueWordList.get(i).equals(var[k])) {
						if(k+1 > var.length-1) {
							wordList.get(i).add("-");
						} else {
							wordList.get(i).add(var[k+1]);
							testList.add(var[k+1]);
						}
					}
				}
			}
		}
//		for(int i=0;i<wordList.size();i++) {
//			System.out.println(wordList.get(i).getList());
//		}
	}
	
	public void generateCardN1() {
		int event = 0;
		while(event < 2) {
			if(newCardList.isEmpty()) {
				int ran = random(uniqueWordList.size());
				String s = uniqueWordList.get(ran);
				System.out.println(wordList.get(ran).returnWord());
				if(s != "-") {
					newCardList.add(s);
				}
			} else {
				if(newCardList.get(newCardList.size()-1).equals("-")) {event++;}
				if(event >= 2) {break;}
				for(int i=0;i<uniqueWordList.size();i++) {
					if(newCardList.get(newCardList.size()-1).equals(uniqueWordList.get(i))) {
						int r = wordList.get(i).size();
						if(r <= 0) {
							event++;
						} else {
							String st = wordList.get(i).get(random(wordList.get(i).size()));
							if(st.equals("(h")) {
								newCardList.add("birthday");
							} else {
								newCardList.add(st);
							}
						}
					}
				}
			}
		}
		for(int i=0;i<newCardList.size();i++) {
			newCard += newCardList.get(i) + " ";
		}
		
		System.out.println(newCard);
	}
	
	public void generateCardN2() {
		File birthdayFile = null;
		BufferedReader br = null;
		allWordsList.removeAll(allWordsList);//if formatWords is not removed
		
		try {
			birthdayFile = new File("assets\\birthdaycards.txt");
			br = new BufferedReader(new FileReader(birthdayFile));
		} catch (Exception e) {
			
		}
		String s;
		try  {
			while((s = br.readLine()) != null) {
				String[] var = s.replaceAll("[^a-zA-Z-( ]", " ").toLowerCase().split("\\s+|\\r?\\n");
				for(int i=0;i<var.length;i++) {
					allWordsList.add(var[i]);
					if(i+1 > var.length-1) {
						
					} else {
						if(wordsList.isEmpty()) {//removes duplicates for 2 Word()
							Word ws = new Word(var[i], var[i+1]);
							wordsList.add(ws);
						} else {
							for(int j=0;j<wordsList.size();j++) {
								if(var[i] == wordsList.get(j).returnWord1() && var[i+1] == wordsList.get(j).returnWord2()) {
									
								} else {
									Word ws = new Word(var[i], var[i+1]);
									wordsList.add(ws);
								}
							}
						}
					}
					if(wordList.isEmpty()) {//removes duplicates for 1 Word()
						Word w = new Word(var[i]);
						wordList.add(w);
					} else {
						for(int j=0;j<wordList.size();j++) {
							if(var[i] == wordList.get(j).returnWord()) {
								
							} else {
								Word w = new Word(var[i]);
								wordList.add(w);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			
		}
		
		for(int i=0;i<allWordsList.size();i++) {//get neighbor words for 2
			for(int j=0;j<wordsList.size();j++) {
				if(i+2>allWordsList.size()-1) {// word1, word2}, word3
					
				} else {
					if(allWordsList.get(i) == wordsList.get(j).returnWord1() && allWordsList.get(i+1) == wordsList.get(j).returnWord2()) {
						wordsList.get(j).add(allWordsList.get(i+2));
					}
				}
			}
		}
		
		for(int i=0;i<allWordsList.size();i++) {//get neighbor words for 1
			for(int j=0;j<wordList.size();j++) {
				if(i+1>allWordsList.size()-1) {//word1}, word2
					
				} else {
					if(allWordsList.get(i) == wordList.get(j).returnWord()) {
						wordList.get(j).add(allWordsList.get(i+1));
					}
				}
			}
		}
		
		//move to separate method once can store in storage vs mem
		int event=0;
		while(event<2) {
			if(newCardList.isEmpty()) {
				int ran = random(wordsList.size());
				newCardList.add(wordsList.get(ran).returnWord1());
				newCardList.add(wordsList.get(ran).returnWord2());
			} else {
				iLoop:
				for(int i=0;i<wordsList.size();i++) {
					int n = newCardList.size();
					if(newCardList.get(n-2) == wordsList.get(i).returnWord1() && newCardList.get(n-1) == wordsList.get(i).returnWord2()) {
						int ran2=random(wordsList.get(i).size());
						newCardList.add(wordsList.get(i).get(ran2));
						if(wordsList.get(i).get(ran2) == "-") {
							event++;
							break iLoop;
						}
					} else {
						if(i == wordsList.size()-1) {
							System.out.println("break");
							for(int j=0;j<wordList.size();j++) {
								if(newCardList.get(n-1) == wordList.get(j).returnWord()) {
									newCardList.add(wordList.get(j).get(random(wordList.size())));
								}
							}
//							event++;
						}
					}
				}
				
			}
		}
		for(int i=0;i<newCardList.size();i++) {
			newCard+=newCardList.get(i) + " ";
		}
		System.out.println(newCard);
	}
	
	private static int random(int i) {
		Random num = new Random();
		int r = num.nextInt(i);
		return (r);
	}
}
