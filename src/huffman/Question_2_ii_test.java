package huffman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Question_2_ii_test {
	
	@Test
	public void testGetEncoding() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();
		Map<Character, String> expectedEncoding = new HashMap<>();
		
		// Check empty tree
		assertEquals(expectedEncoding, tree.getEncoding());		
		
		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		
		expectedEncoding.put('A', "000");
		expectedEncoding.put('B', "001");
		expectedEncoding.put('C', "010");
		expectedEncoding.put('D', "011");
		expectedEncoding.put('E', "10");
		expectedEncoding.put('F', "11");
		assertEquals(expectedEncoding, tree.getEncoding());
		
		encoding = new HashMap<>();
		encoding.put('A', "0");
		encoding.put('B', "10");
		encoding.put('C', "110");
		encoding.put('D', "1110");
		encoding.put('E', "11110");
		encoding.put('F', "111110");
		tree.setCoding(encoding);
		
		assertNotEquals(expectedEncoding, tree.getEncoding());
		expectedEncoding = new HashMap<>();
		expectedEncoding.put('A', "0");
		expectedEncoding.put('B', "10");
		expectedEncoding.put('C', "110");
		expectedEncoding.put('D', "1110");
		expectedEncoding.put('E', "11110");
		expectedEncoding.put('F', "111110");
		assertEquals(expectedEncoding, tree.getEncoding());
	}

}
