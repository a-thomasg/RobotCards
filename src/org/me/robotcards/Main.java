package org.me.robotcards;

import Managers.CardManager;
import Managers.DictionaryManager;
import Managers.WordManager;

public class Main {
	
	static CardManager cardManager;
	static DictionaryManager dictionaryManager;
	static WordManager wordManager;
	
//	public Main() {
//		cardManager = new CardManager();
//	}
	
	public static void main(String[] args) {
		
//		cardManager = new CardManager();
//		cardManager.evaluateCards();
		
		dictionaryManager = new DictionaryManager();
		dictionaryManager.evaluateWords();
		
		wordManager = new WordManager();
		wordManager.updateSaveFiles();
	}
	
}
