package GraphicsLib;


import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-12-16
*
* Defines a Cartesion 3D Space to draw on
* Theme music for this project: http://endlessvideo.com/watch?v=t2ZRy71vivk (Listened on first night for 5+ hours)
* http://en.wikipedia.org/wiki/3D_projection for help with matrices and such
*/
public class DrawingPane3D extends JFrame{

	/**
	*The line where perspective should taper off to. Variable for different views
	*/
	protected double horizonline = width/3.0;
	
	/**
	*The actual width of the drawing panel
	*/
	protected final int defaultWidth = 640;
	/**
	*The actual height of the drawing panel
	*/
	protected final int defaultHeight = 640;


}