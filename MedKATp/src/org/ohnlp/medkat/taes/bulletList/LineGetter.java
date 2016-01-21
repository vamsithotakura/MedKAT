package org.ohnlp.medkat.taes.bulletList;
/**This class gets the next line in a buffer
 * returning null when the buffer is exhausted
 * @author J W Cooper
 *
 */
public class LineGetter {
	private String buffer;
	private int start;
	private int lineStart;
	private String line;

	public LineGetter(String buffer) {
		this.buffer = buffer;
		start = 0;
	}

	public String nextLine() {
		int index = buffer.indexOf("\n", start);
		if (index > 0) {
			line = buffer.substring(start, index);
			lineStart = start;
			start = index + 1;
			return line;
		} else {
			return null;
		}
	}

	public int getStart() {
		return lineStart;
	}
	public String getLine() {
		return line;
	}
}
