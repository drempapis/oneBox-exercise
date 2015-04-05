package org.onebox.anagram.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Reader {
	List<String> read(final InputStream is) throws IOException;
}
