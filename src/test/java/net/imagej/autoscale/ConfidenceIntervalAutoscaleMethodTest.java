/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2009 - 2014 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, and Max Planck
 * Institute of Molecular Cell Biology and Genetics.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.autoscale;

import static org.junit.Assert.assertEquals;
import net.imagej.autoscale.AutoscaleMethod;
import net.imagej.autoscale.AutoscaleService;
import net.imagej.autoscale.ConfidenceIntervalAutoscaleMethod;
import net.imagej.autoscale.DataRange;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.ByteType;

import org.junit.Test;
import org.scijava.Context;

/**
 * Unit tests for {@link ConfidenceIntervalAutoscaleMethod}.
 * 
 * @author Barry DeZonia
 */
public class ConfidenceIntervalAutoscaleMethodTest {

	Context context = new Context(AutoscaleService.class);

	@Test
	public void test() {
		AutoscaleService service = context.getService(AutoscaleService.class);
		AutoscaleMethod method = service.getAutoscaleMethod("Confidence Interval");
		Img<RealType> img = getImg();
		DataRange range = method.getRange(img);
		// System.out.println(range.get1());
		// System.out.println(range.get2());
		assertEquals(2, range.getMin(), 0);
		assertEquals(97, range.getMax(), 0);
	}

	private Img<RealType> getImg() {
		Img<ByteType> img = ArrayImgs.bytes(100);
		byte i = 0;
		for (ByteType b : img)
			b.set(i++);
		return (Img) img;
	}
}
