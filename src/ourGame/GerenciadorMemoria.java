package ourGame;

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
			
			if (this.cont.getCounter() == 2) {
				bg1.setPrintPw(false);
				bg2.setPrintPw(false);
				bg3.setPrintPw(false);
			}
		}
	}
}
