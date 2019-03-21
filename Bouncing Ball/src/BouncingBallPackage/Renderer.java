package BouncingBallPackage;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
abstract class Renderer extends JPanel implements ActionListener {
	private final int WIDTH, HEIGHT;
	protected JFrame frame;
	private Timer t;
	
	public Renderer(String windowTitle, int windowWidth, int windowHeight, int delay) {
		frame = new JFrame(windowTitle);
		t = new Timer(delay, this);
		
		t.start();
		WIDTH = windowWidth;
		HEIGHT = windowHeight;
		this.setBackground(Color.black);
		frame.add(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
