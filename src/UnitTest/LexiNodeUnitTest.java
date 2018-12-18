
package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dictionnaire.LexiNode;
import dictionnaire.LexiWord;

class LexiNodeUnitTest {

	@Test
	void addNewWord() {
		LexiNode dictionnary = new LexiNode();
		LexiWord lexiWord = new LexiWord("test", "allo");

		dictionnary.findNewWordBranch(lexiWord, 0);

		String result = "testallo";
		assertEquals(
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(),
				result);
	}

	@Test
	void addNewWordNodeNumber() {
		LexiNode dictionnary = new LexiNode();

		dictionnary.LoadFile(System.getProperty("user.dir") +"\\bin\\UnitTest\\exemple1.txt");

		int totalNode = size(dictionnary);

		//total similar letter from file
		// ' ' + 'test' + 'er' + 'orons' + 'dvadft' = 18
		
		int result = 18;
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
		dictionnary.findNewWordBranch(lexiWord, 0);

		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findNewWordBranch(lexiWord2, 0);

		String result = "testtester ca";
		assertEquals(result,
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition());
	}

	@Test
	void UpdateExistingWord() {
		LexiNode dictionnary = new LexiNode();

		LexiWord lexiWord = new LexiWord("test", "allo");
		dictionnary.findNewWordBranch(lexiWord, 0);

		LexiWord lexiWord2 = new LexiWord("test", "tester ca");
		dictionnary.findNewWordBranch(lexiWord2,0);

		String result = "testtester ca";
		assertEquals(
				dictionnary.SearchSingleWord("test").getWord() + dictionnary.SearchSingleWord("test").getDefenition(),
				result);
	}

}
