package org.onebox.trains.path;

import static org.onebox.trains.util.Constants.INF;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class ShortestPathImpl implements ShortestPath {
	
	public ShortestPathImpl() {
		super();
	}
	
	@Override
	public int getShortestPath(final int start, final int end, final int[][] graph) {
		Validator.validateInput(start, end, graph);
		int[][] array = this.floydWarshallAlgorithm(graph);
		return array[start][end];
	}
	
	private int[][] floydWarshallAlgorithm(final int[][] graph) {
		int[][] array = Arrays.copyOf(graph, graph.length);
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (array[i][k] != INF && array[k][j] != INF && array[i][k] + array[k][j] < array[i][j]) {
						array[i][j] = array[i][k] + array[k][j];
					}
				}
			}
		}
		return array;
	}
}