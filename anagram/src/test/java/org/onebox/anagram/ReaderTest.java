package org.onebox.anagram;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.anagram.read.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnagramConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ReaderTest {

	@Autowired
	private Reader reader;

	@Test
	public void testToLowerCase() {
		String source = "ToLowerCase";
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(source, "UTF-8");
			List<String> lines = reader.read(is);
			assertThat(lines.get(0), is("tolowercase"));
		} catch (IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void testDeleteWhiteSpaces() {
		String source = "To Lower Case";
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(source, "UTF-8");
			List<String> lines = reader.read(is);
			assertThat(lines.get(0), is("tolowercase"));
		} catch (IOException e) {
			Assert.fail();
		}
	}

    @Test
	public void testDeleteNonAlphabetic() {
		String source = "To`Lower, Case;";
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(source, "UTF-8");
			List<String> lines = reader.read(is);
			assertThat(lines.get(0), is("tolowercase"));
		} catch (IOException e) {
			Assert.fail();
		}
	}
    
    @Test
   	public void testDeleteAccents() {
   		String source = "To`Lower Cáse";
   		InputStream is = null;
   		try {
   			is = IOUtils.toInputStream(source, "UTF-8");
   			List<String> lines = reader.read(is);
   			assertThat(lines.get(0), is("tolowercase"));
   		} catch (IOException e) {
   			Assert.fail();
   		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullInputStream() {
		InputStream is = null;
		try {
			reader.read(is);
		} catch (IOException e) {
			Assert.fail();
		}
	}

}