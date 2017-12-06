package ourGame;

import java.awt.Image;

public class Memoria extends Sprite{
	private int place;
	public Memoria(int x, int y, Image img) {
		super(x,y,img);
		this.place = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	
}
