package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChessTimer extends JFrame implements Runnable {
	private JLabel labelPlayer1, labelPlayer2;
	private JButton buttonStart, buttonReset, buttonPass;
	private JTextField textFieldTime;
	private Thread threadPlayer1, threadPlayer2;
	private long timePlayer1, timePlayer2;
	private int currentPlayer;
	private boolean isRunning;

	public ChessTimer() {
		super("Timer de Xadrez");
		
		threadPlayer1 = new Thread(ChessTimer.this);
		threadPlayer2 = new Thread(ChessTimer.this);
		
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2, 10, 10));

		labelPlayer1 = new JLabel("10:00");
		labelPlayer2 = new JLabel("10:00");
		buttonStart = new JButton("Começar");
		buttonReset = new JButton("Resetar");
		buttonPass = new JButton("Passar vez");
		textFieldTime = new JTextField("10");

		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRunning) {
					timePlayer1 = Long.parseLong(textFieldTime.getText()) * 60 * 1000;
					timePlayer2 = timePlayer1;
					currentPlayer = 1;
					isRunning = true;
					threadPlayer1.start();
					buttonStart.setEnabled(false);
					buttonReset.setEnabled(true);
					buttonPass.setEnabled(true);
				}
			}
		});

		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isRunning = false;
				buttonStart.setEnabled(true);
				buttonReset.setEnabled(false);
				buttonPass.setEnabled(false);
				labelPlayer1.setText("10:00");
				labelPlayer2.setText("10:00");
				textFieldTime.setText("10");
			}
		});

		buttonPass.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(isRunning) {
					currentPlayer = currentPlayer == 1 ? 2 : 1;
					if(currentPlayer == 1) {
		            	threadPlayer1.wait();
						
					}
					else {
						threadPlayer2.stop();
					}

				}
			}
		});

		add(new JLabel("Jogador 1:"));
		add(labelPlayer1);
		add(new JLabel("Jogador 2:"));
		add(labelPlayer2);
		add(buttonStart);
		add(buttonReset);
		add(new JPanel());
		add(buttonPass);
	}

	void playerLostByTime(int player) {
		JOptionPane.showMessageDialog(this, "Jogador " + player + " perdeu por tempo!");
		isRunning = false;
		buttonStart.setEnabled(true);
		buttonReset.setEnabled(false);
		buttonPass.setEnabled(false);
		threadPlayer1.interrupt();
		threadPlayer2.interrupt();
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		long timeLimit = currentPlayer == 1 ? timePlayer1 : timePlayer2;
		JLabel label = currentPlayer == 1 ? labelPlayer1 : labelPlayer2;
		while (!Thread.interrupted() && isRunning) {
			long elapsedTime = System.currentTimeMillis() - startTime;
			long remainingTime = timeLimit - elapsedTime;
			if (remainingTime <= 0) {
				playerLostByTime(currentPlayer);
				return;
			}
			int minutes = (int) (remainingTime / 60000);
			int seconds = (int) ((remainingTime % 60000) / 1000);
			String text = String.format("%02d:%02d", minutes, seconds);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					label.setText(text);
				}
			});
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		ChessTimer timer = new ChessTimer();
		timer.setVisible(true);
	}
}
