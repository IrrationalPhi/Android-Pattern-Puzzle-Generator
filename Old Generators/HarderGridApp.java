import java.awt.*;
import java.util.*;
import java.io.*;

public class HarderGridApp {
	public static int getInt(Scanner console, String prompt) {
		System.out.printf("%s", prompt);
		while (!console.hasNextInt()) {
			System.out.println("error! please input a valid entry.");
			console.next();
			System.out.printf("%s", prompt);
		}
		return console.nextInt();
	}

	public static void main(String[] args) {
		//setup; prompt for user input
		Scanner console = new Scanner(System.in);
		int numSteps = getInt(console, "enter desired length (3-8): ");
		if (numSteps < 3 || numSteps > 8) {
			throw new IllegalArgumentException();
		}

		//creates the grid
		HarderGrid grid = new HarderGrid();
		while (grid.getNElems() <= numSteps) {
			int next = grid.generateNext();
			grid.connect(next);
		}

		//display grid
		DrawingPanel gridFigure = new DrawingPanel(500, 500);
		Graphics g = gridFigure.getGraphics();

		//top and left margin
		int margin = 100;
		int skipSize = 100;
		int diameter = 6;

		//fill in the nodes
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.fillOval(margin + i*skipSize, margin + j*skipSize, diameter, diameter);
			}
		}

		//answer should have nElems elements
		g.setColor(Color.BLUE);
		for (int i = 0; i < grid.getNElems()-1; i++) {
			int start = grid.getNumVO(i);
			int end = grid.getNumVO(i+1);

			int startRow = start/3;
			int startCol = start%3;
			int endRow = end/3;
			int endCol = end%3;

			g.drawLine(diameter/2 + margin+startCol*skipSize, diameter/2 + margin+startRow*skipSize, 
				diameter/2 + margin+endCol*skipSize, diameter/2 + margin+endRow*skipSize);
		}

		/*

		now we want to prompt a response from them


		*/

		//displays answer
		grid.display();
	}
}