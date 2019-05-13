package huffman;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Question_2_iv_test {

	@Test
	public void testDecodeText() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();

		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		
		assertEquals("", tree.decodeText(""));
		assertEquals("FADE", tree.decodeText("1100001110"));
		assertEquals("ABCDEF", tree.decodeText("0000010100111011"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDecodeTextErrorToShort() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();

		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		tree.decodeText("000001010011101");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDecodeTextErrorToLong() {
		HuffmanTree tree = new HuffmanTree();
		Map<Character, String> encoding = new HashMap<>();

		encoding.put('A', "000");
		encoding.put('B', "001");
		encoding.put('C', "010");
		encoding.put('D', "011");
		encoding.put('E', "10");
		encoding.put('F', "11");
		tree.setCoding(encoding);
		tree.decodeText("00000101001110111");
	}

}
