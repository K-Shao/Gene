package parsing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class ProjectFrame extends JFrame {
	
	private List<GramDisplay> panels = new ArrayList<> ();
	
	public ProjectFrame (Gram...grams) {
		super("Project Frame");
		
		this.setSize(1440, 900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		for (Gram gram: grams) {
			panels.add(new GramDisplay(gram));
		}
		
		for (GramDisplay display: panels) {
			this.add(display);
		}

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					for (GramDisplay display: ProjectFrame.this.panels) display.increaseStep();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					for (GramDisplay display: ProjectFrame.this.panels) display.decreaseStep();
				}
				for (GramDisplay display: ProjectFrame.this.panels) display.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

}
