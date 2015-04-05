package org.onebox.anagram.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import static org.onebox.anagram.util.Constants.UTF8;
import static org.onebox.anagram.util.Constants.NON_ALPHABETIC;
import static org.onebox.anagram.util.Constants.WHITE_SPACES;
import static org.onebox.anagram.util.Constants.WORDS_WITH_ACCENTS;;

@Component
public class FileReader implements Reader {
	
	public FileReader() {
		super();
	}

	@Override
	public List<String> read(final InputStream is) throws IOException {
		final List<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is, UTF8));
			String line = null;
			while ((line = br.readLine()) != null) {
				String filteredLine = filterLine(line);
				lines.add(filteredLine);
			}
		} finally {
			br.close();
		}
		return lines;
	}
	
	private String filterLine(final String line) {
		String filteredLine = line.toLowerCase(); 
		filteredLine = removeWhiteSpaces(filteredLine);
		filteredLine = removeNonAlphabeticCharacters(filteredLine);
		filteredLine = removeAccents(filteredLine);
		return filteredLine;
	}
	
	private String removeWhiteSpaces(final String line) {
		return line.replaceAll(WHITE_SPACES, "");
	}
	
	private String removeNonAlphabeticCharacters(final String line) {
		return line.replaceAll(NON_ALPHABETIC, "");
	}
	
	public String removeAccents(String str) {
	    final String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    return nfdNormalizedString.replaceAll(WORDS_WITH_ACCENTS, "");
	}
}
