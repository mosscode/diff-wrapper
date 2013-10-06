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
package com.moss.diff.nativediff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.moss.diff.DiffCommandException;

public class NativeDiffCommand {
	
	public String unifiedDiff(String a, String b) throws DiffCommandException {
		try {
			Process p = Runtime.getRuntime().exec(new String[]{
					"diff", 
					"-u", 
					writeToTempFile(a).getAbsolutePath(), 
					writeToTempFile(b).getAbsolutePath()
			});

			String text = read(p.getInputStream());
			String error = read(p.getErrorStream());
			int result = p.waitFor();
			if(result!=1 && result!=0)
				throw new DiffCommandException("Exit status " + result + ": " + error);
			else
				return text;
		} catch (IOException e) {
			throw new DiffCommandException(e);
		} catch (InterruptedException e) {
			throw new DiffCommandException(e);
		}
	}

	private File writeToTempFile(String text) throws IOException {
		File file = File.createTempFile("file", ".txt");
		file.deleteOnExit();
		FileOutputStream out = new FileOutputStream(file);
		out.write(text.getBytes());
		out.close();
		return file;
	}

	private String read(InputStream stream) throws IOException {
		char[] buff = new char[1024];
		Reader r = new InputStreamReader(stream);
		StringBuffer text = new StringBuffer();
		for(int numRead = r.read(buff);numRead!=-1;numRead = r.read(buff)){
			text.append(buff, 0, numRead);
		}
		r.close();
		return text.toString();
	}
}
