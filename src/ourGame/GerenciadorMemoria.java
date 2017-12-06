package ourGame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GerenciadorMemoria implements Runnable {

	Counter cont;
	BackGround bg1;
	BackGround bg2;
	BackGround bg3; 
	boolean colide;

	public GerenciadorMemoria(BackGround bg1, BackGround bg2, BackGround bg3) {
		this.bg1 = bg1;
		this.bg2 = bg2;
		this.bg3 = bg3;
		this.colide = false;
	}

	@Override
	public void run() {
		this.cont = new Counter();
		Thread t = new Thread(this.cont);
		t.start();
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			if (this.cont.getCounter()%20 == 0 && this.cont.getCounter() < 300 && this.cont.getCounter() > 0) {
				bg1.setPrintPw(false);
				bg2.setPrintPw(false);
				bg3.setPrintPw(false);
				
				
				while(!bg1.isPrintPw() || !bg2.isPrintPw() || !bg3.isPrintPw()) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Image i = null;
				try {
					i = ImageIO.read(getClass().getResource("mem.png"));
				} catch (IOException e) {}
				
				Memoria m = new Memoria(0,740,i);
				m.setPlace(0);
				bg1.setMem(m);
				bg2.setMem(m);
				bg3.setMem(m);
				
			}
		}
	}
}
