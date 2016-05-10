package net.markenwerk.utils.json.common.handler.replay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.handler.replay.events.DocumentBeginJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.DocumentEndJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.JsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.NextJsonEvent;
import net.markenwerk.utils.json.common.handler.replay.events.NullJsonEvent;

@SuppressWarnings("javadoc")
public class JsonReplayTests {

	@Test(expected = IllegalArgumentException.class)
	public void create_nullEvents() {

		new JsonReplay(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_nullEvent() {

		new JsonReplay(Arrays.asList((JsonEvent) null));

	}

	@Test(expected = IllegalArgumentException.class)
	public void replay_nullHandler() {

		new JsonReplay(new LinkedList<JsonEvent>()).replay(null);

	}

	@Test
	public void replay_correctSequence() {

		List<JsonEvent> events = Arrays.asList(new DocumentBeginJsonEvent(), new NullJsonEvent(),
				new DocumentEndJsonEvent());

		new JsonReplay(events).replay(new FailingJsonHandler() {

			private boolean beginCalled;
			private boolean nullCalled;
			private boolean endCalled;

			@Override
			public void onDocumentBegin() throws JsonException {
				if (beginCalled) {
					throw new AssertionError("Second call to onDocumentBegin()");
				}
				beginCalled = true;
			}

			@Override
			public void onNull() throws JsonException {
				if (!beginCalled) {
					throw new AssertionError("Missing call to onDocumentBegin()");
				}
				if (nullCalled) {
					throw new AssertionError("Second call to onNull()");
				}
				nullCalled = true;
			}

			@Override
			public void onDocumentEnd() throws JsonException {
				if (!nullCalled) {
					throw new AssertionError("Missing call to onNull()");
				}
				if (endCalled) {
					throw new AssertionError("Second call to onDocumentEnd()");
				}
				endCalled = true;
			}

			@Override
			public Void getResult() throws JsonException {
				if (!endCalled) {
					throw new AssertionError("Missing call to onDocumentEnd()");
				}
				return null;
			}

		});

	}

	@Test(expected = IllegalArgumentException.class)
	public void assertEquals_nullEvent() {

		new JsonReplay(new LinkedList<JsonEvent>()).assertEquals(null, 0);

	}

	@Test(expected = AssertionError.class)
	public void assertEquals_wrongEvent() {

		new JsonReplay(Arrays.asList(new NullJsonEvent())).assertEquals(new NextJsonEvent(), 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void assertEquals_nullEvents() {

		new JsonReplay(new LinkedList<JsonEvent>()).assertEquals((JsonEvent[]) null);

	}

	@Test(expected = AssertionError.class)
	public void assertEquals_wrongLength() {

		new JsonReplay(new LinkedList<JsonEvent>()).assertEquals(new NullJsonEvent());

	}

}
