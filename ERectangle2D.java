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

	/**
	*Constructs a ERectangle2D instance
	*@param topLeftx The top left x coordinate of the rectangle
	*@param topLefty The top left y coordinate of the rectangle
	*@param width The width of the ERectangle2D
	*@param height The height of the ERectangle2D
	*@param brushWidth The brushwidth of the brush used to paint the component
	*@param color The color of the component
	*/
	public ERectangle2D(double topLeftx, double topLefty, double width, double height,int brushWidth, Color color){
		rect = new Rectangle2D.Double(topLeftx,topLefty,width,height);
		this.color = color;
		setVisible(true);
		this.width = new BasicStroke(brushWidth);
	}

	/**
	*Constructs a ERectangle2D instance
	*@param topLeftx The top left x coordinate of the rectangle
	*@param topLefty The top left y coordinate of the rectangle
	*@param width The width of the ERectangle2D
	*@param height The height of the ERectangle2D
	*@param brushWidth The brushwidth of the brush used to paint the component
	*/
	public ERectangle2D(double topLeftx, double topLefty, double width, double height, int brushWidth){
		this(topLeftx,topLefty,width,height,brushWidth,Color.BLACK);
	}

	/**
	*Constructs a ERectangle2D instance
	*@param topLeftx The top left x coordinate of the rectangle
	*@param topLefty The top left y coordinate of the rectangle
	*@param width The width of the ERectangle2D
	*@param height The height of the ERectangle2D
	*/
	public ERectangle2D(double topLeftx,double topLefty, double width, double height){
		this(topLeftx,topLefty,width,height,4);
	}

	/**
	*Constructs a ERectangle2D instance
	*@param p The point becoming the topleft of the rectangle
	*@param width The width of the ERectangle2D
	*@param height The height of the ERectangle2D
	*/
	public ERectangle2D(Point2D.Double p, double width, double height){
		this(p.getX(),p.getY(),width,height);
	}

	/**
	*Constructs a ERectangle2D instance
	*@param p The point becoming the topleft of the rectangle
	*@param width The width of the ERectangle2D
	*@param height The height of the ERectangle2D
	*@param brushWidth The brushwidth of the brush used to paint the component
	*@param color The color of the component
	*/
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

	/**
	*Accessor method returning the height of the ERectangle2D
	*@retun The height of this instance
	*/
	public double getRectHeight(){
		return rect.getHeight();
	}

	/**
	*Accessor method returning the width of the ERectangle2D
	*@retun The width of this instance
	*/
	public double getRectWidth(){
		return rect.getWidth();
	}

	/**
	*Accessor method returning the top left point of this ERectangle2D
	*@retun The top left point of this instance
	*/
	public Point2D.Double getTopLeft(){
		return new Point2D.Double(rect.getX(),rect.getY());
	}

	/**
	*Accessor method returning the brush width used to paint this component
	*@return The integer width of the brush. (may lose precision)
	*/
	public int getBrushWidth(){
		return (int)width.getLineWidth();
	}

	/**
	*Accessor method returning the color used to paint this component
	*@return The color of the component
	*/
	public Color getColor(){
		return color;
	}


}