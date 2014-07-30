package com.zberg.sample;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zberg.sample.jaxb.Itemtype;

public class ItemtypeParserTest {
	@Test
	public void parse_fileWithOneItem_OneItemMarshalled() throws Exception {
		// arrange
		final File xmlFile = new File(getClass().getResource("testfile_1_item.xml").toURI());
		final String pathToXml = xmlFile.getAbsolutePath();
		final ItemtypeParser parser = new ItemtypeParser(pathToXml);
		final TestItemtypeListener testListener = new TestItemtypeListener();
		parser.registerListener(testListener);
		// act
		parser.parse();
		// assert
		Assert.assertEquals("wrong amount of items parsed", 1, testListener.items.size());
	}

	@Test
	public void parse_fileWithOneItem_itemAsExpected() throws Exception {
		// arrange
		final File xmlFile = new File(getClass().getResource("testfile_1_item.xml").toURI());
		final String pathToXml = xmlFile.getAbsolutePath();
		final ItemtypeParser parser = new ItemtypeParser(pathToXml);
		final TestItemtypeListener testListener = new TestItemtypeListener();
		parser.registerListener(testListener);
		// act
		parser.parse();
		// assert
		final Itemtype item = testListener.items.get(0);
		Assert.assertEquals("item content not correct", "a title", item.getTitle());
		Assert.assertEquals("item content not correct", "a note", item.getNote());
		Assert.assertEquals("item content not correct", new BigDecimal("1.50"), item.getPrice());
		Assert.assertEquals("item content not correct", BigInteger.ONE, item.getQuantity());
	}

	@Test
	public void parse_fileWithTenItems_TenItemMarshalled() throws Exception {
		// arrange
		final File xmlFile = new File(getClass().getResource("testfile_10_item.xml").toURI());
		final String pathToXml = xmlFile.getAbsolutePath();
		final ItemtypeParser parser = new ItemtypeParser(pathToXml);
		final TestItemtypeListener testListener = new TestItemtypeListener();
		parser.registerListener(testListener);
		// act
		parser.parse();
		// assert
		Assert.assertEquals("wrong amount of items parsed", 10, testListener.items.size());
	}

	private static class TestItemtypeListener implements ParseEventListener<Itemtype> {
		private final List<Itemtype> items = new ArrayList<Itemtype>();

		@Override
		public void newObjectParsed(final Itemtype newObject) {
			items.add(newObject);
		}

		@Override
		public void finished() {
			// nothing to do
		}
	}
}
