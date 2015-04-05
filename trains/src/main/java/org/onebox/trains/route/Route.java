package org.onebox.trains.route;

import java.util.List;

public interface Route {
	int calculateRouteDistance(final List<Integer> nodes, final int[][] graph);
}
