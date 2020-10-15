package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.PetPicture;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.PetQuery;
import com.mwb.web.model.vo.PetInfoVO;
import com.mwb.web.service.PetCharacterService;
import com.mwb.web.service.PetPictureService;
import com.mwb.web.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetCharacterService petCharacterService;

    @Autowired
    private PetPictureService petPictureService;

    @RequestMapping("/hot/search")
    public ApiResult hot(PetQuery query) {
        PageInfo<PetInfo> pageInfo = petService.simpleSearch(query);
        return ApiResult.success(PetInfoVO.toVOs(pageInfo.getList()), pageInfo.getTotal());
    }

    @RequestMapping("/search")
    public ApiResult search(PetQuery query) {
        PageInfo<PetInfo> pageInfo = petService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping("/characters")
    public ApiResult characters() {
        return ApiResult.success(petCharacterService.getAll());
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("/pet_detail");
        PetInfo petInfo = petService.selectByKey(id);
        if (petInfo == null) {
            return modelAndView;
        }
        modelAndView.addObject("pet", petInfo);
        modelAndView.addObject("petType", petCharacterService.getCharacterNames(id));
        modelAndView.addObject("pictures", petPictureService.getPicturesByPetId(id));
        return modelAndView;
    }

    @RequestMapping("/picture/search")
    public ApiResult pictureSearch(PageQuery query) {
        PageInfo<PetPicture> pageInfo = petPictureService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @RequestMapping("/picture/hot")
    public ApiResult searchHot() {
        PageInfo<PetPicture> pageInfo = petPictureService.search(new PageQuery(20, "level"));
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }


    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/saveOrUpdate")
    public ApiResult saveOrUpdate(PetInfo petInfo, @RequestParam(value = "types", required = false, defaultValue = "") String types) {
        return ApiResult.success(petService.saveOrUpdate(petInfo, types.split(",")));
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/picture/delete")
    public ApiResult delete(@RequestParam("id") long id) {
        petPictureService.delete(id);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/picture/save")
    public ApiResult pictureSave(PetPicture petPicture,  @RequestParam("images") String images) {
        String[] urls = images.split(",");
        petPicture.setAddTime(new Date());
        for (String image : urls) {
            petPicture.setImage(image);
            petPicture.setId(0);
            petPictureService.saveNotNull(petPicture);
        }
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/info")
    public ModelAndView info(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/pet_add");
        if (id != null) {
            PetInfo petInfo = petService.selectByKey(id);
            if (petInfo != null) {
            }
            modelAndView.addObject("pet", petInfo);
        }
        modelAndView.addObject("types", petCharacterService.search(id));
        return modelAndView;
    }
}