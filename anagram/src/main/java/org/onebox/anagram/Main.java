package org.onebox.anagram;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.onebox.anagram.algorithm.Anagram;
import org.onebox.anagram.read.Reader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	private static AnnotationConfigApplicationContext ctx;

	public static void main(String[] args) {
		ctx = initializeContext();
		
		if(isParametersValid(args)) {
			Reader reader = ctx.getBean(Reader.class);
			try {
				List<String> lines = reader.read(new FileInputStream(args[0]));
				Anagram anagram = ctx.getBean(Anagram.class);
				printOutput(anagram.isAnagram(lines));
			} catch(IOException e) {
				System.out.println("Error occured");
			}
		}
		destroyContext(ctx);
	}

	private static boolean isParametersValid(final String[] args) {
		boolean isValid = true;
		if (args.length != 1) {
			isValid = false;
			System.out.println("Invalid Parameters");
			System.out.println("Execute: java -jar anagram.jar [file]");
			System.out.println("\t Where [file]: the poem to be checked.");
		}
		return isValid;
	}
	
	private static void printOutput(boolean isAnagram) {
		System.out.println("-----------------------");
		System.out.println("Anagram: " + isAnagram);
		System.out.println("-----------------------");
	}

	private static AnnotationConfigApplicationContext initializeContext() {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AnagramConfig.class);
		ctx.refresh();
		return ctx;
	}

	private static void destroyContext(
			final AnnotationConfigApplicationContext ctx) {
		ctx.close();
	}
}
