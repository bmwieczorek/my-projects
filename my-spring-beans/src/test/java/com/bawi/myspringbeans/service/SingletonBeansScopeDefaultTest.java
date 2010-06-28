package com.bawi.myspringbeans.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/my-service-context.xml", "/my-dao-singleton-bean-scope-context.xml" })
public class SingletonBeansScopeDefaultTest {

    @Autowired
    private MyManagerService managerService;

    @Autowired
    private MyWorkerService workerService;

    @Test
    public void shouldReturnTheSameCachedDependency() {
        Assert.assertNotNull(managerService);

        Assert.assertNotNull(workerService);

        Assert.assertEquals(managerService.getPersonDao(), workerService.getPersonDao());
    }
}
