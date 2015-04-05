package org.onebox.trains.command;

import static org.onebox.trains.util.Constants.COMMAND;
import static org.onebox.trains.util.Constants.DISTANCE;
import static org.onebox.trains.util.Constants.NUMBER_OF_ROUTES;
import static org.onebox.trains.util.Constants.PARAMETERS;
import static org.onebox.trains.util.Constants.SHORTEST_PATH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.onebox.trains.exception.NoSuchRouteException;
import org.onebox.trains.graph.Graph;
import org.onebox.trains.path.PathCounter;
import org.onebox.trains.path.ShortestPath;
import org.onebox.trains.read.Reader;
import org.onebox.trains.route.Route;
import org.onebox.trains.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandProcessor implements Command {
	
	@Autowired
	private Graph graph;
	
	@Autowired
	private Route route;
	
	@Autowired
	private PathCounter pathCounter;
	
	@Autowired
	private ShortestPath shortestPath;
	
	@Autowired
	private Reader reader;

	@Override
	public void processCommands(final String commandFile, final String graphFile) throws IOException {
		List<String> commands = reader.read(commandFile);
		for(String command : commands) {
			String[] commandArray = command.replaceAll("\\s","").split("[\\s#]");
			switch(commandArray[COMMAND]) {
				case DISTANCE:
					processDistanceCommand(commandArray[PARAMETERS], graphFile);
					break;
				case NUMBER_OF_ROUTES:
					processNumberOfRoutesCommand(commandArray[PARAMETERS], graphFile);
					break;
				case SHORTEST_PATH:
					processShoretesPathCommand(commandArray[PARAMETERS], graphFile);
					break;
				default:
					break;
			}
		}
	}
	
	private void processDistanceCommand(final String command, final String graphFile) throws IOException {
		List<Integer> routePath = new ArrayList<Integer>();
		StringBuilder routeStr = new StringBuilder();
		
		String[] nodes = command.split("[\\s-]");
		for(String node : nodes) {
			routePath.add(Node.valueOf(node).ordinal());
			routeStr.append(node + "");
		}
		
		try {
			int distance = route.calculateRouteDistance(routePath, graph.getGraph(graphFile));
			System.out.println("Route: " + routeStr + ", Distance: " + distance);
		} catch(NoSuchRouteException e) {
			System.out.println("Route: " + routeStr + ", NO SUCH ROUTE");
		}
	}
	
	private void processNumberOfRoutesCommand(final String command, final String graphFile) throws IOException {
		String[] nodes = command.split("[\\s-]");
		int origin = Node.valueOf(nodes[0]).ordinal();
		int destination = Node.valueOf(nodes[1]).ordinal();
		int routesNumber = pathCounter.getNumberOfPaths(origin, destination, graph.getGraph(graphFile));
		System.out.println("Number of Routes Between: " + nodes[0] + " and " + nodes[1] + " :" + routesNumber);
	}
	
	private void processShoretesPathCommand(final String command, final String graphFile) throws IOException {
		String[] nodes = command.split("[\\s-]");
		int origin = Node.valueOf(nodes[0]).ordinal();
		int destination = Node.valueOf(nodes[1]).ordinal();
		int pathCount = shortestPath.getShortestPath(origin, destination, graph.getGraph(graphFile));
		System.out.println("Shortest Path Between Nodes: " + nodes[0] + " and " + nodes[1] + " :" + pathCount);
	}
}
