import java.awt.Color;

public class Player {
	int x, y;
	int vectX, vectY;
	int keyUP, keyDOWN, keyLEFT, keyRIGHT;
	int codeHor, codeVer;
	Color color;
	//, Color color
	Player(int i, int j, int vX, int vY, int up, int down, int left, int right, int codeHor, int codeVer) {
		this.x = i;
		this.y = j;
		
		this.vectX = vX;
		this.vectY = vY;
		
		this.color = color;

		this.keyUP    = up;
		this.keyDOWN  = down;
		this.keyLEFT  = left;
		this.keyRIGHT = right;
		
		this.codeHor = codeHor;
		this.codeVer = codeVer;
	}
	
	void refresh(int[][] grid) {
		grid[this.x][this.y] = (this.vectX == 0 ? this.codeHor : this.codeVer);				 
		this.x += vectX;
		this.y += vectY;
	}
	
	void checkButton(int button) {
		if (button == this.keyUP) { if (this.vectY == 0) { this.vectX = 0; this.vectY =  1; } }
		else if (button == this.keyDOWN)  {if (this.vectY == 0) { this.vectX = 0; this.vectY = -1; }}
		else if (button == this.keyLEFT)  {if (this.vectX == 0) { this.vectY = 0; this.vectX = -1; }}
		else if (button == this.keyRIGHT) {if (this.vectX == 0) { this.vectY = 0; this.vectX =  1; }}
	}
	
}
