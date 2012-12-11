import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class eLine2D{
	private final Color color;
	private final BasicStroke width;
	Line2D.Double line;
	Point p = null;
	Point q = null;

	eLine2D(Point p, Point q){
		this(p,q,6,new Color(255,255,255,255));
	}

	eLine2D(Point p, Point q, int width){
		this(p,q,width,new Color(255,255,255,255));
	}

	eLine2D(Point p, Point q, int width, Color color){
		this.color = color;
		this.width = new BasicStroke(width);
		line = new Line2D.Double(p.getX(),p.getY(),q.getX(),q.getY());
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.setStroke(width);
		g2.draw(line);
	}


}