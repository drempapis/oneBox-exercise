package org.onebox.trains;

import java.io.IOException;

import org.onebox.trains.command.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	private static AnnotationConfigApplicationContext ctx;
	
	public static void main(String[] args) throws IOException {
		ctx = initializeContext();
		
		if(isParametersValid(args)) {
			final Command commandProcessor = ctx.getBean(Command.class);
			commandProcessor.processCommands(args[0], args[1]);
		}
		destroyContext(ctx);
	}
	
	private static boolean isParametersValid(final String[] args) {
		boolean isValid = true;
		if (args.length != 2) {
			isValid = false;
			System.out.println("-------------------------------------------------");
			System.out.println("Invalid Parameters");
			System.out.println("Execute: java -jar trains.jar [commands] [graph]");
			System.out.println("\t Where [commands]: the commands to be executed.");
			System.out.println("\t Where [graph]: the graph representation.");
			System.out.println("-------------------------------------------------");
		}
		return isValid;
	}
	
	private static AnnotationConfigApplicationContext initializeContext() {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(TrainsConfig.class);
		ctx.refresh();
		return ctx;
	}
	
	private static void destroyContext(final AnnotationConfigApplicationContext ctx) {
		ctx.close();
	}
}
