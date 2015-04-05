package org.onebox.trains.util;

import static org.onebox.trains.util.Constants.INF;

import org.onebox.trains.util.Node;

public class GraphInitializer {

	public static int[][] getMockedGraph() {
		int[][] matrix = new int[5][5];
		matrix = new int[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				matrix[i][j] = INF;
			}
		}
		matrix[Node.A.ordinal()][Node.B.ordinal()] = 5;
		matrix[Node.A.ordinal()][Node.D.ordinal()] = 5;
		matrix[Node.A.ordinal()][Node.E.ordinal()] = 7;
		matrix[Node.B.ordinal()][Node.C.ordinal()] = 4;
		matrix[Node.C.ordinal()][Node.D.ordinal()] = 8;
		matrix[Node.C.ordinal()][Node.E.ordinal()] = 2;
		matrix[Node.D.ordinal()][Node.C.ordinal()] = 8;
		matrix[Node.D.ordinal()][Node.E.ordinal()] = 6;
		matrix[Node.E.ordinal()][Node.B.ordinal()] = 3;
		
		return matrix;
	}
}
