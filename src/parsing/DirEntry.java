package parsing;

import java.nio.ByteBuffer;

public class DirEntry {
	
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public int getElementType() {
		return elementType;
	}
	public int getElementSize() {
		return elementSize;
	}
	public int getNumElements() {
		return numElements;
	}
	public int getDataSize() {
		return dataSize;
	}
	public int getDataOffset() {
		return dataOffset;
	}
	public int getDataHandle() {
		return dataHandle;
	}
	private String name;
	private int number;
	private int elementType;
	private int elementSize;
	private int numElements;
	private int dataSize;
	private int dataOffset;
	private int dataHandle;
	
	public DirEntry (byte [] data) {
		
		assert data.length == 28;
		ByteBuffer bb = ByteBuffer.wrap(data);
		
		StringBuilder nameBuilder = new StringBuilder();
		for (int i = 0; i < 4; i++) { nameBuilder.append((char) bb.get()); }
		this.name = nameBuilder.toString();
		
		this.number = bb.getInt();
		this.elementType = bb.getShort();
		this.elementSize = bb.getShort();
		this.numElements = bb.getInt();
		this.dataSize = bb.getInt();
		this.dataOffset = bb.getInt();
		this.dataHandle = bb.getInt();

	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder("Directory Entry { \n");
		sb.append("\tName: "); sb.append(this.name); sb.append("\n");
		sb.append("\tNumber: "); sb.append(this.number); sb.append("\n");
		sb.append("\tElementType: "); sb.append(this.elementType); sb.append("\n");
		sb.append("\tElementSize: "); sb.append(this.elementSize); sb.append("\n");
		sb.append("\tNumber of Elements: "); sb.append(this.numElements); sb.append("\n");
		sb.append("\tData Size: "); sb.append(this.dataSize); sb.append("\n");
		sb.append("\tData Offset: "); sb.append(this.dataOffset); sb.append("\n");
		sb.append("\tData Handle: "); sb.append(this.dataHandle); sb.append("\n }\n");

		return sb.toString();
	}
	

}
