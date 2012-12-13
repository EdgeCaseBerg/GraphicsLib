package GraphicsLib;

import java.io.*;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.Timer;

import java.util.ArrayList;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.1
*@since 2012-12-11
*
* Defines a Cartesion 2D Space to write on.
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
*/
public class DrawingPane2D extends JFrame{

	/**
	*Constant for refresh rate of the drawing pane.
	*/
	final static int REFRESH_RATE = 25;

	/**
	*A list of things to be drawn. Until I can think of something speedier, we'll use an arraylist
	*/
	ArrayList<EDrawable2D> thingsToDraw = new ArrayList<EDrawable2D>();

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DrawingPane2D dp = new DrawingPane2D(10,10);
            	dp.createLine(new ELine2D(1,1,24,24));
            	dp.createPoint(new EPoint2D(-1,2,3));
            	dp.createRectangle(new ERectangle2D(  new Point2D.Double(-1,-2),20,20));
            }
        });
	}

	/**
	*The actual width of the drawing panel
	*/
	final int defaultWidth = 640;
	/**
	*The actual height of the drawing panel
	*/
	final int defaultHeight = 640;

	/**
	*The transformation matrix to change user defined coordinate system to the screen coordinates
	*/
	double [][] transformationMatrix = {
								{1,0,0,defaultWidth/2},
								{0,-1,0,defaultHeight/2},
								{0,0,1,0},
								{0,0,0,1}
								};
	/**
	*Pre-Allocating storage for a vector to store points to be translated
	*/
	double [] tempVector = {0,0,1,1};

	/**
	*Pre-Allocated storage for a vector to store resultant vectors of translated points
	*/
	double [] resultVector = {0,0,1,1};

	/**
	*The image to draw on that contains the graph.
	*/
	final BufferedImage graphImage =  new BufferedImage(defaultWidth,defaultHeight,BufferedImage.TYPE_INT_ARGB);

	/**
	*Creates and adds a line to the drawing pane a line from point p1 to p2
	*@param p1 The first endpoint of the line.
	*@param p2 The last endpoint of the line.
	*@return The integer id in the drawing pane of the line
	*/
	public int createLine(Point2D.Double p1, Point2D.Double p2){
		return createLine(new ELine2D( translatePoint(p1), translatePoint(p2)));
	}

	/**
	*Adds a line to the drawing pane
	*@param line The line to be added to the drawing pane
	*@return The integer id in the drawing pane of the line
	*/
	public int createLine(ELine2D line){
		//Convert this line over
		ELine2D newline = new ELine2D(translatePoint(line.getP1()),translatePoint(line.getP2()),(int)line.getWidth(),line.getColor());
		thingsToDraw.add(newline);
		return thingsToDraw.indexOf(newline);
	}

	/**
	*Creates a point p on this drawing pane
	*@param p The point to draw on this pane.
	*@return The integer id in the drawing pane of this point
	*/
	public int createPoint(EPoint2D p){
		EPoint2D newPoint = new EPoint2D(translatePoint(p.getPointX(),p.getPointY()),p.getWidth(),p.getColor());
		thingsToDraw.add(newPoint);
		return thingsToDraw.indexOf(newPoint);
	}

	/**
	*Creates a rectangle on this drawing pane
	*@param r The rectangle to draw on this pane
	*@return The integer id in the drawing pane of this rectangle
	*/
	public int createRectangle(ERectangle2D r){
		ERectangle2D translatedRect = new ERectangle2D(translatePoint(r.getTopLeft()),r.getRectWidth(),r.getRectHeight(),r.getBrushWidth(),r.getColor());
		thingsToDraw.add(translatedRect);
		return thingsToDraw.indexOf(translatedRect);
	}


	/**
	*Creates a Drawing Pane with the plane scaled to the desired width and height
	*@param width The desire width of the pane
	*@param height The desired width of the height
	*/
	public DrawingPane2D(int width, int height){
		super("Drawing Pane");
		//We want a graph with -width to +width and -height to +height so:
		width *= 2.0;
		height *= 2.0;

		//Scale everything to stay within the size of the pane.
		if(width < defaultWidth){
			transformationMatrix[0][0] = defaultWidth/width;
		}else if(width > defaultWidth){
			transformationMatrix[0][0] = width/defaultWidth;
		}
		if(height < defaultHeight){
			transformationMatrix[1][1] = -defaultHeight/height;
		}else if(height > defaultHeight){
			transformationMatrix[1][1] = -height/defaultHeight;
		}
		transformationMatrix[0][2] = transformationMatrix[0][0];
		transformationMatrix[1][2] = transformationMatrix[1][1];
		initialize();
	}

	/**
	*Creates the initial components for the DrawingPane
	*/
	protected void initialize(){
		
		setPreferredSize(new Dimension(defaultWidth,defaultHeight));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(defaultWidth,defaultHeight));
		

		final Graph graph = new Graph();
		
		add(graph,BorderLayout.CENTER);
		pack();
		
		setVisible(true);
	

		final RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,
            											 RenderingHints.VALUE_ANTIALIAS_ON);

		final BasicStroke stroke = new BasicStroke(12);

		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent ae){

				//Draw on the image we're going to show
				Graphics2D graph2D = graphImage.createGraphics();
				graph2D.setRenderingHints(hints);
				graph2D.setColor(new Color(255,0,255));
				graph2D.fillRect(0,0,defaultWidth,defaultHeight);
				graph2D.setStroke(stroke);

				graph.paint(graph2D);

				graph.repaint();

				graph2D.dispose();
				repaint();
			}
		};
		setLayout(null);

		Timer timer = new Timer(REFRESH_RATE,al);
		timer.start();
	}

	/**
	*Clears the resultant vector and readys the point vector for the coordinate x,y
	*@param x The x coordinate that will be placed into the temporary vector
	*@param y The y coordinate that will be placed into the temporary vector
	*/
	private void prepareVectors(double x,double y){
		//Clear the resultant so we don't get bad results
		resultVector[0]=0;
		resultVector[1]=0;
		resultVector[2]=0;
		resultVector[3]=0;

		tempVector[0] = x;
		tempVector[1] = y;
		//We do this because the first coordiante we'll check is x and we need
		//It to reflect the wonderful exceptions along the axes
		tempVector[2] = tempVector[0] == 0 ? 0 : x;
		tempVector[3] = 1;
	}

	/**
	*Translates an x,y coordinate in the space of the user's choosing into the pixel system of the form
	*@param p The point to  be translated to the form coordinate system.
	*@return a Point2D.Double type of the resulting point in the pixel system.
	*/
	protected Point2D.Double translatePoint(Point2D.Double p){
		return translatePoint(p.getX(),p.getY());
	}

	/**
	*Translates an x,y coordinate in the space of the user's choosing into the form coordinate system.
	*@param x The x coordinate to be translated
	*@param y the y coordinate to be translated
	*@return a Point2D.Double type of the resulting point in the form coordinate system.
	*/
	protected Point2D.Double translatePoint(double x, double y){
		//Clear old values and set up new vectors
		prepareVectors(x,y);

		for(int i = 0; i < tempVector.length; i++){
			//We need this bit to deal with shifting points on the axes
			tempVector[2] = tempVector[i] == 0 ? 0 : tempVector[i];
			//tempVector[3] = tempVector[i] == 0 ? 0 : 1;
			for(int j =0; j < tempVector.length; j++){
				resultVector[i] += transformationMatrix[i][j]*tempVector[j];
			}

		}

		return new Point2D.Double(resultVector[0],resultVector[1]);
	}


	
	/**
	*Inner class containing the cartesion graph display with axes.
	*/
	public class Graph extends JPanel{
		/**
		*The color of the background of the graph.
		*/
		private final Color color = new Color(0,0,0,0);
		
		/**
		*Storage for the axes
		*/
		Line2D.Double [] axis = new Line2D.Double[2];

		/**
		*Creates an instance of the graph. Using the outer class to determine screen parameters.
		*/
		public Graph(){
			axis[0] = new Line2D.Double(translatePoint(0,-defaultHeight/2),translatePoint(0,defaultHeight/2));
			axis[1] = new Line2D.Double(translatePoint(-defaultWidth/2,0), translatePoint(defaultWidth/2,0));
			setBorder(BorderFactory.createLineBorder(Color.black));
			setOpaque(true);
		}

		/**
		*Retrieves the preffered size of this DrawingPane2D
		*@return A Dimension object with the size of this DrawingPane2D
		*/
		@Override
		public Dimension getPreferredSize() {
        	return new Dimension(defaultWidth,defaultHeight);
    	}

    	/**
    	*paints this component to the graphics g
    	*@param g The graphics to paint on.
    	*/
    	@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;

			g2.setColor(color);
			g2.fillRect(0,0,defaultWidth,defaultHeight);
			g2.setStroke(new BasicStroke(4));
			g2.setColor(new Color(0,0,0,140));
			//Draw the Axis
			for(int i = 0; i < 2; i++){
				g2.draw(axis[i]);
			}
			for(int i = 0; i < thingsToDraw.size(); i++){
				thingsToDraw.get(i).paintComponent(g2);
			}
			repaint();
			//g2.dispose();			

		}



	}

}

