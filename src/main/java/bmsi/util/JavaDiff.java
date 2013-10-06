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
package bmsi.util;

import java.io.StringWriter;

import com.moss.diff.DiffCommand;
import com.moss.diff.DiffCommandException;

import bmsi.util.Diff.change;

/**
 * Wraps code yanked from http://www.bmsi.com/java/#diff
 * which is supposed to be a port of the GNU diff C code
 */
public class JavaDiff implements DiffCommand {
	
	public String unifiedDiff(String aString, String bString) throws DiffCommandException {
		Object[] a = aString.split("\n");
		Object[] b = bString.split("\n");
		
		Diff diff = new Diff(a, b);
		change delta = diff.diff_2(false);
		
		DiffPrint.Base p = new DiffPrint.UnifiedPrint(a, b);
		StringWriter wtr = new StringWriter();
		p.setOutput(wtr);
		p.print_script(delta);
		
		return wtr.getBuffer().toString();
	}
}
