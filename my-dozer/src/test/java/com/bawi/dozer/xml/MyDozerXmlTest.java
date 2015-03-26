package com.bawi.dozer.xml;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.bawi.dozer.DestinationData;
import com.bawi.dozer.SourceData;
import com.bawi.dozer.SourceData.Child;

public class MyDozerXmlTest {

    private final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Test
    public void shouldMap() throws Exception {
        // given
        dozerBeanMapper.setMappingFiles(Arrays.asList("dozer-mappings.xml"));
        SourceData sourceData = new SourceData();
        DestinationData destinationData = new DestinationData();
        sourceData.setAge(123);
        sourceData.setSourceName("Timmy");
        sourceData.setChildren(Arrays.asList(new Child("son"), new Child("daughter")));
        SourceData.Status status = new SourceData.Status();
        //status.setMarried(Boolean.TRUE);
        status.setMarried(Boolean.TRUE);
        sourceData.setStatus(status);

        // when
        dozerBeanMapper.map(sourceData, destinationData);

        // then
        assertEquals(123, destinationData.getAge());
        assertEquals("Timmy", destinationData.getDestinationName());
        assertEquals("son,daughter,", destinationData.getChildren().getNames());
        assertEquals(Boolean.TRUE, destinationData.getStatus().isMarried());
    }
}
