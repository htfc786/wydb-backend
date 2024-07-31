package com.htfc786.wydb;

import com.htfc786.wydb.service.WyContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class WydbApplicationTest {

    @Resource
    private WyContentService wyContentService;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1() {
        wyContentService.moveBack(9L, 2, 1);
    }
}
