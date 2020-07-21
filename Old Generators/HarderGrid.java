import java.util.*;

public class HarderGrid {
	public static final int SIZE = 3;

	//if you are at position i, there are "bawal" points
	//those are the points 
	//bawal pairs can be grouped
	//either antipode, or opposite in row/col if corner

	//visit order of the vertices in answer
	private int[] visitOrder;
	
	//number of elements of visitOrder
	private int nElems;

	//list of visited vertices
	private Hashtable<Integer, Integer> visitedSet;

	//constructor
	public HarderGrid() {
		visitOrder = new int[9];
		nElems = 0;
		visitedSet = new Hashtable<Integer, Integer>();
	}

	//generates next element if applicable (randomly)
	public int generateNext() {
		List<Integer> remain = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (!visitedSet.contains(i))
				remain.add(i);
		}
		java.util.Collections.shuffle(remain);
		return remain.get(0);
	}

	//connect two points in grid (i think we can assume valid, then check for validity in another method)
	//connect a to b
	public void connect(int b) {
		if (this.nElems == 0) {
			visitOrder[nElems++] = b;
			visitedSet.put(b, b);
		}

		if (!isReachable(visitOrder[nElems-1], b))
			return;
		//else b is ok, so push it into stack
		visitOrder[nElems++] = b;
		//put into hash table
		visitedSet.put(b, b);
	}

	//here, we are already assuming b isn't in the pattern list yet
	public boolean isReachable(int a, int b) {
		if (a == b)
			return false;

		int aRow = a/3;
		int bRow = b/3;
		int aCol = a % 3;
		int bCol = b % 3;

		int rowDiff = Math.abs(aRow - bRow);
		int colDiff = Math.abs(aCol - bCol);
		if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1))
			return false;
		if (rowDiff == 1 || colDiff == 1) {
			return true;
		}

		//now bad is if same row/col, or both diff are 2. now "midpoint" exists
		if (rowDiff == 0 || colDiff == 0 || a+b == 8) {
			if (!visitedSet.contains((a+b)/2)) {
				return false;
			}
		}
		return true;
	}

	public void display() {
		System.out.printf("[%d", visitOrder[0]);
		for (int i = 1; i < nElems; i++) {
			System.out.printf(", %d", visitOrder[i]);
		}
		System.out.println("]");
	}

	public int getNElems() {
		return nElems;
	}

	//get from visit order array
	public int getNumVO(int index) {
		if (index < 0 || index >= nElems) {
			throw new IllegalArgumentException();
		}
		return visitOrder[index];
	}
}