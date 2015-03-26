package com.bawi.dozer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;

public class MyDozerTest {

    private final Mapper dozerBeanMapper = new DozerBeanMapper();

    @Test
    public void shouldMap() throws Exception {
        // given
        SourceData sourceData = new SourceData();
        DestinationData destinationData = new DestinationData();
        sourceData.setAge(123);
        sourceData.setSourceName("Timmy");

        // when
        dozerBeanMapper.map(sourceData, destinationData);

        // then
        assertEquals(123, destinationData.getAge());
        assertNull(destinationData.getDestinationName());
    }
}
