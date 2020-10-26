package com.mwb.web.model.vo;

import com.mwb.web.model.PetInfo;
import com.mwb.web.utils.WebConstant;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/10/15
 */
@Data
public class PetInfoVO {

    private long id;

    private String name;

    private String image;


    public static List<PetInfoVO> toVOs(List<PetInfo> petInfos){
        if (CollectionUtils.isEmpty(petInfos)) {
            return Collections.emptyList();
        }
        List<PetInfoVO> vos = new ArrayList<>(petInfos.size());
        for (PetInfo petInfo : petInfos) {
            PetInfoVO vo = new PetInfoVO();
            vo.setId(petInfo.getId());
            vo.setImage(petInfo.getImage());
            vo.setName(petInfo.getName());
            vos.add(vo);
        }

        return vos;
    }


    public String getWaterImage() {
        return WebConstant.getWaterImage(image);
    }
}
