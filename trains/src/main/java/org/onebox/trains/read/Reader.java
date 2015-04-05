package org.onebox.trains.read;

import java.io.IOException;
import java.util.List;

public interface Reader {
	List<String> read(final String fileName) throws IOException;
}
