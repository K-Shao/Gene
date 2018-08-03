package main;

import java.io.IOException;

import parsing.Gram;
import parsing.Parser;
import parsing.ProjectFrame;

public class Main {

	public static void main(String[] args) {

		try {
			Gram gram = Parser.parseAB1("res/sample.AB1");
			Gram gram2 = Parser.parseAB1("res/sample2.AB1");
			new ProjectFrame(gram, gram2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
