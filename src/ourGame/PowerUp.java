package ourGame;

import java.awt.Image;
import java.io.IOException;

public class PowerUp extends Sprite{
	int vel;
	private boolean enable;
	private int tipo;
	
	public PowerUp(int x, int y,Image img, int tipo) throws IOException {
		super(x,y,img);
		this.enable = false;
		this.tipo = tipo;
	}
	
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Image getImage() {return img;}
	public boolean isEnable() {return this.enable;}
	public void disable() {this.enable = false;}
	public void enable() {this.enable = true;}
	public void setVel(int vel) {this.vel=vel;}


	public void setY(int i) {
		this.y = i;
	}
	public int getTipo() {
		return this.tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
