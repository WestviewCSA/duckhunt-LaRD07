import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Font newFont = new Font("Serif", Font.BOLD, 50);
	Fairy fairy1 = new Fairy();
	Fairy fairy2 = new Fairy();
	Fairy fairy3 = new Fairy();
	Bunny bunny1 = new Bunny();
	
	GameBackground ground = new GameBackground("ground.png");
	
	//score related vars and timer
	int    roundTimer;
	int    score;
	long   time;
	int    currRound = 1;
	
	//init any variables objects etc for the start of the game
	
	public void init()
	{
		//init the roundTimer and score
		
		roundTimer = 30;
		score      = 0;
		time       = 0;
		
		bunny1.setWidthHeight(200,200);
		bunny1.setScale(2, 2);
		bunny1.setVx(0);
		bunny1.setXY(0, 400);
		
		fairy1.setWidthHeight(200,200);
		fairy1.setScale(3, 3);
		fairy1.setVx(1);
		
		fairy2.setWidthHeight(200,200);
		fairy2.setScale(3, 3);
		fairy2.setVx(1);
		
		fairy3.setWidthHeight(200,200);
		fairy3.setScale(3, 3);
		fairy3.setVx(1);
		
		ground.setScale(1.1, 1.0);
		ground.setXY(0, 0);
		
		fairy1.setClicked(false);
		fairy2.setClicked(false);
		fairy3.setClicked(false);
		
		fairy1.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx = (int)(Math.random()*(4))-1;
		fairy1.setVx(randVx + currRound);
		
		int randVy = (int)(Math.random()*(4))-1;
		fairy1.setVy(randVy + currRound);
		
		fairy2.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx2 = (int)(Math.random()*(4))-1;
		fairy2.setVx(randVx2 + currRound);
		
		int randVy2 = (int)(Math.random()*(4))-1;
		fairy1.setVy(randVy2 + currRound);

		fairy3.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx3 = (int)(Math.random()*(4))-1;
		fairy3.setVx(randVx3 + currRound);
		
		int randVy3 = (int)(Math.random()*(4))-1;
		fairy1.setVy(randVy3 + currRound);
	}
	
	
	public void reset() 
	{
		
	}
	
	public void nextRound()
	{
		roundTimer = 30;
		currRound++;
		fairy1.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx = (int)(Math.random()*(4))-1;
		fairy1.setVx(randVx + currRound);
		
		fairy2.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx2 = (int)(Math.random()*(4))-1;
		fairy2.setVx(randVx2 + currRound);

		fairy3.setXY((int)(Math.random()*(250))+10, (int)(Math.random()*(400))+10);
		int randVx3 = (int)(Math.random()*(4))-1;
		fairy3.setVx(randVx3 + currRound);
	}
	
	
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//add 20 to time since paint is called every 20ms
		time += 20;
		
		if(time % 1000 == 0)//has it been one second?
		{
			roundTimer -= 1;
			if(roundTimer == 0)
			{
				nextRound();
				t.stop();
				//what do you do after one complete round?
			}
		}
		
		if(roundTimer == 30)
		{
			Font messageFont = new Font("Serif", Font.BOLD, 30);
			g.setFont(messageFont);
			g.drawString("Press the space bar for the next round", 250, 250);
		}
		
		g.setFont(newFont);
		
	
		ground.paint(g);
		
		bunny1.paint(g);
		
		if(!(fairy1.getClicked() && fairy1.getY() > 400))
		{
			fairy1.paint(g);
		}
		
		if(!(fairy2.getClicked() && fairy2.getY() > 400))
		{
			fairy2.paint(g);
		}
		
		if(!(fairy3.getClicked() && fairy3.getY() > 400))
		{
			fairy3.paint(g);
		}
		
		if(fairy1.getClicked() && fairy2.getClicked() && fairy3.getClicked())
		{
			nextRound();
		}
		
		//logic for resetting dog or making it bounce around
		if(fairy1.getY() > 400)
		{
			fairy1.setVy(fairy1.getVy()*-1);
			
		}
		
		if(fairy1.getX() > 700)
		{
			fairy1.setVx((fairy1.getVx())*(-1));
		}
		
		if(fairy2.getY() > 400)
		{
			fairy2.setVy(fairy2.getVy()*-1);
			
		}
		
		if(fairy2.getX() > 700)
		{
			fairy2.setVx((fairy2.getVx())*(-1));
		}
		
		if(fairy3.getY() > 400)
		{
			fairy3.setVy(fairy3.getVy()*-1);
			
		}
		
		if(fairy3.getX() > 700)
		{
			fairy3.setVx((fairy3.getVx())*(-1));
		}
		
		
		
		g.drawString("" + this.roundTimer, 525, 50);
		g.drawString("Round "+ this.currRound, 325, 50);
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(900, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		init();
		
		
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	Timer t = new Timer(16, this);
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
		Rectangle rMouse = new Rectangle(mouse.getX(), mouse.getY(), 25, 25); //guess and check size for now
		
		//second rectangle will be for your object
		Rectangle rFairy1 = new Rectangle(
				fairy1.getX(), fairy1.getY(),
				fairy1.getWidth(), fairy1.getHeight()
				
				);
		if(rMouse.intersects(rFairy1))
		{
			fairy1.setPicture("imgs/fairyGooned.gif");
			fairy1.setVy(3);
			fairy1.setClicked(true);
			
		}
		
		Rectangle rFairy2 = new Rectangle(
				fairy2.getX(), fairy2.getY(),
				fairy2.getWidth(), fairy2.getHeight()
				
				);
		if(rMouse.intersects(rFairy2))
		{
			fairy2.setPicture("imgs/fairyGooned.gif");
			fairy2.setVy(3);
			fairy2.setClicked(true);
		}
		
		Rectangle rFairy3 = new Rectangle(
				fairy3.getX(), fairy3.getY(),
				fairy3.getWidth(), fairy3.getHeight()
				
				);
		if(rMouse.intersects(rFairy3))
		{
			fairy3.setPicture("imgs/fairyGooned.gif");
			fairy3.setVy(3);
			fairy3.setClicked(true);
		}
		
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		//space bar continues the round
		if(arg0.getKeyCode()==32)
		{
			if(!t.isRunning())
			{
				t.start();
			}
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
