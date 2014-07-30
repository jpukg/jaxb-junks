jaxb-junks
==========

Handling large xml files with stax and jaxb

jaxb is the easiest way to parse an xml-file. but what if the xml-file is about 1GB?

i tried to play with this problem and found a good solution: combine stax with jaxb and marshal junk for junk

as an entrypoint i took this sample xml and generated several million item-tags

<shiporder orderid="889923">
	<item>
		<title>a title</title>
		<note>a note</note>
		<quantity>1</quantity>
		<price>1.50</price>
	</item>
	item ....
</shiporder>

the com.zberg.sample.ItemtypeParser takes the file and parses and unmarshalls item per item and notifies listeners (maybe converters?)

