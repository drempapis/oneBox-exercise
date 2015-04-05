package org.onebox.trains.path;

import static org.onebox.trains.util.Constants.INF;

import org.onebox.trains.exception.ValidationInputException;

public class Validator {
	
	public static void validateInput(final int start, final int end, final int[][] graph) {
		if(start == end) {
			throw new ValidationInputException();
		}
		
		if(start < 0 || start == INF) {
			throw new IllegalArgumentException();
		}
		
		if(end < 0 || end == INF) {
			throw new IllegalArgumentException();
		}
		
		if(graph[start].length < end) {
			throw new ValidationInputException("Graph size not matching");
		}
	}
}
