package com.zberg.sample;

/**
 * Receive message, if new object is parsed
 *
 * @author zberg
 *
 * @param <T>
 */
public interface ParseEventListener<T> {
	void newObjectParsed(T newObject);

	void finished();
}
