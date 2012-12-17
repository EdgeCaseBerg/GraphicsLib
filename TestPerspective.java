package GraphicsLib;

public class TestPerspective{
	
	DrawingPane2D pane = new DrawingPane2D(640,640);

	double[] camera = new double[]{0,0,0};

	double[] toBeProj = new double[]{0,0,0};

	double[] orientation = new double[]{0,0,0};

	public TestPerspective(){

	}

	public static void main(String[] args) {
		TestPerspective tp = new TestPerspective();
	}


}