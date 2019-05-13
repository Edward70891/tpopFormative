package huffman;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Question_2_iii_test {

	@Test
	public void testEncodeText() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();

		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		
		assertEquals("", tree.encodeText(""));
		assertEquals("1100001110", tree.encodeText("FADE"));
		assertEquals("0000010100111011", tree.encodeText("ABCDEF"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testEncodeTextError() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();

		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		tree.encodeText("G");
	}
	
}
