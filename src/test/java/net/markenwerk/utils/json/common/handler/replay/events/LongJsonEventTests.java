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
import net.markenwerk.utils.json.common.handler.replay.FailingJsonHandler;
import net.markenwerk.utils.json.common.handler.replay.events.LongJsonEvent;
import net.markenwerk.utils.json.handler.JsonHandler;

@SuppressWarnings("javadoc")
public class LongJsonEventTests {



	@Test
	public void getValue_sameValue() {

		Assert.assertEquals(42, new LongJsonEvent(42).getValue());

	}

	@Test
	public void getType_isLong() {

		Assert.assertEquals(JsonEventType.LONG, new LongJsonEvent(42).getType());

	}

	
	@Test
	public void onHandle_onLong() {

		JsonHandler<?> handler = new FailingJsonHandler() {

			private boolean called;

			@Override
			public void onLong(long value) throws JsonException {
				if (called) {
					throw new AssertionError("Second call to onLong()");
				}
				called = true;
			}

			@Override
			public Void getResult() throws JsonException {
				if (!called) {
					throw new AssertionError("Missing call to onLong()");
				}
				return null;
			}

		};

		new LongJsonEvent(42).onHandle(handler);

		handler.getResult();

	}
	
	@Test
	public void hashCode_sameAsValue() {

		Assert.assertEquals(Long.valueOf(42).hashCode(), new LongJsonEvent(42).hashCode());

	}

	@Test
	public void equals_isEqual() {

		Assert.assertEquals(new LongJsonEvent(42), new LongJsonEvent(42));

	}

	@Test
	public void equals_isNotEqual() {

		Assert.assertNotEquals(new LongJsonEvent(42), new LongJsonEvent(23));

	}

}
