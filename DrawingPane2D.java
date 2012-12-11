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

import java.awt.event.*;
import javax.swing.Timer;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-12-11
*
* Defines a Cartesion 2D Space to write on.
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk
*/
public class DrawingPane2D extends JFrame{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DrawingPane2D dp = new DrawingPane2D(100,100);
            }
        });
	}

	final int defaultWidth = 640;
	final int defaultHeight = 640;

	final int shiftX = 0;
	final int shiftY = 0;

	double [][] transformationMatrix = {
								{1,0,shiftX,defaultWidth/2},
								{0,1,shiftY,defaultHeight/2},
								{0,0,1,0},
								{0,0,0,1}
								};

	double [] tempVector = {0,0,1,1};
	double [] resultVector = {0,0,1,1};

	final BufferedImage graphImage =  new BufferedImage(defaultWidth,defaultHeight,BufferedImage.TYPE_INT_ARGB);
	/**
	*Creates a Drawing Pane with the plane scaled to the desired width and height
	*@param width The desire width of the pane
	*@param height The desired width of the height
	*/
	public DrawingPane2D(int width, int height){
		super("Drawing Pane");
		//Scale everything to stay within the size of the pane.
		if(width < defaultWidth){
			transformationMatrix[0][0] = defaultWidth/width;
		}else if(width > defaultWidth){
			transformationMatrix[0][0] = width/defaultWidth;
		}
		if(height < defaultHeight){
			transformationMatrix[1][1] = defaultHeight/height;
		}else if(height > defaultHeight){
			transformationMatrix[1][1] = height/defaultHeight;
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

		Timer timer = new Timer(25,al);
		timer.start();
	}

	private void prepareVectors(double x,double y){
		resultVector[0]=0;
		resultVector[1]=0;
		resultVector[2]=0;
		resultVector[3]=0;


		tempVector[0] = x;
		tempVector[1] = y;
		tempVector[2] = tempVector[0] == 0 ? 0 : x;
	}

	public Point2D.Double translatePoint(Point2D.Double p){
		return translatePoint(p.getX(),p.getY());
	}

	public Point2D.Double translatePoint(double x, double y){
		//Clear old values and set up new vectors
		prepareVectors(x,y);

		for(int i = 0; i < tempVector.length; i++){
			//We need this bit to deal with shifting points on the axes
			tempVector[2] = tempVector[i] == 0 ? 0 : tempVector[i];
			for(int j =0; j < tempVector.length; j++){
				resultVector[i] += transformationMatrix[i][j]*tempVector[j];
			}

		}

		return new Point2D.Double(resultVector[0],resultVector[1]);
	}

	

	public class Graph extends JPanel{
		
		private final Color color = new Color(0,0,0,0);
		

		Line2D.Double [] axis = new Line2D.Double[2];

		public Graph(){
			axis[0] = new Line2D.Double(translatePoint(0,-defaultHeight/2),translatePoint(0,defaultHeight/2));
			axis[1] = new Line2D.Double(translatePoint(-defaultWidth/2,0), translatePoint(defaultWidth/2,0));
			System.out.println(axis[0].getX1() + " " + axis[0].getY1() + " " + axis[0].getX2() + " " + axis[0].getY2());
			System.out.println(axis[1].getX1() + " " + axis[1].getY1() + " " + axis[1].getX2() + " " + axis[1].getY2());
			setBorder(BorderFactory.createLineBorder(Color.black));
		}

		public Dimension getPreferredSize() {
        	return new Dimension(defaultWidth,defaultHeight);
    	}

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;

			g2.setColor(color);
			g2.fillRect(0,0,defaultWidth,defaultHeight);
			g2.setStroke(new BasicStroke(4));
			g2.setColor(new Color(0,0,0,255));
			//Draw the Axis
			for(int i = 0; i < 2; i++){
				g2.draw(axis[i]);
			}
			repaint();
			//g2.dispose();			

		}



	}

}

