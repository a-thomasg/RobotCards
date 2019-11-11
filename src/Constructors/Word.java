package Constructors;

import java.util.ArrayList;
import java.util.List;

public class Word {
	
	List<String> nextWord = new ArrayList<String>();
	String word1;
	String word2;

	
	public Word(String word1) {
		this.word1 = word1;
	}
	public Word(String word1, String word2) {
		this.word1 = word1;
		this.word2 = word2;
	}
	
	public void add(String str) {
		if(str.equals("p") || str.equals("c") || str.equals("s") || str.equals("f") || str.equals("b") || str.equals("u") || str.equals("n") || str.equals("m")
				|| str.equals("v") || str.equals("e") || str.equals("r") || str.equals("k") || str.equals("h") || str.equals("o") || str.equals("g")) {
			nextWord.add("(" + str);
		} else {
			nextWord.add(str);
		}
		
	}
	
	public String get(int i) {
		return nextWord.get(i);
	}
	
	public int size() {
		return nextWord.size();
	}
	
	public List<String> getList() {
		return nextWord;
	}
	public String returnWord() {
		return word1.toString();
	}
	public String returnWords() {
		return word1.toString() + " " + word2.toString();
	}
	public String returnWord1() {
		return word1.toString();
	}
	public String returnWord2() {
		return word2.toString();
	}
	
}
