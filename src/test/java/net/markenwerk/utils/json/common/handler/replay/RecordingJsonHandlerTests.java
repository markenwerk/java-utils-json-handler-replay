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

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.markenwerk.utils.json.common.handler.replay.events.ArrayBeginJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.ArrayEndJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.BooleanJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.DocumentBeginJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.DocumentEndJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.DoubleJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.JsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.LongJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.NameJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.NextJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.NullJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.ObjectBeginJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.ObjectEndJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.StringJsonEvent;

@SuppressWarnings("javadoc")
public class RecordingJsonHandlerTests {

	private RecordingJsonHandler handler;

	@Before
	public void prepareHandler() {

		handler = new RecordingJsonHandler();
	}

	@Test
	public void onDocumentBegin() {

		handler.onDocumentBegin();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new DocumentBeginJsonEvent());

	}

	@Test
	public void onDocumentEnd() {

		handler.onDocumentEnd();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new DocumentEndJsonEvent());

	}

	@Test
	public void onArrayBegin() {

		handler.onArrayBegin();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new ArrayBeginJsonEvent());

	}

	@Test
	public void onArrayEnd() {

		handler.onArrayEnd();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new ArrayEndJsonEvent());

	}

	@Test
	public void onObjectBegin() {

		handler.onObjectBegin();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new ObjectBeginJsonEvent());

	}

	@Test
	public void onObjectEnd() {

		handler.onObjectEnd();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new ObjectEndJsonEvent());

	}

	@Test
	public void onName() {

		handler.onName("foo");

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new NameJsonEvent("foo"));

	}

	@Test
	public void onNext() {

		handler.onNext();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new NextJsonEvent());

	}

	@Test
	public void onNull() {

		handler.onNull();

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new NullJsonEvent());

	}

	@Test
	public void onBoolean() {

		handler.onBoolean(true);

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new BooleanJsonEvent(true));

	}

	@Test
	public void onLong() {

		handler.onLong(42);

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new LongJsonEvent(42));

	}

	@Test
	public void onDouble() {

		handler.onDouble(42.0);

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new DoubleJsonEvent(42.0));

	}

	@Test
	public void onString() {

		handler.onString("foo");

		JsonReplay reply = handler.getResult();

		Assert.assertEquals(1, reply.size());
		reply.assertEquals(new StringJsonEvent("foo"));

	}

	@Test
	public void getResult_notNull() {

		Assert.assertNotNull(handler.getResult());

	}

	@Test
	public void getResult_correctSequence() {

		handler.onDocumentBegin();
		handler.onNull();
		handler.onDocumentEnd();

		Iterator<JsonEvent> iterator = handler.getResult().iterator();

		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new DocumentBeginJsonEvent(), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new NullJsonEvent(), iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(new DocumentEndJsonEvent(), iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

}
