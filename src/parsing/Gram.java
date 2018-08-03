package parsing;

import java.util.Arrays;

public class Gram {

	private DirEntry directoryEntryStructure;
	private DirEntry[] directories;
	private byte[] data;

	private short[] channel1;
	private short[] channel2;
	private short[] channel3;
	private short[] channel4;

	private char[] bases;
	private short[] locations;
	private short[] qualities;

	public Gram (DirEntry des, byte[] data) {
		directoryEntryStructure = des;
		directories = new DirEntry[directoryEntryStructure.getNumElements()];
		this.data = data;
	}

	public void setChannels(short[] channel1, short[] channel2, short[] channel3, short[] channel4) {
		this.channel1 = channel1;
		this.channel2 = channel2;
		this.channel3 = channel3;
		this.channel4 = channel4;
	}

	public short[] getChannel1() {
		return channel1;
	}

	public short[] getChannel2() {
		return channel2;
	}

	public short[] getChannel3() {
		return channel3;
	}

	public short[] getChannel4() {
		return channel4;
	}

	public char[] getBases() {
		return bases;
	}

	public void setBases(char[] bases) {
		this.bases = bases;
	}

	public short[] getLocations() {
		return locations;
	}

	public void setLocations(short[] locations) {
		this.locations = locations;
	}

	public DirEntry getDirectoryEntryStructure() {
		return directoryEntryStructure;
	}

	public int getDirectoryOffset() {
		return directoryEntryStructure.getDataOffset();
	}

	public int getNumDirectoryEntries() {
		return directoryEntryStructure.getNumElements();
	}

	public DataSet getData(String name, int number) {
		return new DataSet(lookup(name, number), data);
	}

	public DataSet getData(DirEntry entry) {
		return new DataSet(entry, data);
	}

	public DirEntry lookup(String name, int number) {
		for (DirEntry d : directories) {
			if (d.getName().equals(name) && number == d.getNumber()) {
				return d;
			}
		}
		return null;
	}

	public void fillDirectories(byte[] data) {
		for (int i = 0; i < getNumDirectoryEntries(); i++) {
			directories[i] = new DirEntry(Arrays.copyOfRange(data, 28 * i, 28 * (i + 1)));
		}
	}

	public short[] getQualities() {
		return qualities;
	}

	public void setQualities(short[] qualities) {
		this.qualities = qualities;
	}

}
