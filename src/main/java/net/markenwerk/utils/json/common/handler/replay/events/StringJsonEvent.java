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
package net.markenwerk.utils.json.common.handler.replay.events;

import net.markenwerk.utils.json.common.JsonValueException;
import net.markenwerk.utils.json.handler.IdleJsonHandler;
import net.markenwerk.utils.json.handler.JsonHandler;

/**
 * A {@link StringJsonEvent} is a {@link JsonEvent} that represents a call to
 * {@link JsonHandler#onString(String)}.
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */

public final class StringJsonEvent implements JsonEvent {

	private final String value;

	/**
	 * Creates a new {@link StringJsonEvent} for the given value.
	 * 
	 * @param value
	 *            The value to be used.
	 * 
	 * @throws JsonValueException
	 *             If the given value is {@literal null}.
	 */
	public StringJsonEvent(String value) throws JsonValueException {
		IdleJsonHandler.checkString(value);
		this.value = value;
	}

	/**
	 * Returns the value this {@link StringJsonEvent} has been created with.
	 * 
	 * @return The value.
	 */
	public String getValue() {
		return value;
	}

	@Override
	public JsonEventType getType() {
		return JsonEventType.STRING;
	}

	@Override
	public void onHandle(JsonHandler<?> handler) {
		handler.onString(value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (null == object || !(object instanceof StringJsonEvent)) {
			return false;
		} else {
			return value.equals(((StringJsonEvent) object).value);
		}
	}

	@Override
	public String toString() {
		return "StringJsonEvent [value=" + value + "]";
	}

}
