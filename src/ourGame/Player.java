package ourGame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.input.KeyCode;

public class Player extends Sprite{
	
	private KeyCode charL;
	private KeyCode charR;
	private int vel;
	int numPlayer;
	int rLimit;
	int lLimit;
	
	public Player(int numPlayer, int initPosX, int initPosY, KeyCode left, KeyCode right, Image img) throws IOException {
		
		super(initPosX, initPosY, img);
		this.vel = 15;
		this.charL = left;
		this.charR = right;
		this.numPlayer = numPlayer;
		
		
	}
	
	public Player(int numPlayer, int initPosX, int initPosY, KeyCode left, KeyCode right, Image img, int lLimit, int rLimit ) throws IOException {
		super(initPosX,initPosY,img);
		
		this.vel = 20;
		this.charL = left;
		this.charR = right;
		this.numPlayer = numPlayer;
		this.lLimit = lLimit;
		this.rLimit = rLimit;
		
		
	}
	
	Image getImage() {
		return this.img;
	}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	int getNumPlayer() {return this.numPlayer;}
	KeyCode getCharL() {return this.charL;}
	KeyCode getCharR() {return this.charR;}

	
	public void movePlayer(int dir) {
		if((dir > 0 && this.getX() < (rLimit-vel-img.getWidth(null))) ||
				(dir < 0 && this.getX() > (lLimit+vel)))
			this.x += (this.vel*dir);
	}


	
}
