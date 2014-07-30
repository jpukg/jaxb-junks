package com.zberg.sample;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import com.zberg.sample.jaxb.Itemtype;

/**
 * Parses all {@link Itemtype} elements from a given xml. Example is build for
 * xml documents following the schema <code>shiporder.xsd</code>
 *
 * @author zberg
 *
 */
public class ItemtypeParser extends GenericParser<Itemtype> {
	private final String pathToXml;
	private XMLInputFactory xmlInputFactory;
	private XMLEventReader xmlEventReader;
	private XMLEventReader startTagEventReader;
	private Unmarshaller unMarshaller;
	private FileReader fileReader;

	public ItemtypeParser(final String pathToXml) {
		this.pathToXml = pathToXml;
	}

	@Override
	public void parse() {
		try {
			parseInternal();
		} catch (FileNotFoundException | FactoryConfigurationError | XMLStreamException | JAXBException e) {
			throw new RuntimeException(e.getCause());
		} finally {
			cleanupSilently();
		}
	}

	private void parseInternal() throws FactoryConfigurationError, FileNotFoundException, XMLStreamException, JAXBException {
		initializeEventReaders();
		while (null != previewNextStartTag()) {
			final Itemtype item = unmarshallItemtype(unMarshaller);
			notifyListeners(item);
		}
		notifyFinished();
	}

	private XMLEvent previewNextStartTag() throws XMLStreamException {
		return startTagEventReader.peek();
	}

	private Itemtype unmarshallItemtype(final Unmarshaller unMarshaller) throws JAXBException {
		final JAXBElement<Itemtype> jaxbItem = unMarshaller.unmarshal(xmlEventReader, Itemtype.class);
		return jaxbItem.getValue();
	}

	private Unmarshaller createUnmarshallerForItemType() throws JAXBException {
		final JAXBContext jbContext = JAXBContext.newInstance(Itemtype.class);
		return jbContext.createUnmarshaller();
	}

	private void initializeEventReaders() throws FactoryConfigurationError, FileNotFoundException, XMLStreamException, JAXBException {
		unMarshaller = createUnmarshallerForItemType();
		xmlInputFactory = XMLInputFactory.newInstance();
		fileReader = new FileReader(pathToXml);
		xmlEventReader = xmlInputFactory.createXMLEventReader(fileReader);
		startTagEventReader = xmlInputFactory.createFilteredReader(xmlEventReader, startElementEventFilter());
		// in this case, move to our uber-tag
		startTagEventReader.nextEvent(); // now we are at the <shiporder>-tag
	}

	private EventFilter startElementEventFilter() {
		return new EventFilter() {
			@Override
			public boolean accept(final XMLEvent event) {
				return event.isStartElement();
			}
		};
	}

	private void cleanupSilently() {
		closeEventReaderSilently(startTagEventReader);
		closeEventReaderSilently(xmlEventReader);
		closeFileReaderSilently(fileReader);
	}

	private void closeEventReaderSilently(final XMLEventReader anEventReader) {
		try {
			anEventReader.close();
		} catch (final Exception e) {
			// nothing to do (loggin would be nice :-) )
		}
	}

	private void closeFileReaderSilently(final FileReader afileReader) {
		try {
			afileReader.close();
		} catch (final Exception e) {
			// nothing to do (loggin would be nice :-) )
		}
	}

	public static void main(final String[] args) throws Exception {
		final ItemtypeParser parser = new ItemtypeParser("src/main/resources/shiporder_10000000.xml");
		parser.registerListener(new ItemtypeConverter("target/output.txt"));
		parser.parse();
	}
}
