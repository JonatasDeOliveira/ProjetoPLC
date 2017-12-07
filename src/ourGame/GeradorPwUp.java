package ourGame;

public class GeradorPwUp{
	public Counter cont1, cont2, cont3;
	
	public GeradorPwUp() {
		this.cont1 = new Counter();
		this.cont2 = new Counter();
		this.cont3 = new Counter();
	}
	
	public Counter getCont1() {
		return cont1;
	}

	public void setCont1(Counter cont1) {
		this.cont1 = cont1;
	}

	public Counter getCont2() {
		return cont2;
	}

	public void setCont2(Counter cont2) {
		this.cont2 = cont2;
	}

	public Counter getCont3() {
		return cont3;
	}

	public void setCont3(Counter cont3) {
		this.cont3 = cont3;
	}

	public void start() {
		Thread t1, t2, t3;
		t1 = new Thread(this.cont1);
		t2 = new Thread(this.cont2);
		t3 = new Thread(this.cont3);
		t1.start();
		t2.start();
		t3.start();
		this.cont1.setPaused(false);
		this.cont2.setPaused(false);
		this.cont3.setPaused(false);
	}
	
	public void setOthersIsPaused(int i, boolean flag) {
		if(i == 1) {
			this.cont2.setPaused(flag);
			this.cont3.setPaused(flag);
		}else if(i == 2) {
			this.cont1.setPaused(flag);
			this.cont3.setPaused(flag);
		}else if(i == 3) {
			this.cont1.setPaused(flag);
			this.cont2.setPaused(flag);
		}else {
			this.cont1.setPaused(flag);
			this.cont2.setPaused(flag);
			this.cont3.setPaused(flag);
		}
	}
	
}
