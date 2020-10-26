package com.mwb.web.controller;

import cn.wanghaomiao.seimi.spring.common.CrawlerCache;
import cn.wanghaomiao.seimi.struct.CrawlerModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@Controller
public class TestController {

    @Autowired
    private UserInfoService loginUserService;


    @Autowired
    private PetService petService;

    @Autowired
    private com.mwb.web.mapper.MessageMapper messageMapper;

    @Autowired
    private PetPictureService petPictureService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/test")
    public Boolean test(UserInfo user) {
        for (int i=0 ; i < 500 ; i ++) {
            messageMapper.insertCaht(i% 10, i%2 == 0? 1:0, i );
        }
        return true;
    }


    @ResponseBody
    @RequestMapping("/test11")
    public ApiResult test11(UserInfo user) {
        return ApiResult.success(commentService.searchById(Collections.singletonList(1L)));
    }


    @ResponseBody
    @RequestMapping("/test2")
    public ApiResult test2(String name) {
        UserInfo user = loginUserService.getByAccount(name);
        return ApiResult.success(user);
    }

    @ResponseBody
    @RequestMapping("/test3")
    public ApiResult test3(String cname) {
        CrawlerModel model = CrawlerCache.getCrawlerModel("basic");
        model.queueInfo();
        return ApiResult.success(1);
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("apiKey", 1);
        json.put("petID", 1);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        String s = restTemplate.postForEntity("http://mengweibo.com/pet/picture/hot", formEntity, String.class).getBody();
        System.out.println(s);
    }


    private static JSONArray m() {
        String json = "[{\"petID\":1,\"name\":\"埃及猫\",\"engName\":\"Egyptian Mau\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img99961410946559.jpg\"},{\"petID\":2,\"name\":\"伯曼猫\",\"engName\":\"Birman\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img43731405667543.jpg\"},{\"petID\":3,\"name\":\"卡尔特猫\",\"engName\":\"Chartreux\",\"price\":\"10000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img49891406109364.jpg\"},{\"petID\":4,\"name\":\"金吉拉\",\"engName\":\"Chinchilla\",\"price\":\"2500-5000元\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img12571406109486.jpg\"},{\"petID\":5,\"name\":\"俄罗斯蓝猫\",\"engName\":\"Russian Blue\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img46251406108822.jpg\"},{\"petID\":6,\"name\":\"斯芬克斯猫\",\"engName\":\"Canadian Hairless\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img38291406017823.jpg\"},{\"petID\":7,\"name\":\"苏格兰折耳猫\",\"engName\":\"Scottish Fold\",\"price\":\"8000--20000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick44371435642982.png\"},{\"petID\":8,\"name\":\"加拿大无毛猫\",\"engName\":\"Sphynx\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img66841406110188.jpg\"},{\"petID\":9,\"name\":\"暹罗猫\",\"engName\":\"Siamese\",\"price\":\"500-1500\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img78961410942863.jpg\"},{\"petID\":10,\"name\":\"波斯猫\",\"engName\":\"Persian\",\"price\":\"4000--20000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick56891435629771.png\"},{\"petID\":11,\"name\":\"美国短毛猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img79491416973896.jpg\"},{\"petID\":12,\"name\":\"异国短毛猫\",\"engName\":\"Exotic Shorthair\",\"price\":\"3500--12000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick48961435629470.png\"},{\"petID\":13,\"name\":\"中国狸花猫\",\"engName\":\"Dragon Li\",\"price\":\"2000--5000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img68361406281613.jpg\"},{\"petID\":14,\"name\":\"英国短毛猫\",\"engName\":\"British Shorthair\",\"price\":\"5000--10000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img63891406280994.jpg\"},{\"petID\":15,\"name\":\"布偶猫\",\"engName\":\"Ragdoll\",\"price\":\"宠物级:8000-18000 赛级: 18000-25000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img91405652854.jpg\"},{\"petID\":16,\"name\":\"奥西猫\",\"engName\":\"Ocicat\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img74051410945489.jpg\"},{\"petID\":17,\"name\":\"缅因猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick18281508393528.png\"},{\"petID\":18,\"name\":\"曼基康猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick68661508384319.jpg\"},{\"petID\":19,\"name\":\"孟买猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/20090529141017.jpg\"},{\"petID\":20,\"name\":\"孟加拉豹猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick67191497233207.jpg\"},{\"petID\":21,\"name\":\"中华田园猫\",\"engName\":\"\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/imagick12791473240420.jpg\"},{\"petID\":22,\"name\":\"重点色短毛猫\",\"engName\":\"Colorpoint Shorthair\",\"price\":\"10000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img72221406282181.jpg\"},{\"petID\":23,\"name\":\"东奇尼猫\",\"engName\":\"Tonkinese\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img95801405672398.jpg\"},{\"petID\":24,\"name\":\"内华达猫\",\"engName\":\"Nebelung\",\"price\":\"8000--20000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img21151410859877.jpg\"},{\"petID\":25,\"name\":\"威尔士猫\",\"engName\":\"Cymric\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img87301406196811.jpg\"},{\"petID\":26,\"name\":\"欧洲缅甸猫\",\"engName\":\"European Burmese\",\"price\":\"5000--10000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img93211405935300.jpg\"},{\"petID\":27,\"name\":\"柯尼斯卷毛猫\",\"engName\":\"Cornish Rex\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img4921406022121.jpg\"},{\"petID\":28,\"name\":\"索马里猫\",\"engName\":\"Somali \",\"price\":\"10000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img2951406018758.jpg\"},{\"petID\":29,\"name\":\"哈瓦那猫\",\"engName\":\"Havana \",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img98071410947762.jpg\"},{\"petID\":30,\"name\":\"科拉特猫\",\"engName\":\"Korat\",\"price\":\"10000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img58431406016800.jpg\"},{\"petID\":31,\"name\":\"东方猫\",\"engName\":\"Oriental Shorthair\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img14161406022511.jpg\"},{\"petID\":32,\"name\":\"巴厘猫\",\"engName\":\"Balinese\",\"price\":\"2000-5000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img25631405668906.jpg\"},{\"petID\":33,\"name\":\"夏特尔猫 \",\"engName\":\"Chartreux\",\"price\":\"8000--20000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img10501406280356.jpg\"},{\"petID\":34,\"name\":\"塞尔凯克卷毛猫\",\"engName\":\"Selkirk Rex\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img72201406106623.jpg\"},{\"petID\":35,\"name\":\"德文卷毛猫\",\"engName\":\"Devon Rex\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img81481405673729.jpg\"},{\"petID\":36,\"name\":\"土耳其安哥拉猫\",\"engName\":\"Turkish Angora\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img43261406194638.jpg\"},{\"petID\":37,\"name\":\"阿比西尼亚猫\",\"engName\":\"Abyssinian\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img33571405663401.jpg\"},{\"petID\":38,\"name\":\"日本短尾猫\",\"engName\":\"Japanese Bobtail\",\"price\":\"8000--15000\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img70661405933355.jpg\"},{\"petID\":39,\"name\":\"沙特尔猫\",\"engName\":\"Chartreux\",\"price\":\"10000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img13171406105650.jpg\"},{\"petID\":40,\"name\":\"波米拉猫\",\"engName\":\"Burmilla\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img43941405671164.jpg\"},{\"petID\":41,\"name\":\"新加坡猫\",\"engName\":\"Singapura、Kucinta\",\"price\":\"\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img44591410944462.jpg\"},{\"petID\":42,\"name\":\"土耳其梵猫\",\"engName\":\"TurkishVan\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img21021406195580.jpg\"},{\"petID\":43,\"name\":\"喜马拉雅猫\",\"engName\":\"Himalayan、Colourpoint Persians\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img81471406191314.jpg\"},{\"petID\":44,\"name\":\"西伯利亚森林猫\",\"engName\":\"Siberian、SibirskayaKoshka\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img89081406193390.jpg\"},{\"petID\":45,\"name\":\"山东狮子猫\",\"engName\":\"Persian\",\"price\":\"20000以上\",\"coverURL\":\"http://img.boqiicdn.com/Data/BK/P/img92251406107429.jpg\"}]";

        return JSONArray.parseArray(json);
    }

}
