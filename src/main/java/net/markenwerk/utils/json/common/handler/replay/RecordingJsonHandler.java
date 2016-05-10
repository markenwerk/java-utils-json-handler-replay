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

import java.util.LinkedList;
import java.util.List;

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.JsonIndexException;
import net.markenwerk.utils.json.common.JsonValueException;
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
import net.markenwerk.utils.json.handler.IdleJsonHandler;

/**
 * A {@link RecordingJsonHandler} is an {@link IdleJsonHandler} that records
 * every reported event as a {@link JsonEvent} calculates a {@link JsonReplay}
 * from the recorded {@link JsonEvent JsonEvents} as a result.
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class RecordingJsonHandler extends IdleJsonHandler<JsonReplay> {

	private final List<JsonEvent> events = new LinkedList<JsonEvent>();

	@Override
	public void onDocumentBegin() throws JsonException {
		events.add(new DocumentBeginJsonEvent());
	}

	@Override
	public void onDocumentEnd() throws JsonException {
		events.add(new DocumentEndJsonEvent());
	}

	@Override
	public void onArrayBegin() throws JsonException {
		events.add(new ArrayBeginJsonEvent());
	}

	@Override
	public void onArrayEnd() throws JsonException {
		events.add(new ArrayEndJsonEvent());
	}

	@Override
	public void onObjectBegin() throws JsonException {
		events.add(new ObjectBeginJsonEvent());
	}

	@Override
	public void onObjectEnd() throws JsonException {
		events.add(new ObjectEndJsonEvent());
	}

	@Override
	public void onName(String name) throws JsonIndexException, JsonException {
		events.add(new NameJsonEvent(name));
	}

	@Override
	public void onNext() throws JsonException {
		events.add(new NextJsonEvent());
	}

	@Override
	public void onNull() throws JsonException {
		events.add(new NullJsonEvent());
	}

	@Override
	public void onBoolean(boolean value) throws JsonException {
		events.add(new BooleanJsonEvent(value));
	}

	@Override
	public void onLong(long value) throws JsonException {
		events.add(new LongJsonEvent(value));
	}

	@Override
	public void onDouble(double value) throws JsonValueException, JsonException {
		events.add(new DoubleJsonEvent(value));
	}

	@Override
	public void onString(String value) throws JsonValueException, JsonException {
		events.add(new StringJsonEvent(value));
	}

	@Override
	public JsonReplay getResult() throws JsonException {
		return new JsonReplay(events);
	}

}
