import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Fairy extends Character{
	private boolean clicked;
	
	public Fairy() {
		super("fairyIdle.gif");
	}
	public void setPicture(String picture) {
        super.changePicture(picture);
        }
	
	public void setClicked(boolean value)
	{
		clicked = value;
	}
	
	public boolean getClicked()
	{
		return clicked;
	}

}
