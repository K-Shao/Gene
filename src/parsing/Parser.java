package parsing;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Parser {
	
	public static Gram parseAB1 (String location) throws IOException {
				
		byte [] file = Files.readAllBytes(Paths.get(location));
		
		byte[] programNameInts = Arrays.copyOfRange(file, 0, 4);
		char[] programName = new char[programNameInts.length];
		for (int i = 0; i < programName.length; i++) {
			programName[i] = (char) programNameInts[i];
		}
		
		byte [] programVersionArr = Arrays.copyOfRange(file, 4, 6);
		int programVersion = programVersionArr[0] * 256 + programVersionArr[1];
		
		DirEntry directoryEntryStructure = new DirEntry(Arrays.copyOfRange(file, 6, 34));
		Gram gram = new Gram (directoryEntryStructure, file);
		int directoryOffset = gram.getDirectoryOffset();
		int numElements = gram.getNumDirectoryEntries();
		gram.fillDirectories(Arrays.copyOfRange(file, directoryOffset, directoryOffset + (28 * numElements)));
				
		DataSet channel1Signal = gram.getData("DATA", 9);
		byte [] channel1SignalArray = channel1Signal.getRawData();
		ByteBuffer bb = ByteBuffer.wrap(channel1SignalArray);
		short [] channel1 = new short[channel1Signal.getData().length];
		for (int i = 0; i < channel1.length; i++) {
			channel1[i] = bb.getShort();
		}
		bb.clear();

		DataSet channel2Signal = gram.getData("DATA", 10);
		byte [] channel2SignalArray = channel2Signal.getRawData();
		bb = ByteBuffer.wrap(channel2SignalArray);
		short [] channel2 = new short[channel2Signal.getData().length];
		for (int i = 0; i < channel2.length; i++) {
			channel2[i] = bb.getShort();
		}
		bb.clear();

		DataSet channel3Signal = gram.getData("DATA", 11);
		byte [] channel3SignalArray = channel3Signal.getRawData();
		bb = ByteBuffer.wrap(channel3SignalArray);
		short [] channel3 = new short[channel3Signal.getData().length];
		for (int i = 0; i < channel3.length; i++) {
			channel3[i] = bb.getShort();
		}
		bb.clear();

		DataSet channel4Signal = gram.getData("DATA", 12);
		byte [] channel4SignalArray = channel4Signal.getRawData();
		bb = ByteBuffer.wrap(channel4SignalArray);
		short [] channel4 = new short[channel4Signal.getData().length];
		for (int i = 0; i < channel4.length; i++) {
			channel4[i] = bb.getShort();
		}
		bb.clear();
		
		byte [] basesBytes = gram.getData("PBAS", 2).getRawData();
		char [] bases = new char [basesBytes.length];
		for (int i = 0; i < bases.length; i++) {bases[i] = (char)basesBytes[i];}
		
		
		DataSet locationSet = gram.getData("PLOC", 2);
		bb = ByteBuffer.wrap(locationSet.getRawData());
		short [] locations = new short[locationSet.getData().length];
		for (int i = 0; i < locations.length; i++) {
			locations[i] = bb.getShort();
		}
		bb.clear();
		
		System.out.println(gram.lookup("PCON", 2));
		byte [] q = gram.getData("PCON", 2).getRawData();
		short [] quality = new short[q.length];
		for (int i = 0; i < quality.length; i++) {
			quality[i]*=4;
		}
		
		
		gram.setBases(bases);
		gram.setLocations(locations);
		gram.setQualities(quality);
		gram.setChannels(channel1, channel2, channel3, channel4);

		return gram;
		
	}
	
}
