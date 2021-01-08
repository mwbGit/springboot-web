package com.mwb.mwb;

import com.mwb.web.service.OssService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest1 extends BaseTest {


    @Autowired
    private OssService ossService;


    @Test
    public void test1() {

        System.out.println(1);
    }

}
