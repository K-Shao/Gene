package parsing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JPanel;

public class GramDisplay extends JPanel {

	private Gram gram;
	
	private int max = 2500;
	private int min = -100;
	
	private int step = 0;
	private int drawWidth = 3;
	
	private int lastBase;
	
	private static final int SCREEN_WIDTH = 1440;
	private static final int SCREEN_HEIGHT = 200;
	
	public GramDisplay (Gram gram) {
		this.gram = gram;
		lastBase = gram.getBases().length;
		
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setVisible(true);
		

		
	}

	@Override
	public void paint (Graphics g) {
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		int position = gram.getLocations()[step];
		short [] currentChannel1 = Arrays.copyOfRange(gram.getChannel1(), position, position + (SCREEN_WIDTH / drawWidth) + 1);
		short [] currentChannel2 = Arrays.copyOfRange(gram.getChannel2(), position, position + (SCREEN_WIDTH / drawWidth) + 1);
		short [] currentChannel3 = Arrays.copyOfRange(gram.getChannel3(), position, position + (SCREEN_WIDTH / drawWidth) + 1);
		short [] currentChannel4 = Arrays.copyOfRange(gram.getChannel4(), position, position + (SCREEN_WIDTH / drawWidth) + 1);

		drawChannel(g, currentChannel1, Color.BLACK);
		drawChannel(g, currentChannel2, Color.GREEN);
		drawChannel(g, currentChannel3, Color.RED);
		drawChannel(g, currentChannel4, Color.BLUE);
		
		drawBasecalls(g);
		
		g.setColor(Color.BLACK);
		g.drawString("Step: " + step, 30, 50);

	}
	
	private void drawBasecalls(Graphics g) {
		g.setColor(Color.BLACK);
		int current = step;
		int leftOffset = gram.getLocations()[current];
		int rightOffset = leftOffset + SCREEN_WIDTH;
		int currentPos;
		while ((currentPos = gram.getLocations()[current] ) <= rightOffset) {
			int x = (currentPos - leftOffset) * drawWidth;
			g.drawString(gram.getBases()[current] + "", x, 35);
			if ((current+1) % 10 == 0) {
				g.drawString((current+1) + "", x-5, 20);
			}
			current++;
		}
		g.drawLine(0, 40, SCREEN_WIDTH, 40);
	}

	public void drawChannel (Graphics g, short[] channelData, Color color) {
		g.setColor(color);
		for (int i = 0; i < channelData.length - 1; i++) {
			short level1 = channelData[i];
			short level2 = channelData[i+1];			
			g.drawLine(i*drawWidth, extrapolate(level1), (i+1)*drawWidth, extrapolate(level2));
		}
	}
	
	private int extrapolate (int level) {
		double percentage = (double) (level - min) / (double) (max + min);
		return (int) ((1-percentage) * SCREEN_HEIGHT);
	}
	
	public void setStep (int step) {
		this.step = step;
	}
	
	public void increaseStep () {
		this.step++;
		if (this.step > lastBase) {
			this.step = lastBase;
		}
	}
	
	public void decreaseStep () {
		this.step--;
		if (this.step < 0) {
			this.step = 0;
		}
	}
	
	

}
