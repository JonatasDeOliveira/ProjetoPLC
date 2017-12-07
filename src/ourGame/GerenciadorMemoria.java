package ourGame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GerenciadorMemoria implements Runnable {

	Counter cont;
	BackGround bg1;
	BackGround bg2;
	BackGround bg3;
	private boolean endOfGame; 
	private GeradorPwUp gpu;

	public GerenciadorMemoria(BackGround bg1, BackGround bg2, BackGround bg3, GeradorPwUp gpu) {
		this.bg1 = bg1;
		this.bg2 = bg2;
		this.bg3 = bg3;
		this.endOfGame = false;
		this.bg1.setEndOfGame(endOfGame);
		this.bg2.setEndOfGame(endOfGame);
		this.bg3.setEndOfGame(endOfGame);
		this.gpu = gpu;
	}

	@Override
	public void run() {
		this.cont = new Counter();
		Thread t = new Thread(this.cont);
		t.start();
		while (!this.endOfGame) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			if(this.cont.getCounter() >= 200) {
				this.endOfGame = true;
				bg1.setPrintPw(false);
				bg2.setPrintPw(false);
				bg3.setPrintPw(false);
				bg1.pw1.disable();
				bg2.pw1.disable();
				bg3.pw1.disable();
			}
			
			if (this.cont.getCounter()%20 == 0 && this.cont.getCounter() < 300 && this.cont.getCounter() > 0 && !this.isEndOfGame()) {
				bg1.setPrintPw(false);
				bg2.setPrintPw(false);
				bg3.setPrintPw(false);
				
				while(!bg1.isPrintPw() || !bg2.isPrintPw() || !bg3.isPrintPw()) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {}
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
				this.gpu.setOthersIsPaused(-1, false);
				bg1.setPrintPw(true);
				bg2.setPrintPw(true);
				bg3.setPrintPw(true);
				bg1.setGameRunning(true);
				bg2.setGameRunning(true);
				bg3.setGameRunning(true);
				
			}
		}
		this.bg1.setEndOfGame(this.endOfGame);
		this.bg2.setEndOfGame(this.endOfGame);
		this.bg3.setEndOfGame(this.endOfGame);
	}

	public boolean isEndOfGame() {
		return endOfGame;
	}
}
