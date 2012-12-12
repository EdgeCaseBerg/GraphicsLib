package GraphicsLib;

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-12-11
*
* Defines simple 2D line that is drawable onto a DrawingPane2D
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
*/
public class ELine2D extends Component implements EDrawable2D{
	/**
	*The color of the line
	*/
	private final Color color;
	/**
	*The width of the line
	*/
	private final BasicStroke width;
	/**
	*The line to be drawn by the component
	*/
	private final Line2D.Double line;

	/**
	*Defines a line with endpoints (x1,y1) and (x2,y2)
	*@param x1 The x coordinate of the first endpoint
	*@param y1 The y coordinate of the first endpoint
	*@param x2 The x coordinate of the second endpoint
	*@param y2 The y coordiante of the second endpoint
	*/
	ELine2D(int x1, int y1, int x2, int y2){
		this(new Point2D.Double(x1,y1),new Point2D.Double(x2,y2));
	}
	/**
	*Defines a line with endpoints p and q.
	*@param p The first endpoint
	*@param q The second endpoint
	*/
	ELine2D(Point2D.Double p, Point2D.Double q){
		this(p,q,6,new Color(0,255,0,65));
	}
	/**
	*Defines a line with endpoints p and q, and width.
	*@param p The first endpoint
	*@param q The second endpoint
	*@param width The width of the line, in pixels(?)
	*/
	ELine2D(Point2D.Double p, Point2D.Double q, int width){
		this(p,q,width,new Color(0,255,0,54));
	}

	/**
	*Defines a line with endpoints p and q, width of width and color of color.
	*@param p The first endpoint
	*@param q The second endpoint
	*@param width The width of the line, in pixels(?)
	*@param color The color of the line
	*/
	ELine2D(Point2D.Double p, Point2D.Double q, int width, Color color){
		this.color = color;
		this.width = new BasicStroke(width);
		line = new Line2D.Double(p.getX(),p.getY(),q.getX(),q.getY());
		setVisible(true);
		

	}

	/**
	*Paints this ELine2D to the graphics g
	*@param g The graphics to paint on
	*/
	@Override
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
		return (Point2D.Double)line.getP1();
	}
	public Point2D.Double getP2(){
		return (Point2D.Double)line.getP2();
	}
	public double getX1(){
		return line.getX1();
	}
	public double getY1(){
		return line.getY1();
	}
	public double getX2(){
		return line.getX2();
	}
	public double getY2(){
		return line.getY2();
	}
	public Color getColor(){
		return color;
	}
	public int getWidth(){
		return (int)width.getLineWidth();
	}


}