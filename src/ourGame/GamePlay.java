package ourGame;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;

public class GamePlay implements Runnable{
	
	private String powerUps[] = new String[6];
	private boolean used[];
	private ImageIcon[] player_images;
	private JLabel winner;
	
	public GamePlay(ImageIcon[] player_images) throws IOException{
		this.player_images = player_images;
	}

	@Override
	public void run() {
		try {
			call();
		} catch (IOException | InterruptedException e) {}
	}

	private void call() throws IOException, InterruptedException {
		int numPlayers = 3;
		
		used = new boolean[6];
		for(int i = 0;i<6;i++) used[i] = false;
		int cont = 0;
		while(cont < 6) {
			Random rand = new Random();
			int k = rand.nextInt(6);
			if(!used[k]) {
				powerUps[k] = "pw"+(cont+1)+".png";
				used[k] = true;
				cont++;
			}
		}
		
		
		JFrame frame = new JFrame();
		frame.setSize(1125,720);
		int frameWidth = (int)(frame.getSize().getWidth()/numPlayers);
		frame.setTitle("My game ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		Image i = ImageIO.read(getClass().getResource("/ThreadRunImages/player1.png"));
		Player p1 = new Player(1,0+(frameWidth/2), 0, KeyCode.A, KeyCode.D, i);
		i = ImageIO.read(getClass().getResource("/ThreadRunImages/player2.png"));
		Player p2 = new Player(2,0+(frameWidth/2), 0, KeyCode.B, KeyCode.M, i);
		i = ImageIO.read(getClass().getResource("/ThreadRunImages/player3.png"));
		Player p3 = new Player(3,0+(frameWidth/2), 0, KeyCode.LEFT, KeyCode.RIGHT, i);
		
		GerenciadorCounter gerador = new GerenciadorCounter();
		
		Image memImg = ImageIO.read(getClass().getResource("/ThreadRunImages/mem.png"));
		Memoria mem = new Memoria(0,740,memImg);
		
		JLabel text_winner = new JLabel("CONGRATULATIONS! YOU ARE THE WINNER");
		text_winner.setHorizontalAlignment(SwingConstants.CENTER);
		text_winner.setForeground(new Color(255, 140, 0));
		text_winner.setFont(new Font("Courier New", Font.BOLD, 30));
		text_winner.setBounds(162, 398, 785, 150);
		frame.getContentPane().add(text_winner);
		text_winner.setVisible(false);
		winner = new JLabel("");
		winner.setHorizontalAlignment(SwingConstants.CENTER);
		winner.setBounds(479, 203, 150, 150);
		frame.getContentPane().add(winner);
		winner.setVisible(false);
		
		JLabel panel_winner = new JLabel();
		panel_winner.setBounds(0, 0, 1109, 682);
		frame.getContentPane().add(panel_winner);
		panel_winner.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/fundo_image.png")));
		panel_winner.setVisible(false);

		
		JPanel bg1 = new BackGround(0, 0, "/ThreadRunImages/cplusplus.png" ,frameWidth, 3623, p1, 1, gerador, powerUps, mem, player_images[0]);
		bg1.setBounds(0, 0, 370, 720);
		frame.getContentPane().add(bg1);
		
		JPanel bg2 = new BackGround(0, 0, "/ThreadRunImages/java.png" ,frameWidth, 3623, p2, 2, gerador, powerUps,mem, player_images[1]);
		bg2.setBounds(370, 0, 370, 720);
		frame.getContentPane().add(bg2);
		
		JPanel bg3 = new BackGround(0, 0, "/ThreadRunImages/python.png" ,frameWidth, 3623, p3,  3, gerador, powerUps,mem, player_images[2]);
		bg3.setBounds(740, 0, 370, 720);
		frame.getContentPane().add(bg3);
		
		
		
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				((BackGround)bg1).keyPressed(arg0);
				((BackGround)bg2).keyPressed(arg0);
				((BackGround)bg3).keyPressed(arg0);
			}
			public void keyReleased(KeyEvent e) {
				((BackGround)bg1).keyReleased(e);
				((BackGround)bg2).keyReleased(e);
				((BackGround)bg3).keyReleased(e);
			}
		});
		
		Thread t1 = new Thread((Runnable) bg1);
		Thread t2 = new Thread((Runnable) bg2);
		Thread t3 = new Thread((Runnable) bg3);
		
		t1.start();
		t2.start();
		t3.start();
		
		GerenciadorMemoria gm = new GerenciadorMemoria((BackGround)bg1, (BackGround)bg2, (BackGround)bg3, gerador);
		Thread gmT = new Thread(gm);
		
		gerador.getCont1().isPaused = false;
		gerador.getCont2().isPaused = false;
		gerador.getCont3().isPaused = false;
		gerador.start();
		gmT.start();
		
		while(!gm.isEndOfGame()) {
			Thread.sleep(100);
		}
		
		
		ImageIcon primeiro = null;
		ImageIcon segundo = null;
		ImageIcon terceiro = null;
		if(((BackGround)bg1).getCounterPw().getCounter() >= ((BackGround)bg2).getCounterPw().getCounter() &&
				((BackGround)bg1).getCounterPw().getCounter() >= ((BackGround)bg3).getCounterPw().getCounter()) {
			primeiro = player_images[0];
			if(((BackGround)bg2).getCounterPw().getCounter() >= ((BackGround)bg3).getCounterPw().getCounter())
				segundo = player_images[1];
			else terceiro = player_images[2];
		}else if(((BackGround)bg2).getCounterPw().getCounter() > ((BackGround)bg1).getCounterPw().getCounter() &&
				((BackGround)bg2).getCounterPw().getCounter() > ((BackGround)bg3).getCounterPw().getCounter()) {
			primeiro = player_images[1];
			if(((BackGround)bg1).getCounterPw().getCounter() >= ((BackGround)bg3).getCounterPw().getCounter())
				segundo = player_images[0];
			else terceiro = player_images[2];
		}else {
			primeiro = player_images[2];
			if(((BackGround)bg1).getCounterPw().getCounter() >= ((BackGround)bg2).getCounterPw().getCounter())
				segundo = player_images[0];
			else terceiro = player_images[1];
		}
		
		Thread.sleep(2000);
		
		// TODO "Fechar" tela do jogo e imprimir tela de vencedores
		panel_winner.setVisible(true);
		winner.setIcon((primeiro));
		winner.setVisible(true);
		Timer t = new javax.swing.Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (text_winner.isVisible() == true) {
					text_winner.setVisible(false);
				}else{
					text_winner.setVisible(true);
				}   
			}
		});
		t.start();
	}
	
}
