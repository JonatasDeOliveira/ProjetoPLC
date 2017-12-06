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
					Menu frame = new Menu();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private int count;
	private JButton btn_StarGame;
	private JComponent btn_StartGame;
	private JLabel lblSelectYourPlayer;
	

	/**
	 * Create the frame.
	 */
	public Menu() {
		int count = 0;
		
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
		
		JLabel label_2 = new JLabel("New label");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label_2.setVisible(false);
				verificCount();
				increment();
				
			}
			
		});
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(Menu.class.getResource("/ourGame/doug_player.jpg")));
		label_2.setBounds(820, 291, 150, 150);
		getContentPane().add(label_2);
		
		JLabel label = new JLabel("New label");
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label.setVisible(false);
				verificCount();
				increment();
				
			}
			
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Menu.class.getResource("/ourGame/valdemiro_player.jpg")));
		label.setBounds(339, 291, 150, 150);
		getContentPane().add(label);
		javax.swing.Timer t;
		
		lblSelectYourPlayer = new JLabel("SELECT PLAYER 1");
		lblSelectYourPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectYourPlayer.setForeground(new Color(255, 140, 0));
		lblSelectYourPlayer.setFont(new Font("Courier New", Font.PLAIN, 48));
		lblSelectYourPlayer.setBounds(109, 167, 846, 111);
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
		JLabel label_1 = new JLabel("New label");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label_1.setVisible(false);
				verificCount();
				increment();
				
			}
			
		});
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(Menu.class.getResource("/ourGame/ullayne_player.jpg")));
		label_1.setBounds(579, 291, 150, 150);
		getContentPane().add(label_1);
		
		JLabel lbl_player = new JLabel("New label");
		lbl_player.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lbl_player.setVisible(false);
				verificCount();
				increment();
				
			}
			
		});
		lbl_player.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_player.setIcon(new ImageIcon(Menu.class.getResource("/ourGame/lucas_player.jpg")));
		lbl_player.setBounds(113, 291, 150, 150);
		getContentPane().add(lbl_player);
		
		JLabel lblNewLabel_1 = new JLabel("CODE RUN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 73));
		lblNewLabel_1.setBounds(315, 57, 435, 111);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/ourGame/fundo_image.png")));
		lblNewLabel.setBounds(0, 0, 1094, 682);
		getContentPane().add(lblNewLabel);
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
		}
	}
}
