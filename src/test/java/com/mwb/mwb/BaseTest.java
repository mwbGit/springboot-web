package com.mwb.mwb;

import com.mwb.web.SpringbootWebApplication;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 *
 * @auther yangzhihua@kanzhun.com
 * @create 2020/2/24 8:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWebApplication.class)
public class BaseTest {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("disconf.env","qa");
        System.setProperty("disconf.conf_server_host","192.168.1.135:8081");
    }

    @Test
    public void testJsonp() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        String str = "http://s9.cdn.viposs.com/default/1152058695.jpg?imageView2/1/w/270/h/270/q/90|watermark/2/text/MTU3MTEzNDk0MzI=/font/5Lu_5a6L/fontsize/1000/fill/I0ZGMDAwMA==/dissolve/20/gravity/Center/dx/10/dy/10";

        System.out.println(str.substring(0, str.indexOf("?")));
    }
}
