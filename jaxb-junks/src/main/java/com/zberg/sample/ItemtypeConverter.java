package com.zberg.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import com.zberg.sample.jaxb.Itemtype;

class ItemtypeConverter implements ParseEventListener<Itemtype> {
	private Date startTime;
	private File output = null;
	private PrintWriter printWriter;
	private final String outputFile;

	public ItemtypeConverter(final String outputFile) {
		this.outputFile = outputFile;
		start();
	}

	@Override
	public void newObjectParsed(final Itemtype newObject) {
		printWriter.println(newObject.toString());
	}

	@Override
	public void finished() {
		printWriter.flush();
		printWriter.close();
		final double duration = (new Date().getTime() - startTime.getTime()) / 1000.0d;
		System.out.println("finished in " + duration + " seconds");
	}

	public void start() {
		startTime = new Date();
		output = new File(outputFile);
		try {
			printWriter = new PrintWriter(output);
		} catch (final FileNotFoundException e) {
			throw new RuntimeException(e.getCause());
		}
	}
}