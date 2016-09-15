package br.com.rodrigo.monteiro.api.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by monteiro on 9/14/16.
 */
public class PIT_IDTest {

    @Test
    public void getByOrdinal() throws Exception {
        Assert.assertEquals(PIT_ID.PIT_1, PIT_ID.getByOrdinal(0));
        Assert.assertEquals(PIT_ID.PIT_2, PIT_ID.getByOrdinal(1));
        Assert.assertEquals(PIT_ID.PIT_3, PIT_ID.getByOrdinal(2));
        Assert.assertEquals(PIT_ID.PIT_4, PIT_ID.getByOrdinal(3));
        Assert.assertEquals(PIT_ID.PIT_5, PIT_ID.getByOrdinal(4));
        Assert.assertEquals(PIT_ID.PIT_6, PIT_ID.getByOrdinal(5));
    }
}