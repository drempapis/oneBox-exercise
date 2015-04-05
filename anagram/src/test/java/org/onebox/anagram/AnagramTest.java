package org.onebox.anagram;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebox.anagram.algorithm.Anagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnagramConfig.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class AnagramTest {

	@Autowired
	private Anagram anagram;

	@Test(expected = IllegalArgumentException.class)
	public void testInputNull() {
		List<String> lines = null;
		anagram.isAnagram(lines);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInputOneLine() {
		List<String> lines = new ArrayList<String>();
		lines.add("First Line");
		anagram.isAnagram(lines);
	}
	
	@Test
	public void testIsAnagram() {
		List<String> lines = new ArrayList<String>();
		lines.add("mistletoeasinusandnosenag");
		lines.add("annualtoastsendsenseigoim");
		lines.add("meitoastangelsandeonsinus");
		assertThat(anagram.isAnagram(lines), is(true));
	}
	
	@Test
	public void testIsNotAnagram() {
		List<String> lines = new ArrayList<String>();
		lines.add("mistletoeasinusandnosenag");
		lines.add("annualtoastsendsenseigoim");
		lines.add("meitoastangelsandeonsinus".replace("e", "a"));
		assertThat(anagram.isAnagram(lines), is(false));
	}
}