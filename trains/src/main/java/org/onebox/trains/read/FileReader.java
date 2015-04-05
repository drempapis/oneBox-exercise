package org.onebox.trains.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileReader implements Reader {
	
	public FileReader() {
		super();
	}

	public List<String> read(final String fileName) throws IOException {
		final List<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					lines.add(line);
				}
			}
		} finally {
			br.close();
		}
		return lines;
	}
	
}
