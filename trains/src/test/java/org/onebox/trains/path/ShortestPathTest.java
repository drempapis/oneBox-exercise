package org.onebox.trains.path;

import static org.junit.Assert.assertEquals; 

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.trains.TrainsConfig;
import org.onebox.trains.exception.ValidationInputException;
import org.onebox.trains.graph.Graph;
import org.onebox.trains.path.ShortestPath;
import org.onebox.trains.util.GraphInitializer;
import org.onebox.trains.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.onebox.trains.util.Constants.INF;
import static org.onebox.trains.util.Constants.MOCK_FILENAME;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainsConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ShortestPathTest {
	
	@Autowired
	private ShortestPath shortestPath;
	
	private Graph graph;
	
	@Before
	public void setup() throws IOException {
		graph = mock(Graph.class);
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(GraphInitializer.getMockedGraph());
	}
	
	@Test
	public void testShortestPath_AC() throws Exception {
		assertEquals(9, shortestPath.getShortestPath(Node.A.ordinal(), Node.C.ordinal(), graph.getGraph(MOCK_FILENAME)));
	}
	
	@Test
	public void testShortestPath_AE() throws Exception {
		assertEquals(7, shortestPath.getShortestPath(Node.A.ordinal(), Node.E.ordinal(), graph.getGraph(MOCK_FILENAME)));
	}
	
	@Test
	public void testShortestPath_BE() throws Exception {
		assertEquals(6, shortestPath.getShortestPath(Node.B.ordinal(), Node.E.ordinal(), graph.getGraph(MOCK_FILENAME)));
	}
	
	@Test(expected = ValidationInputException.class)
	public void testSameNode() throws Exception {
		shortestPath.getShortestPath(Node.B.ordinal(), Node.B.ordinal(), graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_StartNode() throws Exception {
		shortestPath.getShortestPath(-1, Node.A.ordinal(), graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_StartNode_INF() throws Exception {
		shortestPath.getShortestPath(INF, Node.A.ordinal(), graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_EndNode() throws Exception {
		shortestPath.getShortestPath(Node.A.ordinal(), -1, graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_EndNode_INF() throws Exception {
		shortestPath.getShortestPath(Node.A.ordinal(), INF, graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=ValidationInputException.class)
	public void testInvalidGraph() throws Exception {
		int[][] invalidGraph = new int[1][1];
		assertEquals(9, shortestPath.getShortestPath(Node.A.ordinal(), Node.C.ordinal(), invalidGraph));
	}
}