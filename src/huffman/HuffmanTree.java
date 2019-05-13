package huffman;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;

public class HuffmanTree {

	HuffmanTree left, right;
	Character symbol;

	public HuffmanTree() {
		left = null;
		right = null;
		symbol = null;
	}

	/**
	 * Convenience method to check if a node has a left branch
	 * 
	 * @return true if the node has a left branch, false otherwise
	 */
	private boolean hasLeft() {
		return left != null;
	}

	/**
	 * Convenience method to check if a node has a right branch
	 * 
	 * @return true if the node has a right branch, false otherwise
	 */
	private boolean hasRight() {
		return right != null;
	}

	/**
	 * Convenience method to check if a node is a leaf node.
	 * 
	 * @return true if the node is a leaf node, false otherwise
	 */
	private boolean isLeaf() {
		return right == null && left == null;
	}

	/**
	 * Check if the tree is empty, i.e. contains no symbols.
	 * 
	 * @return true is the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return right == null && left == null && symbol == null;
	}

	/**
	 * Clear the tree from its content. All symbols are removed from the tree and
	 * the tree is then empty.
	 */
	public void clear() {
		left = null;
		right = null;
		symbol = null;
	}

	/**
	 * Build a Huffman tree from an encoding. The encoding is a Map of the form:
	 * {'A':"000",'B':"001",'C':"010",'D':"011",'E':"10",'F':"11"}
	 * 
	 * @param encoding
	 */
	public void setCoding(Map<Character, String> encoding) {
		this.clear();
		for (Character key : encoding.keySet()) {
			String code = encoding.get(key);
			System.out.println(code);
			insert(key, code);
		}
	}

	/**
	 * Convenience method to insert a symbol into the Huffman tree given a path
	 * (e.g. "0010").
	 * 
	 * @param key
	 *            the symbol to be inserted into the tree
	 * @param code
	 *            the path to insert the symbol into the tree.
	 */
	private void insert(Character key, String code) {
		if (isEmpty()) {
			if (!code.isEmpty()) {
				if (code.charAt(0) == '0') { // insert left
					left = new HuffmanTree();
					left.insert(key, code.substring(1));
				} else if (code.charAt(0) == '1') { // insert right
					right = new HuffmanTree();
					right.insert(key, code.substring(1));
				} else { // not binary
					throw new IllegalArgumentException();
				}
			} else { // arrived at a end of coding word and at a leaf
				symbol = key;
			}
		} else {
			if (code.isEmpty()) { // end of coding word but not at a leaf so error
				throw new IllegalArgumentException();
			} else if (code.charAt(0) == '0') { // insert left
				if (hasLeft()) { // branch has been created previously
					left.insert(key, code.substring(1));
				} else { // no branch exists yet
					left = new HuffmanTree();
					left.insert(key, code.substring(1));
				}
			} else if (code.charAt(0) == '1') { // insert right
				if (hasRight()) { // branch has been created previously
					right.insert(key, code.substring(1));
				} else { // no branch exists yet
					right = new HuffmanTree();
					right.insert(key, code.substring(1));
				}
			} else { // not binary
				throw new IllegalArgumentException();
			}
		}

	}

	public static Map<Character, Double> getCharFrequencies(String text) {
		Map<Character, Integer> initial = new HashMap<Character, Integer>();
		Integer lengthOfText = text.length();
		Character currentChar;
		// Iterate through, summing all the chars
		for (int i = 0; i < text.length(); i++) {
			currentChar = new Character(text.charAt(i));
			if (initial.containsKey(currentChar)) {
				initial.put(currentChar, initial.get(currentChar) + 1);
			} else {
				initial.put(currentChar, 1);
			}
		}
		// Divide all sums by the total length
		Iterator<Entry<Character, Integer>> hashIt = initial.entrySet().iterator();
		Map<Character, Double> toReturn = new HashMap<Character, Double>();
		while (hashIt.hasNext()) {
			Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)hashIt.next();
			toReturn.put(pair.getKey(), new Double((double)pair.getValue() / (double)lengthOfText));
		}
		return toReturn;
	}
	
	public Map<Character, String> getEncoding() {
		Map<Character, String> toReturn = new HashMap<Character, String>();
		// If this is a leaf, return just our symbol with a blank string
		if (this.isLeaf()) {
			if (!this.isEmpty()) {
				toReturn.put(this.symbol, new String(""));
			}
		} else {
			// Call this function on the left and/or right branches and then append those results with the correct number
			if (this.hasLeft()) {
				Map<Character, String> leftReturn = this.left.getEncoding();
				Iterator<Entry<Character, String>> hashIt = leftReturn.entrySet().iterator();
				while (hashIt.hasNext()) {
					Map.Entry<Character, String> pair = (Map.Entry<Character, String>)hashIt.next();
					toReturn.put(pair.getKey(), new String("0").concat(pair.getValue()));
				}
			}
			if (this.hasRight()) {
				Map<Character, String> rightReturn = this.right.getEncoding();
				Iterator<Entry<Character, String>> hashIt = rightReturn.entrySet().iterator();
				while (hashIt.hasNext()) {
					Map.Entry<Character, String> pair = (Map.Entry<Character, String>)hashIt.next();
					toReturn.put(pair.getKey(), new String("1").concat(pair.getValue()));
				}
			}
		}
		return toReturn;
	}
	
	public String encodeText(String text) {
		Map<Character, String> characterMapping = this.getEncoding();
		String binaryString = "";
		for (Character currentChar: text.toCharArray()) {
			if (!characterMapping.containsKey(currentChar)) {
				throw new IllegalArgumentException();
			}
			binaryString = binaryString.concat(characterMapping.get(currentChar));
		}
		return binaryString;
	}
	
	public String decodeText(String text) {
		HuffmanTree currentNodePointer = this;
		String outString = new String("");
		for (Character currentChar: text.toCharArray()) {
			// Go right
			if (currentChar == '0') {
				if (!currentNodePointer.hasLeft()) {
					throw new IllegalArgumentException();
				}
				currentNodePointer = currentNodePointer.left;
			// Go left
			} else if (currentChar == '1') {
				if (!currentNodePointer.hasRight()) {
					throw new IllegalArgumentException();
				currentNodePointer = currentNodePointer.right;
			}
			if (currentNodePointer.isLeaf()) {
				outString = outString.concat(Character.toString(currentNodePointer.symbol));
				currentNodePointer = this;
			}
			// If we get to the end of the string and the pointer hasn't reset, throw an exception
			if (i == text.length() - 1 && currentNodePointer != this) {
				throw new IllegalArgumentException();
			}
		}
		
		return outString;
	}
}
