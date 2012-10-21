import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * <applet code="Sample" width=600 height=600>
 * </applet>
*/

public class Tron extends Applet implements Runnable {
	Player B, R;
	
	int[][] grid;
	
	String winner;
	
	int rows = 15, columns = 15;
	
	Thread t = null;
	
	boolean stopFlag;
	
	public void init() {
		setFocusable(true);
		
		setBackground(Color.black);
		setForeground(Color.green);
		
		grid = new int[rows][columns];
		
		B = new Player(rows - 2, columns - 2, -1, 0, 40, 38, 37, 39, 2, 1);
		R = new Player(1, 1, 0, 1, (int)'S', (int)'W', (int)'A', (int)'D', 4, 3);
		
		addKeyListener(new KeyListener() {
			public void keyPressed (KeyEvent e){  
			} 
			
			public void keyTyped (KeyEvent e) {  
			}
			
			public void keyReleased (KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				B.checkButton(keyCode);
				R.checkButton(keyCode);
			}  
		}) ; 
	}
	
	public void start() {
		t = new Thread(this);
		stopFlag = false;
		t.start();
	}
	
	public void run() {
		for ( ; ; ) {
			try {
				repaint();
				
				if (stopFlag) {
					break;
				}
				
				Thread.sleep(400);
				
				B.refresh(grid);
				R.refresh(grid);
				
				if (grid[B.x][B.y] != 0) { grid[B.x][B.y] = 5; stopFlag = true; winner = "RED"; }
				if (grid[R.x][R.y] != 0) { grid[R.x][R.y] = 5; stopFlag = true; winner = "BLU"; }
				
				if (B.x == R.x && B.y == R.y) { grid[B.x][B.y] = 5; stopFlag = true; winner = "NBD"; }
				
				if (B.x < 1 || B.x > (rows - 1) || B.y < 1 || B.y > (columns - 1)) {
					grid[B.x][B.y] = 5; stopFlag = true; winner = "RED"; 
				}
				
				if (R.x < 1 || R.x > (rows - 1) || R.y < 1 || R.y > (columns - 1)) { 
					grid[R.x][R.y] = 5; stopFlag = true; winner = "BLUE"; 
				}
				
			} catch (Exception E) {}
		}
	}
	
	public void paint(Graphics g) {
		if (winner != null) {
			g.drawString(winner + " WIN", 310, 310);
		}
		
		
		for (int i = 0 ; i < rows ; i++) {
			for (int j = 0 ; j < columns ; j++) {
				char chr = (char) (Math.random() * 26 + 'a');
				
				if (j == 0 || j == (columns - 1)) { chr = '—'; g.setColor(Color.gray); }
				if (i == 0 || i == (rows - 1))    { chr = '|'; g.setColor(Color.gray); }
				
				if (grid[i][j] > 0) {
					switch (grid[i][j]) {
						case 1: g.setColor(Color.blue); chr = '—'; break;
						case 2: g.setColor(Color.blue); chr = '|'; break;
						case 3: g.setColor(Color.red);  chr = '—'; break;
						case 4: g.setColor(Color.red);  chr = '|'; break;
						case 5: g.setColor(Color.white);chr = 'O'; break;
					}
				}
				
				if ((i == B.x && j == B.y) && grid[i][j] < 5) {
					if (B.vectX != 0) {
						chr = '—';
					} else {
						chr = '|';
					}
					
					g.setColor(Color.blue);
				}
				
				if ((i == R.x && j == R.y) && grid[i][j] < 5) {
					if (R.vectX != 0) {
						chr = '—';
					} else {
						chr = '|';
					}
					
					g.setColor(Color.red);
				}
				
				int shiftX = 20;
				int shiftY = 20;
			
				
				g.drawString(Character.toString(chr), shiftX + i * 20, shiftY + j * 20);
				g.setColor(Color.green);
			}
		}
	}
	
	public void stop() {
		stopFlag = true;
		t = null;
	}	
}
