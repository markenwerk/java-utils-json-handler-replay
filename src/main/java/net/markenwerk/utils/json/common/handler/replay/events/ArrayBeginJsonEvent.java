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

import net.markenwerk.utils.json.handler.JsonHandler;

/**
 * An {@link ArrayBeginJsonEvent} is a {@link JsonEvent} that represents a call
 * to {@link JsonHandler#onArrayBegin()}.
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class ArrayBeginJsonEvent implements JsonEvent {

	
	
	@Override
	public JsonEventType getType() {
		return JsonEventType.ARRAY_BEGIN;
	}

	@Override
	public void onHandle(JsonHandler<?> handler) {
		handler.onArrayBegin();
	}

	@Override
	public int hashCode() {
		return 25;
	}

	@Override
	public boolean equals(Object object) {
		return null != object && object instanceof ArrayBeginJsonEvent;
	}

	@Override
	public String toString() {
		return "ArrayBeginJsonEvent []";
	}

}
