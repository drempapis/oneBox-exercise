package org.onebox.trains.route;

import static org.onebox.trains.util.Constants.INF;

import java.util.List;

import org.onebox.trains.exception.NoSuchRouteException;
import org.onebox.trains.exception.ValidationInputException;
import org.springframework.stereotype.Component;

@Component
public class RouteImpl implements Route {
	
	public RouteImpl() {}

	@Override
	public int calculateRouteDistance(final List<Integer> nodes, final int[][] graph) {
		validateInput(nodes);

		int distance = 0;
		for (int i = 0; i < nodes.size() - 1; i++) {
			int origin = nodes.get(i);
			int destination = nodes.get(i + 1);
			int weight = graph[origin][destination];
			if (weight == INF) {
				throw new NoSuchRouteException();
			}
			distance += weight;
		}
		return distance;
	}

	private void validateInput(List<Integer> nodes) {
		if (nodes.size() < 2) {
			throw new ValidationInputException();
		}
	}
}
