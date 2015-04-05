package org.onebox.trains.command;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.onebox.trains.util.Constants.MOCK_FILENAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.onebox.trains.graph.Graph;
import org.onebox.trains.path.PathCounter;
import org.onebox.trains.path.ShortestPath;
import org.onebox.trains.read.Reader;
import org.onebox.trains.route.Route;

public class CommandTest {

	@InjectMocks
	private CommandProcessor command;
	
	@Mock
	private Reader reader;
	
	@Mock
	private Route route;
	
	@Mock
	private Graph graph;
	
	@Mock
	private PathCounter pathCounter;
	
	@Mock
	private ShortestPath shortestPath;
		
	@Before
	public void inti() throws IOException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDistance_Command() throws IOException  {
		when(reader.read(MOCK_FILENAME)).thenReturn(Arrays.asList("distance# A-B-C"));
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(new int[][]{});
		when(route.calculateRouteDistance(Collections.<Integer> emptyList(), new int[][]{})).thenReturn(0);
		
		command.processCommands(MOCK_FILENAME, MOCK_FILENAME);
		verify(reader, times(1)).read(MOCK_FILENAME);
		verify(route, times(1)).calculateRouteDistance(new ArrayList<Integer>(Arrays.asList(0,1,2)), new int[][]{});
		verify(graph, times(1)).getGraph(MOCK_FILENAME);
	}
	
	@Test
	public void testNumberOfRoutes_Command() throws IOException  {
		when(reader.read(MOCK_FILENAME)).thenReturn(Arrays.asList("number_of_routes# A-C"));
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(new int[][]{});
		when(pathCounter.getNumberOfPaths(0, 0, new int[][]{})).thenReturn(0);
		
		command.processCommands(MOCK_FILENAME, MOCK_FILENAME);
		verify(reader, times(1)).read(MOCK_FILENAME);
		verify(pathCounter, times(1)).getNumberOfPaths(0, 2, new int[][]{});
		verify(graph, times(1)).getGraph(MOCK_FILENAME);
	}
	
	@Test
	public void testShortestPath_Command() throws IOException  {
		when(reader.read(MOCK_FILENAME)).thenReturn(Arrays.asList("shortest_path# A-C"));
		when(graph.getGraph(MOCK_FILENAME)).thenReturn(new int[][]{});
		when(shortestPath.getShortestPath(0, 0, new int[][]{})).thenReturn(0);
		
		command.processCommands(MOCK_FILENAME, MOCK_FILENAME);
		verify(reader, times(1)).read(MOCK_FILENAME);
		verify(shortestPath, times(1)).getShortestPath(0, 2, new int[][]{});
		verify(graph, times(1)).getGraph(MOCK_FILENAME);
	}
}
