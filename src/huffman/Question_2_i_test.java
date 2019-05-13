package huffman;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Question_2_i_test {

	@Test
	public void testGetCharFrequancies() {
		Map<Character,Double> expectedMap = new HashMap<Character,Double>();
		String text = "aa, b ccc.";
		expectedMap.put('a', 2/10.0);
		expectedMap.put(' ', 2/10.0);
		expectedMap.put(',', 1/10.0);
		expectedMap.put('b', 1/10.0);
		expectedMap.put('c', 3/10.0);
		expectedMap.put('.', 1/10.0);
		
		Map<Character,Double> actualMap = HuffmanTree.getCharFrequencies(text);
		assertEquals(expectedMap.size(), actualMap.size());
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		for(Character c:expectedMap.keySet()){
			assertEquals(expectedMap.get(c), actualMap.get(c), 0.000000000001);
		}
	}
	
	@Test
	public void testGetCharFrequanciesEmptyText() {
		assertEquals((Map<Character,Double>)new HashMap<Character,Double>(), HuffmanTree.getCharFrequencies(""));
	}
	
}
