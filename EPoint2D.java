package GraphicsLib;

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.1
*@since 2012-12-12
*
* Defines simple 2D point that is drawable onto a DrawingPane2D
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
*/
public class EPoint2D extends Component implements EDrawable2D{

	private final Color color;

	private final BasicStroke width;

	private final Line2D.Double point;

	EPoint2D(Double x,Double y,int width, Color color){
		this.color = color;
		this.width = new BasicStroke(width);
		point = new Line2D.Double(x,y,x,y);
		setVisible(true);
	}

	EPoint2D(double x, double y){
		this(x,y,1,new Color(0,0,0,0));
	}

	EPoint2D(double x, double y, int width){
		this(x,y,width, new Color(0,0,0,0));
	}

	EPoint2D(Point2D.Double p){
		this(p.getX(),p.getY());
	}

	EPoint2D(Point2D.Double p, int width){
		this(p.getX(),p.getY(),width);
	}

	EPoint2D(Point2D.Double p, int width, Color color){
		this(p.getX(),p.getY(),width,color);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(width);
		g2.setColor(color);
		g2.fill(point);
		g2.draw(point);
		repaint();
	}

	public double getPointX(){
		return point.getX1();
	}

	public double getPointY(){
		return point.getY1();
	} 

	public int getWidth(){
		return (int)width.getLineWidth();
	}

	public Color getColor(){
		return color;
	}
 
}