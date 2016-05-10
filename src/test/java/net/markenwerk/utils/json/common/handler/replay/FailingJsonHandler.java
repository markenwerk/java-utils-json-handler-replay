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

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.JsonValueException;
import net.markenwerk.utils.json.handler.IdleJsonHandler;

@SuppressWarnings("javadoc")
public class FailingJsonHandler extends IdleJsonHandler<Void> {

	@Override
	public void onDocumentBegin() throws JsonException {
		throw new AssertionError("Unexpected call to onDocumentBegin()");
	}

	@Override
	public void onDocumentEnd() throws JsonException {
		throw new AssertionError("Unexpected call to onDocumentEnd()");
	}

	@Override
	public void onArrayBegin() throws JsonException {
		throw new AssertionError("Unexpected call to onArrayBegin()");
	}

	@Override
	public void onArrayEnd() throws JsonException {
		throw new AssertionError("Unexpected call to onArrayEnd()");
	}

	@Override
	public void onObjectBegin() throws JsonException {
		throw new AssertionError("Unexpected call to onObjectBegin()");
	}

	@Override
	public void onObjectEnd() throws JsonException {
		throw new AssertionError("Unexpected call to onObjectEnd()");
	}

	@Override
	public void onName(String name) throws JsonException {
		throw new AssertionError("Unexpected call to onName(" + name + ")");
	}

	@Override
	public void onNext() throws JsonException {
		throw new AssertionError("Unexpected call to onNext()");
	}

	@Override
	public void onNull() throws JsonException {
		throw new AssertionError("Unexpected call to onNull()");
	}

	@Override
	public void onBoolean(boolean value) throws JsonException {
		throw new AssertionError("Unexpected call to onBoolean(" + value + ")");
	}

	@Override
	public void onLong(long value) throws JsonException {
		throw new AssertionError("Unexpected call to onLong(" + value + ")");
	}

	@Override
	public void onDouble(double value) throws JsonValueException, JsonException {
		throw new AssertionError("Unexpected call to onDouble(" + value + ")");
	}

	@Override
	public void onString(String value) throws JsonValueException, JsonException {
		throw new AssertionError("Unexpected call to onString(" + value + ")");
	}

	@Override
	public Void getResult() throws JsonException {
		return null;
	}

}
