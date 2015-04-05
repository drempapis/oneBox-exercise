package org.onebox.trains.graph;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.trains.TrainsConfig;
import org.onebox.trains.util.GraphInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainsConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class GraphTest {

	@Autowired
	private Graph graph;

	@Test
	public void testReadGraph_Success() throws IOException  {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("graph-input.txt").getFile());
		int[][] matrix = graph.getGraph(file.getAbsolutePath());
		assertArrayEquals(matrix, GraphInitializer.getMockedGraph());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadGraphSpecification() throws IOException  {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("bad-graph-input.txt").getFile());
		graph.getGraph(file.getAbsolutePath());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadNumberFormat() throws IOException  {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("number-format-graph-input.txt").getFile());
		graph.getGraph(file.getAbsolutePath());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNoFileProvided() throws IOException  {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(null).getFile());
		graph.getGraph(file.getAbsolutePath());
	}
}
