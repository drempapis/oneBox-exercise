package org.onebox.trains.path;

public interface PathCounter {
	int getNumberOfPaths(final int start, final int end, final int[][] graph);
}
