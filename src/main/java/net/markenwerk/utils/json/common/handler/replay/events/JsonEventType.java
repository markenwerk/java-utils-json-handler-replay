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

/**
 * A {@link JsonEventType} describes the type of a {@link JsonEvent}.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public enum JsonEventType {

	/**
	 * The {@link JsonEvent} is a {@link DocumentBeginJsonEvent}.
	 */
	DOCUMENT_BEGIN,

	/**
	 * The {@link JsonEvent} is a {@link DocumentBeginJsonEvent}.
	 */
	DOCUMENT_END,

	/**
	 * The {@link JsonEvent} is a {@link ArrayBeginJsonEvent}.
	 */
	ARRAY_BEGIN,

	/**
	 * The {@link JsonEvent} is a {@link ArrayEndJsonEvent}.
	 */
	ARRAY_END,

	/**
	 * The {@link JsonEvent} is a {@link ObjectBeginJsonEvent}.
	 */
	OBJECT_BEGIN,

	/**
	 * The {@link JsonEvent} is a {@link ObjectEndJsonEvent}.
	 */
	OBJECT_END,

	/**
	 * The {@link JsonEvent} is a {@link NameJsonEvent}.
	 */
	NAME,

	/**
	 * The {@link JsonEvent} is a {@link NextJsonEvent}.
	 */
	NEXT,

	/**
	 * The {@link JsonEvent} is a {@link NullJsonEvent}.
	 */
	NULL,

	/**
	 * The {@link JsonEvent} is a {@link BooleanJsonEvent}.
	 */
	BOOLEAN,

	/**
	 * The {@link JsonEvent} is a {@link LongJsonEvent}.
	 */
	LONG,

	/**
	 * The {@link JsonEvent} is a {@link DoubleJsonEvent}.
	 */
	DOUBLE,

	/**
	 * The {@link JsonEvent} is a {@link StringJsonEvent}.
	 */
	STRING;

}
