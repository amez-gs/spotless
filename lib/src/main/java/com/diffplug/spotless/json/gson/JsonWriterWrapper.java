/*
 * Copyright 2022 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.spotless.json.gson;

import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.diffplug.spotless.JarState;

class JsonWriterWrapper extends GsonWrapperBase {

	private final Class<?> clazz;
	private final Constructor<?> constructor;
	private final Method setIndentMethod;

	JsonWriterWrapper(JarState jarState) {
		this.clazz = loadClass(jarState.getClassLoader(), "com.google.gson.stream.JsonWriter");
		this.constructor = getConstructor(clazz, Writer.class);
		this.setIndentMethod = getMethod(clazz, "setIndent", String.class);
	}

	Object createJsonWriter(Writer writer) {
		return newInstance(constructor, writer);
	}

	void setIndent(Object jsonWriter, String indent) {
		invoke(setIndentMethod, jsonWriter, indent);
	}

	Class<?> getWrappedClass() {
		return clazz;
	}

}
