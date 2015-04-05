package org.onebox.trains.route;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.onebox.trains.TrainsConfig;
import org.onebox.trains.exception.NoSuchRouteException;
import org.onebox.trains.exception.ValidationInputException;
import org.onebox.trains.graph.Graph;
import org.onebox.trains.route.Route;
import org.onebox.trains.util.GraphInitializer;
import org.onebox.trains.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.onebox.trains.util.Constants.MOCK_FILENAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainsConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class RouteTest {

	@Autowired
	private Route route;

	@Mock
	private Graph graph;

	@Before
	public void setup() throws IOException {
		graph = mock(Graph.class);
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(GraphInitializer.getMockedGraph());
	}

	@Test
	public void testDistanceBetween_ABC() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		routePath.add(Node.B.ordinal());
		routePath.add(Node.C.ordinal());
		assertEquals(9,route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME)));
	}

	@Test
	public void testDistanceBetween_AD() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		routePath.add(Node.D.ordinal());
		assertEquals(5,route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME)));
	}

	@Test
	public void testDistanceBetween_ADC() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		routePath.add(Node.D.ordinal());
		routePath.add(Node.C.ordinal());
		assertEquals(13,route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME)));
	}

	@Test
	public void testDistanceBetween_AEBCD() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		routePath.add(Node.E.ordinal());
		routePath.add(Node.B.ordinal());
		routePath.add(Node.C.ordinal());
		routePath.add(Node.D.ordinal());
		assertEquals(22,route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME)));
	}

	@Test(expected = NoSuchRouteException.class)
	public void testDistanceBetween_AED() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		routePath.add(Node.E.ordinal());
		routePath.add(Node.D.ordinal());
		route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME));
	}

	@Test(expected = ValidationInputException.class)
	public void testValidationInputException() throws Exception {
		List<Integer> routePath = new ArrayList<Integer>();
		routePath.add(Node.A.ordinal());
		route.calculateRouteDistance(routePath, graph.getGraph(MOCK_FILENAME));
	}
}