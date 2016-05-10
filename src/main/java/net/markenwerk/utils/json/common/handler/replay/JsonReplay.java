/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.utils.json.common.handler.replay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.markenwerk.utils.json.common.handler.replay.events.JsonEvent;
import net.markenwerk.utils.json.handler.JsonHandler;

/**
 * A {@link JsonReplay} holds a {@link List} of {@link JsonEvent JsonEvents} and
 * is able to {@link JsonReplay#replay(JsonHandler) replay} the events to a
 * {@link JsonHandler}.
 * 
 * @author tk
 */
public final class JsonReplay implements Iterable<JsonEvent> {

	private final List<JsonEvent> events;

	/**
	 * Creates a new {@link JsonReplay} for the given {@link JsonEvent
	 * JsonEvents}.
	 * 
	 * @param events
	 *            The {@link JsonEvent JsonEvents} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link JsonEvent JsonEvents} are {@literal null}
	 *             or if any of the given {@link JsonEvent JsonEvents} is
	 *             {@literal null}.
	 */
	public JsonReplay(List<? extends JsonEvent> events) throws IllegalArgumentException {
		if (null == events) {
			throw new IllegalArgumentException("The given events are null");
		}
		this.events = new ArrayList<JsonEvent>(events.size());
		for (int i = 0, n = events.size(); i < n; i++) {
			JsonEvent event = events.get(i);
			if (null == event) {
				throw new IllegalArgumentException("The event at position " + i + " is null");
			}
			this.events.add(event);
		}
	}

	@Override
	public Iterator<JsonEvent> iterator() {
		return events.iterator();
	}

	/**
	 * Replays the recorded events to the given {@link JsonHandler}.
	 * 
	 * @param handler
	 *            The {@link JsonHandler} to be used.
	 * @return The result of the given {@link JsonHandler}.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link JsonHandler} is {@literal null}.
	 */
	public <Result> Result replay(JsonHandler<Result> handler) throws IllegalArgumentException {
		if (null == handler) {
			throw new IllegalArgumentException("The given handler is null");
		}
		for (JsonEvent event : events) {
			event.onHandle(handler);
		}
		return handler.getResult();
	}

	/**
	 * Returns the number of recorded events.
	 * 
	 * @return The number of recorded events.
	 */
	public int size() {
		return events.size();
	}

	/**
	 * Asserts that the recorded event at the given index is equal to the given
	 * {@link JsonEvent}.
	 * 
	 * @param event
	 *            The {@link JsonEvent} to be used.
	 * @param index
	 *            The index to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link JsonEvent} is {@literal null}.
	 * @throws IndexOutOfBoundsException
	 *             If the given index is negative or equal to or larger than the
	 *             number of recorded events.
	 * @throws AssertionError
	 *             If the recorded event at the given index is not equal to the
	 *             given {@link JsonEvent}.
	 */
	public void assertEquals(JsonEvent event, int index) throws IllegalArgumentException, IndexOutOfBoundsException,
			AssertionError {
		if (null == event) {
			throw new IllegalArgumentException("The expected event for index " + index + " is null");
		}
		if (!events.get(index).equals(event)) {
			throw new AssertionError("Event at index " + index + " was " + events.get(index) + ", but " + event
					+ " was expected");
		}
	}

	/**
	 * Asserts that the recorded events are equal to the given sequence of
	 * {@link JsonEvent JsonEvents}.
	 * 
	 * @param events
	 *            The {@link JsonEvent JsonEvents} to be used.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given sequence of {@link JsonEvent JsonEvents} is
	 *             {@literal null}.
	 * @throws AssertionError
	 *             If the number of recorded events is not equal to the length
	 *             of the given sequence of {@link JsonEvent JsonEvents} or if
	 *             any of the recorded events is not equal to the given
	 *             {@link JsonEvent} at the corresponding index.
	 */
	public void assertEquals(JsonEvent... events) throws IllegalArgumentException, AssertionError {
		if (null == events) {
			throw new IllegalArgumentException("The expected events are null");
		}
		if (events.length != this.events.size()) {
			throw new AssertionError(events.length + " events are expected, but " + this.events.size()
					+ " events are recorded");
		}
		for (int i = 0, n = events.length; i < n; i++) {
			assertEquals(events[i], i);
		}
	}

}
