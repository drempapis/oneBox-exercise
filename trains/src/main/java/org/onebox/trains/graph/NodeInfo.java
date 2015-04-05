package org.onebox.trains.graph;

import org.onebox.trains.util.Node;

public class NodeInfo {
	private String origin;
	private String destination;
	private int weight;
	private static int maxNode;
	
	NodeInfo(final String origin, final String destination, final int weight) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
		setMaxNode(origin, destination);
	}
	
	private void setMaxNode(final String origin, final String destination) {
		int originOrdinal = Node.valueOf(origin).ordinal();
		if(originOrdinal > maxNode) {
			maxNode = originOrdinal;
		}
		
		int destinationOrdinal = Node.valueOf(destination).ordinal();
		if(destinationOrdinal > maxNode) {
			maxNode = destinationOrdinal;
		}
	}
	
	public static int getMaxNode() {
		return maxNode;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}
}
