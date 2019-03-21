package BouncingBallPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class BouncingBall extends Renderer implements KeyListener{
	
	private static final int WIDTH = 600, HEIGHT = 600, DELAY = 5, BALL_WIDTH = 20;
	private static final double AIR_RESISTANCE = 0.99, FRICTION = 0.99, GRAVITY = 0.5;
	private double accelerationY, velocityY, accelerationX, velocityX;
	private int x, y;
	
	public BouncingBall() {
		super("Bouncing Ball", WIDTH, HEIGHT, DELAY);
		x = WIDTH / 2;
		frame.addKeyListener(this);
	}

	public static void main(String args[]) {
		new BouncingBall();
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.white);
		g.fillOval(x, y, BALL_WIDTH, BALL_WIDTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//When falling, the ball's acceleration in the y direction will be equal the force of gravity
		accelerationY += GRAVITY;
		
		//add this acceleration to the velocity in the y direction
		velocityY += accelerationY;
		
		//if the ball is falling down (positive velocity), decreasing its descent by adding a air resistance force to the velocity
		if(velocityY > 0) 
			velocityY *= AIR_RESISTANCE;
		
		//In the x direction, add the x acceleration to the x velocity
		velocityX += accelerationX;
		
		//apply a frictional force to the velocity to slow the ball down over time
		velocityX *= FRICTION;
		
		
		x += (int) velocityX;
		y += (int) velocityY;
		
		accelerationY = 0;
		accelerationX = 0;
		
		//when the ball has hit the ground
		if(y + BALL_WIDTH > WIDTH - 100) {
		
			//apply the friction of the ground to its current velocity and add the negative of that value the acceleration in the y direction 
			accelerationY += (-velocityY * FRICTION);
			
			//push the ball back onto the screen so it doesn't get stuck.
			y = WIDTH - 100 - BALL_WIDTH;
			
			//when the ball has hit the ground, its velocity will temporarily be 0.
			velocityY = 0;
		}
		
		//wrap the ball to the other side of the screen
		if(x + BALL_WIDTH >= WIDTH + BALL_WIDTH) 
			x = -BALL_WIDTH;
		
		//System.out.println("accelX: " + accelerationX + " y: " + y + " velocityX: " + velocityX + " velocityY: " + velocityY);
		repaint();
	}

	public void applyWindForce(double force) {
		accelerationX += force;	
	}
	
	public void applyUpwardsForce(double force) {
		accelerationY += force;
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_W) {
			//System.out.println("w is pressed!");
			applyWindForce(20);
		}
			
		else if(key.getKeyCode() == KeyEvent.VK_U) {
			//System.out.println("u is pressed!");
			applyUpwardsForce(-20);
		}
			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
