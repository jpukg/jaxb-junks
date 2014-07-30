package com.zberg.sample;

import java.io.File;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.zberg.sample.jaxb.Itemtype;

public class ItemtypeConverterTest {
	@Rule
	public TemporaryFolder tempFolderRule = new TemporaryFolder();

	@Test
	public void notifyNewObject_always_fileWithItemsToString() throws Exception {
		// arrange
		final File outputFile = tempFolderRule.newFile("output.txt");
		final ItemtypeConverter testee = new ItemtypeConverter(outputFile.getAbsolutePath());
		final Itemtype item = new Itemtype();
		// act
		testee.newObjectParsed(item);
		testee.finished();
		// assert
		final String result = Files.toString(outputFile, Charsets.UTF_8);
		final String expected = item.toString() + System.lineSeparator();
		Assert.assertEquals(expected, result);
	}
}
