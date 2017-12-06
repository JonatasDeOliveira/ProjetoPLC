package ourGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Menu extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(player_images);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private int count;
	private JButton btn_StarGame;
	private static ImageIcon[] player_images = new ImageIcon[3];


	private JLabel lblSelectYourPlayer;
	private JLabel player_4;
	private JLabel player_1;
	private JLabel player_3;
	private JLabel player_2;


	/**
	 * Create the frame.
	 */
	public Menu() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 720);
		getContentPane().setLayout(null);

		btn_StarGame = new JButton("Start ");
		btn_StarGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new GamePlay();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btn_StarGame.setBounds(441, 539, 185, 42);
		getContentPane().add(btn_StarGame);
		btn_StarGame.setVisible(false);
		player_1 = new JLabel("New label");

		player_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				player_1.setVisible(false);
				addImageIcon((ImageIcon)player_1.getIcon());
				verificCount();
				increment();

			}

		});
		player_1.setHorizontalAlignment(SwingConstants.CENTER);
		player_1.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/valdemiro_player.jpg")));
		player_1.setBounds(339, 291, 150, 150);
		getContentPane().add(player_1);

		player_2 = new JLabel("New label");
		player_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				player_2.setVisible(false);
				addImageIcon((ImageIcon)player_2.getIcon());
				verificCount();
				increment();

			}

		});
		player_2.setHorizontalAlignment(SwingConstants.CENTER);
		player_2.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/doug_player.jpg")));
		player_2.setBounds(820, 291, 150, 150);
		getContentPane().add(player_2);

		javax.swing.Timer t;

		lblSelectYourPlayer = new JLabel("SELECT PLAYER 1");
		lblSelectYourPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectYourPlayer.setForeground(new Color(255, 140, 0));
		lblSelectYourPlayer.setFont(new Font("Courier New", Font.PLAIN, 48));
		lblSelectYourPlayer.setBounds(124, 167, 846, 111);
		getContentPane().add(lblSelectYourPlayer);

		t = new javax.swing.Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lblSelectYourPlayer.isVisible() == true) {
					lblSelectYourPlayer.setVisible(false);
				}else{
					lblSelectYourPlayer.setVisible(true);
				}   
			}
		});
		t.start();
		player_3 = new JLabel("New label");
		player_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				player_3.setVisible(false);
				addImageIcon((ImageIcon)player_3.getIcon());
				verificCount();
				increment();

			}

		});
		player_3.setHorizontalAlignment(SwingConstants.CENTER);
		player_3.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/ullayne_player.jpg")));
		player_3.setBounds(579, 291, 150, 150);
		getContentPane().add(player_3);

		player_4 = new JLabel("New label");
		player_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				player_4.setVisible(false);
				addImageIcon((ImageIcon)player_4.getIcon());
				verificCount();
				increment();

			}

		});
		player_4.setHorizontalAlignment(SwingConstants.CENTER);
		player_4.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/lucas_player.jpg")));
		player_4.setBounds(113, 291, 150, 150);
		getContentPane().add(player_4);

		JLabel name_game = new JLabel("THREAD RUN");
		name_game.setHorizontalAlignment(SwingConstants.CENTER);
		name_game.setForeground(new Color(255, 140, 0));
		name_game.setFont(new Font("Courier New", Font.PLAIN, 73));
		name_game.setBounds(277, 60, 540, 111);
		getContentPane().add(name_game);

		JLabel fundo_menu = new JLabel("");
		fundo_menu.setIcon(new ImageIcon(getClass().getResource("/ThreadRunImages/fundo_image.png")));
		fundo_menu.setBounds(0, 0, 1094, 682);
		getContentPane().add(fundo_menu);
	}
	public void increment() {
		count++;
	}
	public void verificCount() {
		if(count == 0) {
			lblSelectYourPlayer.setText("SELECT PLAYER 2");
		}else if(count == 1) {
			lblSelectYourPlayer.setText("SELECT PLAYER 3");
		}else if (count == 2) {
			lblSelectYourPlayer.setText("PRESS START!!!");
			btn_StarGame.setVisible(true);
			player_1.setVisible(false);
			player_2.setVisible(false);
			player_3.setVisible(false);
			player_4.setVisible(false);
			
			
		}
	}
	public void addImageIcon(ImageIcon x) {
		for (int i = 0; i < player_images.length; i++) {
			if(player_images[i] == null){
				player_images[i] = x;
			}

		}
	}
}
