package GraphicsLib;

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-12-16
*
* Defines a polygon that is drawable onto a DrawingPane2D
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
*/
public class EPolygon2D extends Component implements EDrawable2D{
	/**
	*The color of the line
	*/
	private final Color color;
	/**
	*The width of the line
	*/
	private final BasicStroke width;
	/**
	*The polygon to be drawn by the component
	*/
	private final Path2D.Double poly;

	private double[] xpath;
	private double[] ypath;

	public EPolygon2D(double [] xpoints, double [] ypoints, int width, Color color){
		poly = new Path2D.Double();
		if(xpoints.length!= ypoints.length){
			System.err.println("Point lists not the same size in polygon construction.");
		}else{
			if(xpoints.length == 0){
				System.err.println("No Points given to construct polygon");
			}else{
				//We have validity!
				poly.moveTo(xpoints[0],ypoints[0]);
				for (int i = 1; i < xpoints.length ; i++ ) {
					poly.lineTo(xpoints[i],ypoints[i]);
				}
				//Close that path!
				poly.closePath();
			}
		}
		this.color = color;
		this.width = new BasicStroke(width);
		this.xpath = xpoints;
		this.ypath = ypoints;
		setVisible(true);
	}

	public EPolygon2D(Point2D.Double [] points, int width, Color color){
		this(getXList(points),getYList(points),width,color);
	}

	public static double [] getYList(Point2D.Double []points){
		double [] yps = new double[points.length];
		for(int i=0; i < points.length; i++){
			yps[i] = points[i].getY();
		}
		return yps;
	}

	public static double [] getXList(Point2D.Double []points){
		double [] xps = new double[points.length];
		for(int i=0; i < points.length; i++){
			xps[i] = points[i].getX();
		}
		return xps;
	}

	public double[] getXPath(){
		return xpath;
	}

	public double[] getYPath(){
		return ypath;
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
		g2.draw(poly);
		repaint();
	}

	
	public Color getColor(){
		return color;
	}
	/**
	*Gets the width of this line
	*@return The width of the line
	*/
	public int getWidth(){
		return (int)width.getLineWidth();
	}


}