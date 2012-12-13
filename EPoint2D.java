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

	/**
	*The color of the line
	*/
	private final Color color;
	/**
	*The width of the line
	*/
	private final BasicStroke width;
	/**
	*The point to be drawn by the component
	*/
	private final Ellipse2D.Double point;

	/**
	*Constructs a new EPoint2D instance with center point (x,y), a brush width of width and a Color of color
	*@param x The x coordinate to center this point at
	*@param y The y coordinate to center this point at
	*@param width The width of the brush stroke applied when painting this point
	*@param color The color of this point when drawn
	*/
	EPoint2D(Double x,Double y,int width, Color color){
		this.color = color;
		this.width = new BasicStroke(width);
		point = new Ellipse2D.Double(x-width/2.0,y-width/2.0,width,width);
		setVisible(true);
	}

	/**
	*Constructs a new EPoint2D instance with center point (x,y)
	*@param x The x coordinate to center this point at
	*@param y The y coordinate to center this point at
	*/
	EPoint2D(double x, double y){
		this(x,y,1,new Color(0,0,0,255));
	}

	/**
	*Constructs a new EPoint2D instance with center point (x,y), and a brush width of width 
	*@param x The x coordinate to center this point at
	*@param y The y coordinate to center this point at
	*@param width The width of the brush stroke applied when painting this point
	*/
	EPoint2D(double x, double y, int width){
		this(x,y,width, new Color(0,0,0,255));
	}

	/**
	*Constructs a new EPoint2D instance with center point p
	*@param p The point to center this EPoint2D at.
	*/
	EPoint2D(Point2D.Double p){
		this(p.getX(),p.getY());
	}

	/**
	*Constructs a new EPoint2D instance with center point p and brush width of width
	*@param p The point to center this EPoint2D at
	*@param width The width of the brush stroke applied when painting this point
	*/
	EPoint2D(Point2D.Double p, int width){
		this(p.getX(),p.getY(),width);
	}

	/**
	*Constructs a new EPoint2D instance with center point p and brush width of width
	*@param p The point to center this EPoint2D at
	*@param width The width of the brush stroke applied when painting this point
	*@param color The color of this EPoint2D when drawn
	*/
	EPoint2D(Point2D.Double p, int width, Color color){
		this(p.getX(),p.getY(),width,color);
	}

	/**
	*Paints this point to the graphics g.
	*@param g The graphics to draw this point to.
	*/
	@Override
	public void paintComponent(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(width);
		g2.setColor(color);
		g2.draw(point);
		repaint();
	}

	/**
	*Getter method for the center x coordinate of this EPoint2D
	*@return The center x coordinate of this EPoint2D
	*/
	public double getPointX(){
		return point.getCenterX();
	}

	/**
	*Getter method for the center y coordinate of this EPoint2D
	*@return The center y coordinate of this EPoint2D
	*/
	public double getPointY(){
		return point.getCenterY();
	} 

	/**
	*Getter method for the width of the brush stroke used when painting this component
	*@return The integer width of the brush stroke (may lose precision)
	*/
	public int getWidth(){
		return (int)width.getLineWidth();
	}

	/**
	*Accessor method for the color that this component is drawn with
	*@return The color of the component
	*/
	public Color getColor(){
		return color;
	}
 
}