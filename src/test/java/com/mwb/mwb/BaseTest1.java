package com.mwb.mwb;

import com.mwb.web.model.PetPicture;
import com.mwb.web.service.OssService;
import com.mwb.web.service.PetPictureService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseTest1 extends BaseTest{

    @Autowired
    private PetPictureService petPictureService;

    @Autowired
    private OssService ossService;


    @Test
    public void test1() {
        List<PetPicture> all =  petPictureService.selectAll();
        for (PetPicture petPicture1 : all) {
            if (!petPicture1.getImage().contains("xwz")) {
//               String url = ossService.uploadImageToOss(petPicture1.getImage());

//                System.out.println("img=" + petPicture1.getImage() + " url=" + url);
//                petPicture1.setImage(url);
//                petPictureService.updateNotNull(petPicture1);
            }
        }
        System.out.println(1);
    }

}
