package org.onebox.trains.command;

import java.io.IOException;

public interface Command {
	 void processCommands(final String commandFile, final String graphFile) throws IOException;
}
