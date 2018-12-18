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
		assertEquals(dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(), result);
	}
	
	@Test
	void addNewWordNodeNumber() {
		LexiNode dictionnary = new LexiNode();
		
		dictionnary.LoadFile("\\test\\exemple1");
		
		int totalNode = 0;
		
		totalNode += dictionnary.getChildren().size();
		
		assertEquals(totalNode, 17);
	}
	
	@Test
	void addExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findNewWordBranch(lexiWord2, 0);
		
		String result = "testtester ca"; 
		assertEquals(dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(), result);
	}
	
	@Test
	void UpdateExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.updateLexiWord(lexiWord2);
		
		String result = "testtester ca"; 
		assertEquals(dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(), result);
	}
	
	@Test
	void UpdateUnexistingExistingWord() {
		LexiNode dictionnary = new LexiNode();
		
		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);
		
		LexiWord lexiWord2 = new LexiWord("asdf", "tester ca");
		dictionnary.updateLexiWord(lexiWord2);
		
		String result = "testallo"; 
		assertEquals(dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(), result);
	}
	
	@Test
	void loadTest() {
		
	}

}
