/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.sources.ImpulseSource;

public class FirFilterTest {
    @Test
    public void testTaps() {
        List<Double> c = new ArrayList<Double>();
        c.add(0.0);
        c.add(1.0);

        FirFilter ff = new FirFilter(c);
        Assert.assertEquals(2, ff.taps());

        c.add(1.0);
        ff = new FirFilter(c);
        Assert.assertEquals(3, ff.taps());
    }

    @Test
    public void testOut() {
        List<Double> c = new ArrayList<Double>();
        c.add(0.0);
        c.add(1.0);

        FirFilter ff = new FirFilter(c);

        for (int i = 0; i < 441000; i++) {
            Assert.assertEquals(0.0, ff.output(), 0.0001);
        }
    }

    @Test
    public void testImpulseResponse() {
        List<Double> c = new ArrayList<Double>();
        c.add(0.0);
        c.add(0.1);
        c.add(1.0);
        c.add(-0.1);
        c.add(-1.0);

        FirFilter ff = new FirFilter(c);
        ff.setInput(new ImpulseSource());

        for (int i = 0; i < c.size(); i++) {
            Assert.assertEquals(c.get(i), ff.output(), 0.0001);
        }
    }
}
