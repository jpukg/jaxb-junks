//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2014.07.30 um 11:28:21 PM CEST
//
package com.zberg.sample.jaxb;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.Objects;

/**
 * <p>
 * Java-Klasse für itemtype complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="itemtype">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{}stringtype"/>
 *         &lt;element name="note" type="{}stringtype" minOccurs="0"/>
 *         &lt;element name="quantity" type="{}inttype"/>
 *         &lt;element name="price" type="{}dectype"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemtype", propOrder = { "title", "note", "quantity", "price" })
public class Itemtype {
	@XmlElement(required = true)
	protected String title;
	protected String note;
	@XmlElement(required = true)
	protected BigInteger quantity;
	@XmlElement(required = true)
	protected BigDecimal price;

	/**
	 * Ruft den Wert der title-Eigenschaft ab.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Legt den Wert der title-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTitle(final String value) {
		this.title = value;
	}

	/**
	 * Ruft den Wert der note-Eigenschaft ab.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Legt den Wert der note-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setNote(final String value) {
		this.note = value;
	}

	/**
	 * Ruft den Wert der quantity-Eigenschaft ab.
	 *
	 * @return possible object is {@link BigInteger }
	 *
	 */
	public BigInteger getQuantity() {
		return quantity;
	}

	/**
	 * Legt den Wert der quantity-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link BigInteger }
	 *
	 */
	public void setQuantity(final BigInteger value) {
		this.quantity = value;
	}

	/**
	 * Ruft den Wert der price-Eigenschaft ab.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Legt den Wert der price-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setPrice(final BigDecimal value) {
		this.price = value;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)//
				.add("title", title)//
				.add("note", note)//
				.add("quantity", quantity)//
				.add("price", price)//
				.toString();
	}
}
