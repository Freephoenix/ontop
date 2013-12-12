package it.unibz.krdb.sql.api;

/*
 * #%L
 * ontop-obdalib-core
 * %%
 * Copyright (C) 2009 - 2013 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * This class represents the literal of string value.
 */
public class StringLiteral extends Literal {
	
	private static final long serialVersionUID = -1560568410005060451L;
	
	/**
	 * The string value.
	 */
	protected String value;

	public StringLiteral(String value) {
		set(value);
	}

	public void set(String value) {
		this.value = value;
	}

	public Object get() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("'%s'", value);
	}
}
