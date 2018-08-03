package parsing;

import java.util.Arrays;

public class DataSet {
	
	private byte [][] data;
	private byte [] rawData;
	private int type;
	
	public DataSet (DirEntry directory, byte [] fullData) {
		this.type = directory.getElementType();
		int offset = directory.getDataOffset();
		int numElements = directory.getNumElements();
		int elementSize = directory.getElementSize();
		this.rawData = Arrays.copyOfRange(fullData, offset, offset + (numElements * elementSize));
		this.data = new byte [numElements][elementSize];
		
		for (int i = 0; i < numElements; i++) {
			for (int j = 0; j < elementSize; j++) {
				this.data[i][j] = rawData[i * elementSize + j];
			}
		}
	}

	public byte[][] getData() {
		return data;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public int getType () {
		return type;
	}

}
