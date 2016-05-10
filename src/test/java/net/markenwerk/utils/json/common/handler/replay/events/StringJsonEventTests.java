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

import org.junit.Assert;
import org.junit.Test;

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.JsonValueException;
import net.markenwerk.utils.json.common.handler.replay.FailingJsonHandler;
import net.markenwerk.utils.json.handler.JsonHandler;

@SuppressWarnings("javadoc")
public class StringJsonEventTests {

	@Test(expected = JsonValueException.class)
	public void create_nullString() {

		new StringJsonEvent(null);

	}

	@Test
	public void getValue_sameValue() {

		Assert.assertEquals("foo", new StringJsonEvent("foo").getValue());

	}

	@Test
	public void getType_isString() {

		Assert.assertEquals(JsonEventType.STRING, new StringJsonEvent("foo").getType());

	}

	@Test
	public void onHandle_onString() {

		JsonHandler<?> handler = new FailingJsonHandler() {

			private boolean called;

			@Override
			public void onString(String value) throws JsonException {
				if (called) {
					throw new AssertionError("Second call to onString()");
				}
				called = true;
			}

			@Override
			public Void getResult() throws JsonException {
				if (!called) {
					throw new AssertionError("Missing call to onString()");
				}
				return null;
			}

		};

		new StringJsonEvent("foo").onHandle(handler);

		handler.getResult();

	}

	@Test
	public void hashCode_sameAsValue() {

		Assert.assertEquals("foo".hashCode(), new StringJsonEvent("foo").hashCode());

	}

	@Test
	public void equals_isEqual() {

		Assert.assertEquals(new StringJsonEvent("foo"), new StringJsonEvent("foo"));

	}

	@Test
	public void equals_isNotEqual() {

		Assert.assertNotEquals(new StringJsonEvent("foo"), new StringJsonEvent("bar"));

	}

}
