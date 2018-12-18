package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dictionnaire.LexiNode;
import dictionnaire.LexiWord;

class LexiNodeTest {
	
	@Test
	void addNewWord() {
		LexiNode dictionnary = new LexiNode();
		LexiWord lexiWord = new LexiWord("test", "allo");
		
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		String result = "testallo"; 
		assertEquals(dictionnary.SearchSingleWord(lexiWord).getWord() + dictionnary.SearchSingleWord(lexiWord).getDefenition(), result);
	}
	
	@Test
	void addExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findNewWordBranch(lexiWord2, 0);
		
		String result = "testtester ca"; 
		assertEquals(dictionnary.SearchSingleWord(lexiWord).getWord() + dictionnary.SearchSingleWord(lexiWord).getDefenition(), result);
	}
	
	@Test
	void UpdatingExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.updateLexiWord(lexiWord2);
		
		String result = "testtester ca"; 
		assertEquals(dictionnary.SearchSingleWord(lexiWord).getWord() + dictionnary.SearchSingleWord(lexiWord).getDefenition(), result);
	}
	
	@Test
	void UpdatingExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.updateLexiWord(lexiWord2);
		
		String result = "testtester ca"; 
		assertEquals(dictionnary.SearchSingleWord(lexiWord).getWord() + dictionnary.SearchSingleWord(lexiWord).getDefenition(), result);
	}
	
	@Test
	void loadTest() {
		
	}

}
