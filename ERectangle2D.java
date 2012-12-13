package GraphicsLib;

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.1
*@since 2012-12-11
*
* Defines simple 2D rectangle that is drawable onto a DrawingPane2D
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
*/
public class ERectangle2D extends Component implements EDrawable2D{
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
	private final Rectangle2D.Double rect;

	public ERectangle2D(double topLeftx, double topLefty, double width, double height,int brushWidth, Color color){
		rect = new Rectangle2D.Double(topLeftx,topLefty,width,height);
		this.color = color;
		setVisible(true);
		this.width = new BasicStroke(brushWidth);
	}

	public ERectangle2D(double topLeftx, double topLefty, double width, double height, int brushWidth){
		this(topLeftx,topLefty,width,height,brushWidth,Color.BLACK);
	}

	public ERectangle2D(double topLeftx,double topLefty, double width, double height){
		this(topLeftx,topLefty,width,height,4);
	}

	public ERectangle2D(Point2D.Double p, double width, double height){
		this(p.getX(),p.getY(),width,height);
	}

	public ERectangle2D(Point2D.Double p, double width, double height, int brushWidth, Color color){
		this(p.getX(),p.getY(),width,height,brushWidth,color);
	}

	/**
	*Paints this ERectangle2D to the graphics g
	*@param g The graphics to paint on
	*/
	@Override
	public void paintComponent(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(width);
		g2.setColor(color);
		g2.fill(rect);
		g2.draw(rect);
		repaint();
	}

	public double getRectHeight(){
		return rect.getHeight();
	}

	public double getRectWidth(){
		return rect.getWidth();
	}

	public Point2D.Double getTopLeft(){
		return new Point2D.Double(rect.getX(),rect.getY());
	}

	public int getBrushWidth(){
		return (int)width.getLineWidth();
	}

	public Color getColor(){
		return color;
	}


}