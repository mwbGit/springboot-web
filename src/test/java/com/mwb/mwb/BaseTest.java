package com.mwb.mwb;

import com.mwb.web.SpringbootWebApplication;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWebApplication.class)
public class BaseTest {


    @BeforeClass
    public static void beforeClass() {
        System.setProperty("disconf.env","qa");
    }

    @Test
    public void testJsonp() {
        System.out.println(1);
    }

    public static void main(String[] args) {

    }
}
