package org.onebox.trains.read;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.trains.TrainsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrainsConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ReaderTest {

	@Autowired
	private Reader reader;

	@Test
	public void testReadCommandsFile() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("commands.txt").getFile());
			List<String> lines = reader.read(file.getAbsolutePath());
			assertThat(lines.size(), is(10));
		} catch (IOException e) {
			Assert.fail();
		}
	}

	@Test(expected = NullPointerException.class)
	public void testNullInputStream() {
		try {
			reader.read(null);
		} catch (IOException e) {
			Assert.fail();
		}
	}
}