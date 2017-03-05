# Replaying handler for JSON libraries

[![Build Status](https://travis-ci.org/markenwerk/java-utils-json-handler-replay.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-json-handler-replay)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-json-handler-replay/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-json-handler-replay?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/573f1b9ece8d0e0047372236/badge.svg)](https://www.versioneye.com/user/projects/573f1b9ece8d0e0047372236)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-replay/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-replay)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-json-handler-replay.svg)](https://github.com/markenwerk/java-utils-json-handler-replay/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-json-handler-replay/blob/master/LICENSE)

## Overview

This library provides a replaying JSON handlers for other JSON processing libraries.

Consult the [documentation](http://markenwerk.github.io/java-utils-json-handler-replay/javadoc/index.html) and the [usage description](#usage) for further information:

- [Replaying handler](#replaying-handler)

## Maven

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-replay). You can use it with the following coordinates:

```replay
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>utils-json-handler-replay</artifactId>
	<version>1.0.1</version>
</dependency>
```
 
## Usage

### Replaying handler

A [`RecordingJsonHandler`][RecordingJsonHandler] is a [`JsonHandler`][JsonHandler] that creates a [`JsonReplay`][JsonReplay] of the described JSON document. A [`JsonReplay`][JsonReplay] represents a sequence of [`JsonEvents`][JsonEvent] and is able to replay these events to another [`JsonHandler`][JsonHandler].


```java
// a JsonDocument
JsonDocument document = ...

// a JsonHandler
JsonHandler handler = ...

// returns a JsonReplay  
JsonReplay replay = document.handle(new RecordingJsonHandler());

// replay the JsonDocument to the JsonHandler
replay.replay(handler); 
```

A [`RecordingJsonHandler`][RecordingJsonHandler] is useful when testing a component that takes a [`JsonHandler`][JsonHandler], because the generated [`JsonReplay`][JsonReplay] can be used to easily check, if the correct callback methods have been called.

```java
// a JsonReplay
JsonReply replay = ...

// check the replay against an expected sequence of JsonEvents
replay.assertEquals(
	new DocumentBeginJsonEvent(),
	new NullJsonEvent(),
	new DocumentEndJsonEvent(),
);
```


[JsonReplay]: https://markenwerk.github.io/java-utils-json-handler-replay/index.html?net/markenwerk/utils/json/common/handler/replay/JsonReplay.html
[RecordingJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-replay/index.html?net/markenwerk/utils/json/common/handler/replay/RecordingJsonHandler.html

[JsonEvent]: https://markenwerk.github.io/java-utils-json-handler-replay/index.html?net/markenwerk/utils/json/common/handler/replay/events/JsonEvent.html

[JsonHandler]: https://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/JsonHandler.html