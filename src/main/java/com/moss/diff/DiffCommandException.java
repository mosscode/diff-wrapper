/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of diff-wrapper.
 *
 * diff-wrapper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * diff-wrapper is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with diff-wrapper; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 */
package com.moss.diff;

public class DiffCommandException extends Exception {

	public DiffCommandException() {
		super();
	}

	public DiffCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiffCommandException(String message) {
		super(message);
	}

	public DiffCommandException(Throwable cause) {
		super(cause);
	}

}
