package org.onebox.trains.graph;

import static org.onebox.trains.util.Constants.INF; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.onebox.trains.read.FileReader;
import org.onebox.trains.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphImpl implements Graph  {
	
	@Autowired
	private FileReader fileReader;
	
	public GraphImpl() {
		super();
	}

	@Override
	public int[][] getGraph(final String graphFile) throws IOException {
		List<NodeInfo> nodeInfoList = new ArrayList<NodeInfo>();
		
		List<String> lines = fileReader.read(graphFile);
		for(String line : lines) {
			nodeInfoList.addAll(getNodesFromLine(line));
		}

		return createGraph(NodeInfo.getMaxNode(), nodeInfoList);		
	}
	
	private List<NodeInfo> getNodesFromLine(final String line) {
		List<NodeInfo> nodeInfoList = new ArrayList<NodeInfo>();
		for (String entry : line.split("[\\s,]")) {
			if (!entry.isEmpty()) {
				if (entry.length() < 3) {
					throw new IllegalArgumentException("Bad graph specification: " + entry);
				}

				String origin = entry.substring(0, 1);
				String destination = entry.substring(1, 2);
				int weight = 0;
				try {
					weight = Integer.parseInt(entry.substring(2));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Bad graph specification: " + entry + ": "
									+ entry.substring(2)+ " is not a valid integer");
				}
				nodeInfoList.add(new NodeInfo(origin, destination, weight));
			}
		}
		return nodeInfoList;
	}
	
	private int[][] createGraph(final int maxNode, final List<NodeInfo> nodeInfoList) {
		int size = maxNode + 1;
		int[][] graph = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				graph[i][j] = INF;
			}
		}
		
		for(NodeInfo node : nodeInfoList) {
			int originOrdinal = Node.valueOf(node.getOrigin()).ordinal();
			int destinationOrdinal = Node.valueOf(node.getDestination()).ordinal();
			graph[originOrdinal][destinationOrdinal] = node.getWeight();
		}
		return graph;
	}

}
