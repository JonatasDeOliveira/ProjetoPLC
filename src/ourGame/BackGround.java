package ourGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BackGround extends JPanel implements ActionListener, KeyListener, Runnable {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	int vel;
	Image bg1;
	Image bg2;
	int mouseX;
	int mouseY;
	int wid;
	Player player;
	Image playerImage;
	PowerUp pw1;
	int index;
	GerenciadorCounter gerador;
	private int dir;
	Counter counterPw;
	private String powerUps[];
	private boolean printPw;
	private Memoria mem;
	private boolean endOfGame;
	private boolean gameRunning;
	private ImageIcon player_image_icon;
	Image image_icon;

	public BackGround(int initPosX, int initPosY, String imgName, int width, int height, Player player, int index,
			GerenciadorCounter gerador, String powerUps[], Memoria mem, ImageIcon player_image_icon) throws IOException {
		// addMouseListener(this);
		
		this.player_image_icon = player_image_icon;
		this.mem = mem;
		this.printPw = true;
		this.dir = 0;
		addKeyListener(this);
		this.index = index;
		this.wid = width;
		this.gerador = gerador;
		this.player = new Player(player.getNumPlayer(), player.getX(), player.getY(), player.getCharL(),
				player.getCharR(), player.getImage(), this.getX(), this.getX() + width);
		playerImage = this.player.getImage();
		this.pw1 = new PowerUp(0, 0, playerImage, 0);
		this.powerUps = powerUps;

		this.vel = 15;
		if (this.index == 1) {
			this.counterPw = this.gerador.getCont1();
		} else if (this.index == 2) {
			this.counterPw = this.gerador.getCont2();
		} else {
			this.counterPw = this.gerador.getCont3();
		}
		this.gameRunning = true;
		image_icon = this.player_image_icon.getImage().getScaledInstance(50, 50, 100);
		setFocusable(true);
		bg2 = ImageIO.read(getClass().getResource(imgName));
		bg1 = bg2;

		Timer timer = new Timer(40, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!endOfGame) {
					if (!counterPw.isPaused) {
						movePlayer();
						moveBackground(bg1, bg2);
						if (pw1.isEnable() && printPw)
							movePowerUp();
						if (!printPw && gameRunning)
							moveMemoria();
					}
					repaint();
				}
			}
		});
		bg1 = bg1.getScaledInstance(width, height, 1);
		bg2 = bg2.getScaledInstance(width, height, 1);

		this.x1 = initPosX;
		this.y1 = initPosY;
		this.x2 = this.x1;
		this.y2 = this.y1 + bg1.getHeight(null);

		timer.start();

	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.bg1, this.x1, this.y1, null);
		g2d.drawImage(this.bg2, this.x2, this.y2, null);
		g2d.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), null);
		if (this.pw1.isEnable() && this.printPw) {
			g2d.drawImage(this.pw1.getImage(), this.pw1.getX(), this.pw1.getY(), null);
		}
		if (!this.printPw) {
			g2d.drawImage(this.mem.getImg(), this.mem.getX(), this.mem.getY(), null);
		}
		g2d.drawString(this.counterPw.counter + "", 10, 20);
		
		g2d.drawImage(image_icon, 310, 620, null);

	}

	private void moveBackground(Image bg1, Image bg2) {
		if ((this.y1 + bg1.getHeight(null)) < 0) {
			this.y1 = 0 + bg1.getHeight(null);
		} else if ((this.y2 + bg2.getHeight(null)) < 0) {
			this.y2 = 0 + bg2.getHeight(null);
		} else {
			this.y1 -= this.vel;
			this.y2 -= this.vel;
		}
	}

	private void movePowerUp() {
		this.pw1.setY(this.pw1.getY() - this.vel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).equals(this.player.getCharL().getName())) {
			this.setDir(-1);
		} else if (KeyEvent.getKeyText(e.getKeyCode()).equals(this.player.getCharR().getName())) {
			this.setDir(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.getKeyText(e.getKeyCode()).equals(this.player.getCharL().getName())
				|| KeyEvent.getKeyText(e.getKeyCode()).equals(this.player.getCharR().getName())) {
			this.setDir(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void run() {

		Random rand = new Random();

		while (!this.endOfGame) {

			if (!printPw) {
				synchronized (mem) {
					if (collision(mem)) {
						gameRunning = false;
						this.counterPw.setPaused(true);
						gerador.setOthersIsPaused(this.index, true);
						Image i = null;
						try {
							i = ImageIO.read(getClass().getResource("/ThreadRunImages/mem" + this.index + ".png"));
						} catch (IOException e) {
						}

						if (this.mem.getPlace() == 0) {
							this.counterPw.setCounter(this.counterPw.getCounter() + 20);
							this.mem.setPlace(this.mem.getPlace() + 1);
						} else if (this.mem.getPlace() == 1) {
							this.counterPw.setCounter(this.counterPw.getCounter() + 10);
							this.mem.setPlace(this.mem.getPlace() + 1);
						} else {
							this.counterPw.setCounter(this.counterPw.getCounter() + 5);
							this.mem.setPlace(this.mem.getPlace() + 1);
						}

						this.mem = new Memoria(this.mem.getX(), this.mem.getY(), i);

						// resetando tudo de volta ao normal
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						gerador.setOthersIsPaused(this.index, false);
						this.counterPw.setPaused(false);
						this.setPrintPw(true);

					}
				}
			}

			if (gameRunning) {
				if (!this.counterPw.isPaused) {
					

					if (this.pw1.isEnable()) {
						if (collision(this.pw1)) {
							if (this.pw1.getTipo() == 0) {
								gerador.setOthersIsPaused(this.index, true);
							} else if (this.pw1.getTipo() == 1) {
								this.counterPw.setPaused(true);
							}
							this.pw1.disable();
							this.pw1.setVel(0);
							this.pw1.setY(-20);
						}
					}

					if ((this.counterPw.getCounter()) % 7 == 0 && this.counterPw.getCounter() != 0) {
						Image image = null;
						int x = 0;
						int k = rand.nextInt(6);

						try {
							image = ImageIO.read(getClass().getResource("/ThreadRunImages/"+this.powerUps[k]));
						} catch (IOException e1) {
						}

						x = rand.nextInt(370 - image.getWidth(null));

						try {

							this.setPowerUp(new PowerUp(x, 720, image, k % 2));
						} catch (IOException e) {
						}

						this.counterPw.setCounter(this.counterPw.getCounter() + 1);
					}
				} else if (this.printPw) {
					long current = System.currentTimeMillis(), aux = 0;

					while (aux - current < 5000) {
						aux = System.currentTimeMillis();
					}
					this.counterPw.setPaused(false);

				}
			}

		}
	}

	public boolean collision(Sprite s) {
		int x1 = s.getX();
		int y1 = s.getY();
		Image image1 = s.getImg();
		int x2 = this.player.getX();
		int y2 = this.player.getY();
		Image image2 = this.player.getImage();
		if (((x1 + image1.getWidth(null)) >= x2 && x1 <= (x2 + image2.getWidth(null)))
				&& ((y1 + image1.getHeight(null)) >= y2 && y1 <= (y2 + image2.getHeight(null))))
			return true;
		else
			return false;
	}

	public void moveMemoria() {
		this.mem.setY(this.mem.getY() - 3);
	}

	void setPowerUp(PowerUp pw) {
		this.pw1 = pw;
		this.pw1.enable();
	}

	private void movePlayer() {
		this.player.movePlayer(this.dir);
	}

	public Counter getCounterPw() {
		return counterPw;
	}

	void setDir(int dir) {
		this.dir = dir;
	}

	public boolean isPrintPw() {
		return printPw;
	}

	public void setPrintPw(boolean printPw) {
		this.printPw = printPw;
	}

	public void setMem(Memoria m) {
		this.mem = m;
	}

	public void setEndOfGame(boolean b) {
		this.endOfGame = b;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

}
