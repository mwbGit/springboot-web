package com.mwb.mwb;

import com.mwb.web.model.PetPicture;
import com.mwb.web.service.PetPictureService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest1 extends BaseTest{

    @Autowired
    private PetPictureService petPictureService;

    @Test
    public void test1() {
        PetPicture petPicture = new PetPicture();
        petPicture.setPetId(1);
        petPicture.setId(1);
        petPictureService.saveNotNull(petPicture);
        System.out.println(1);
    }

}
