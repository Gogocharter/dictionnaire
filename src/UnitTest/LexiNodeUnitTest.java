
package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import dictionnaire.LexiNode;
import dictionnaire.LexiWord;

class LexiNodeUnitTest {

	@Test
	void addNewWord() {
		LexiNode dictionnary = new LexiNode();
		LexiWord lexiWord = new LexiWord("test", "allo");

		dictionnary.findAddorUpdateBranch(lexiWord, 0);

		String result = "testallo";
		assertEquals(
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(),
				result);
	}

	@Test
	void addNewWordNodeNumber() {
		LexiNode dictionnary = new LexiNode();

		dictionnary.LoadFile(System.getProperty("user.dir") + "\\bin\\UnitTest\\exemple1.txt");

		int totalNode = size(dictionnary);

		// total similar letter from file
		// 'test' + 'er' + 'orons' = 11

		int result = 11;
		assertEquals(totalNode, result);
	}

	int size(LexiNode node) {
		int total = 0;

		for (LexiNode child : node.getChildren()) {
			total++;
			total += size(child);
		}

		return total;
	}

	@Test
	void addExistingWord() {
		LexiNode dictionnary = new LexiNode();

		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findAddorUpdateBranch(lexiWord, 0);

		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findAddorUpdateBranch(lexiWord2, 0);

		String result = "testtester ca";
		assertEquals(result,
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition());
	}

	@Test
	void UpdateExistingWord() {
		LexiNode dictionnary = new LexiNode();

		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findAddorUpdateBranch(lexiWord, 0);

		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findAddorUpdateBranch(lexiWord2, 0);

		String result = "testtester ca";
		assertEquals(
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(),
				result);
	}

	@Test
	void SearchOk() {
		LexiNode dictionnary = new LexiNode();

		dictionnary.LoadFile(System.getProperty("user.dir") + "\\bin\\UnitTest\\exemple1.txt");
		
		LinkedList<LexiWord> list = dictionnary.Search("test");
		
		LexiNode dictionnary2 = new LexiNode();
		
		LexiWord word1 = new LexiWord("test", "tester de quoi");
		LexiWord word2 = new LexiWord("tester", "tester de quoi");
		LexiWord word3 = new LexiWord("testorons", "tester de quoi");
		
		LinkedList<LexiWord> result = new LinkedList<LexiWord>();
		dictionnary2.findAddorUpdateBranch(word1, 0);
		dictionnary2.findAddorUpdateBranch(word2, 0);
		dictionnary2.findAddorUpdateBranch(word3, 0);
		
		result.add(word1);
		result.add(word2);
		result.add(word3);
		
		for (int i = 0; i < list.size(); i++) {
			assertEquals(result.get(i).getWord(), list.get(i).getWord());
			assertEquals(result.get(i).getDefenition(), list.get(i).getDefenition());
		}
		
	}

}
