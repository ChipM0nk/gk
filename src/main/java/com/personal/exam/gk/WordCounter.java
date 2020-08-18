package com.personal.exam.gk;

import java.util.HashMap;
import java.util.Map;


public class WordCounter {

	public static void countWords(String input) {
		String[] words = input.split(" ");
		
		Map<String,Integer> wordCount = new HashMap<String, Integer>();
		
		for(String word : words) {
			word = word.toLowerCase();
			
			wordCount.computeIfPresent(word, (k,v) -> v+1);
			wordCount.putIfAbsent(word, 1);
		}
		
		System.out.println(wordCount.toString());
	}
	
	public static void main(String[] args) {
		WordCounter.countWords("Hello hi there. Hi Hello");
	}
}
