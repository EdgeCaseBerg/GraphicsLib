package GraphicsLib;

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;


public class ELine2D extends Component implements EDrawable2D{
	private final Color color;
	private final BasicStroke width;
	Line2D.Double line;
	Point2D.Double p = null;
	Point2D.Double q = null;

	ELine2D(int x1, int y1, int x2, int y2){
		this(new Point2D.Double(x1,y1),new Point2D.Double(x2,y2));
	}
	ELine2D(Point2D.Double p, Point2D.Double q){
		this(p,q,6,new Color(0,255,0,65));
	}

	ELine2D(Point2D.Double p, Point2D.Double q, int width){
		this(p,q,width,new Color(0,255,0,54));
	}

	ELine2D(Point2D.Double p, Point2D.Double q, int width, Color color){
		this.color = color;
		this.width = new BasicStroke(width);
		line = new Line2D.Double(p.getX(),p.getY(),q.getX(),q.getY());
		this.p = p;
		this.q = q;
		setVisible(true);
		

	}

	public void paintComponent(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(width);
		g2.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
		g2.draw(line);
		repaint();
	}

	public Point2D.Double getP1(){
		return p;
	}
	public Point2D.Double getP2(){
		return q;
	}
	public double getX1(){
		return p.getX();
	}
	public double getY1(){
		return p.getY();
	}
	public double getX2(){
		return q.getX();
	}
	public double getY2(){
		return q.getY();
	}
	public Color getColor(){
		return color;
	}
	public int getWidth(){
		return (int)width.getLineWidth();
	}


}