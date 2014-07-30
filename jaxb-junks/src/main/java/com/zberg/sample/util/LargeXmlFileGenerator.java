package com.zberg.sample.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Helper for creating large xml-files.
 *
 * @author zberg
 *
 */
public class LargeXmlFileGenerator {
	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		if (args.length == 0) {
			throw new IllegalArgumentException("1 arg needed: amount of item-tags to create");
		}
		final int itemsToCreate = getAmountOfItemsToCreateFromArgs(args);
		final PrintWriter pw = new PrintWriter(new FileWriter(new File("target/shiporder_" + itemsToCreate + "items.xml")));
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<shiporder orderid=\"889923\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"shiporder.xsd\">");
		generateSomeItems(itemsToCreate, pw);
		pw.println("</shiporder>");
		pw.close();
	}

	private static int getAmountOfItemsToCreateFromArgs(final String[] args) {
		try {
			return Integer.parseInt(args[0]);
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException("first argument has to be an integer");
		}
	}

	private static void generateSomeItems(final int itemsToCreate, final PrintWriter pw) throws Exception {
		for (int i = 0; i < itemsToCreate; i++) {
			pw.println("	<item>");
			pw.println("		<title>Title for item " + i + "</title>");
			pw.println("		<note>Note for item " + i + "</note>");
			pw.println("		<quantity>" + i + "</quantity>");
			pw.println("		<price>" + i + ".50</price>");
			pw.println("	</item>");
		}
	}
}
