package org.onebox.anagram.algorithm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class AnagramImpl implements Anagram {

	@Override
	public boolean isAnagram(final List<String> lines) {
		validateInput(lines);

		boolean isAnagram = true;
		for (int i = 0; i < lines.size() - 1 && isAnagram == true; i++) {
			for (int j = i + 1; j < lines.size(); j++) {
				String firstLine = lines.get(i);
				String secondLine = lines.get(j);
				if(!compareTwoLines(firstLine, secondLine)) {
					isAnagram = false;
					break;
				}
			}
		}
		return isAnagram;
	}
	
	private void validateInput(final List<String> lines) {
		if (lines == null || lines.size() <= 1 ) {
			throw new IllegalArgumentException();
		}
	}

	private boolean compareTwoLines(final String firstLine, final String secondLine) {
		final char[] first = firstLine.toCharArray();
		final char[] second = secondLine.toCharArray();
		final ConcurrentMap<Character, AtomicLong> map = new ConcurrentHashMap<Character, AtomicLong>();
		
		for (int i = 0; i < first.length; i++) {
			map.putIfAbsent(first[i], new AtomicLong(0));
			map.get(first[i]).incrementAndGet();
		}
		
		for (int i = 0; i < second.length; i++) {
			map.get(second[i]).decrementAndGet();
		}
		
		boolean isLinesAnagram = true;
		for(Map.Entry<Character, AtomicLong> entry : map.entrySet()) {
			if(entry.getValue().get() != 0) {
				isLinesAnagram = false;
				break;
			}
		}
		return isLinesAnagram;
	}
}
