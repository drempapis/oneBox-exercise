package org.onebox.trains.path;

import static org.junit.Assert.assertEquals;  
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.trains.TrainsConfig;
import org.onebox.trains.exception.ValidationInputException;
import org.onebox.trains.graph.Graph;
import org.onebox.trains.path.PathCounter;
import org.onebox.trains.util.GraphInitializer;
import org.onebox.trains.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.onebox.trains.util.Constants.INF;
import static org.onebox.trains.util.Constants.MOCK_FILENAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainsConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class PathCounterTest {

	@Autowired
	private PathCounter pathCounter;

	private Graph graph;

	@Before
	public void setup() throws IOException {
		graph = mock(Graph.class);
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(GraphInitializer.getMockedGraph());
	}

	@Test(expected= ValidationInputException.class)
	public void testSameNode() throws Exception {
		pathCounter.getNumberOfPaths(Node.C.ordinal(),Node.C.ordinal(), graph.getGraph(MOCK_FILENAME));
	}

	@Test
	public void testNumberOfPaths_AC() throws Exception {
		assertEquals(4, pathCounter.getNumberOfPaths(Node.A.ordinal(),Node.C.ordinal(), graph.getGraph(MOCK_FILENAME)));
	}

	@Test
	public void testNumberOfPaths_AE() throws Exception {
		assertEquals(5, pathCounter.getNumberOfPaths(Node.A.ordinal(),Node.E.ordinal(), graph.getGraph(MOCK_FILENAME)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_StartNode() throws Exception {
		pathCounter.getNumberOfPaths(-1, Node.A.ordinal(), graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_StartNode_INF() throws Exception {
		pathCounter.getNumberOfPaths(INF, Node.A.ordinal(), graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_EndNode() throws Exception {
		pathCounter.getNumberOfPaths(Node.A.ordinal(), -1, graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalid_EndNode_INF() throws Exception {
		pathCounter.getNumberOfPaths(Node.A.ordinal(), INF, graph.getGraph(MOCK_FILENAME));
	}
	
	@Test(expected=ValidationInputException.class)
	public void testInvalidGraph() throws Exception {
		int[][] invalidGraph = new int[1][1];
		assertEquals(9, pathCounter.getNumberOfPaths(Node.A.ordinal(), Node.C.ordinal(), invalidGraph));
	}
}