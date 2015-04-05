package org.onebox.trains.path;

import static org.onebox.trains.util.Constants.INF;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class PathCounterImpl implements PathCounter {
	
	public PathCounterImpl() {
		super();
	}
	
	@Override
	public int getNumberOfPaths(final int start, final int end, final int[][] graph) {
		Validator.validateInput(start, end, graph);
		int[][] array = Arrays.copyOf(graph, graph.length);
		return numberOfPathsRecursive(start, end, start, array);
	}
	
	private int numberOfPathsRecursive(final int start, final int end, int previous, final int[][] array) {
		int numRoutes = 0;
		for(int index = 0; index < array[start].length; index++) {
			if(array[start][index] == INF  || index == previous) {
				continue;
			}
			if(index == end) {
				numRoutes++;
			} else {
				numRoutes += numberOfPathsRecursive(index, end, start, array);
			}
		}
		return numRoutes;
	}
}
