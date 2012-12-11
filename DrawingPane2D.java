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
* Defines a Cartesion 2D Space to write on
* 
*/
public class DrawingPane2D extends JFrame{
	


	final int defaultWidth = 480;
	final int defaultHeight = 640;

	final int shiftX = defaultWidth/2;
	final int shiftY = defaultHeight/2;

	double [][] transformationMatrix = {
								{1,0,shiftX},
								{0,1,shiftY},
								{0,0,1}
								};

	double [] tempVector = {0,0,1};
	double [] resultVector = {0,0,1};

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
		initialize();
	}

	/**
	*Creates the initial components for the DrawingPane
	*/
	protected void initialize(){
		
		setPreferredSize(new Dimension(defaultHeight,defaultHeight));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(defaultHeight,defaultWidth));
	
		final Graph graph = new Graph();
		
		add(graph,BorderLayout.CENTER);
		pack();
		
		setVisible(true);
	

		final RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,
            											 RenderingHints.VALUE_ANTIALIAS_ON);

		final BasicStroke stroke = new BasicStroke(6);

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

	public Point2D.Double translatePoint(Point2D.Double p){

		tempVector[0] = p.getX();
		tempVector[1] = p.getY();

		for(int i = 0; i < 3; i++){
			for(int j =0; j < 3; i++){
				resultVector[j] += transformationMatrix[i][j]*tempVector[j];
			}
		}

		return new Point2D.Double(resultVector[0],resultVector[1]);
	}

	public Point2D.Double translatePoint(double x, double y){
		resultVector[0]=0;
		resultVector[1]=0;
		resultVector[2]=0;
	
		tempVector[0] = x;
		tempVector[1] = y;

		for(int i = 0; i < tempVector.length; i++){
			for(int j =0; j < tempVector.length; j++){
				resultVector[i] += transformationMatrix[i][j]*tempVector[j];
				System.out.println("RESULT j" + resultVector[j]);
			}

		}

		return new Point2D.Double(resultVector[0],resultVector[1]);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DrawingPane2D dp = new DrawingPane2D(480,640);
            }
        });
	}

	public class Graph extends JPanel{
		
		private final Color color = new Color(0,0,0,0);
		

		Line2D.Double [] axis = new Line2D.Double[2];

		public Graph(){
			axis[0] = new Line2D.Double(translatePoint(0,-defaultHeight),translatePoint(0,defaultHeight));
			axis[1] = new Line2D.Double(translatePoint(-defaultWidth,0), translatePoint(defaultWidth,0));
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
			
			g2.setColor(new Color(54,24,4,64));
			//Draw the Axis
			for(int i = 0; i < 2; i++){
				g2.draw(axis[i]);
			}
			repaint();
			//g2.dispose();			

		}



	}

}

