package com.zberg.sample;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericParser<T> {
	private final List<ParseEventListener<T>> listeners = new ArrayList<ParseEventListener<T>>();

	public void registerListener(final ParseEventListener<T> listener) {
		synchronized (listener) {
			listeners.add(listener);
		}
	}

	public void removeListener(final ParseEventListener<T> listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	protected void notifyFinished() {
		synchronized (listeners) {
			for (final ParseEventListener<T> listener : listeners) {
				listener.finished();
			}
		}
	}

	protected void notifyListeners(final T item) {
		synchronized (listeners) {
			for (final ParseEventListener<T> listener : listeners) {
				listener.newObjectParsed(item);
			}
		}
	}

	public abstract void parse();
}
