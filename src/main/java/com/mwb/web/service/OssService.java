package com.mwb.web.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.mwb.web.utils.DateTimeUtils;
import com.mwb.web.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Slf4j
@Service("OssService")
public class OssService {

    @Value("${oss.image.domain}")
    private String ossImageDomain;

    @Value("${oss.bucket}")
    private String bucket;

    private boolean mock =true;
    private String DEFAULT_URL = "https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg";

    @Autowired
    private OSSClient ossClient;


    public String uploadImage(MultipartFile file) {
        if (mock) {
            return DEFAULT_URL;
        }

        String url = "";
        if (file == null) {
            return "";
        }
        try {
            String time = DateTimeUtils.formatYYYYMMDDNOSPACE(new Date());
            String fileName = time + "/" + MD5Util.md5(FilenameUtils.getBaseName(file.getOriginalFilename())) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            uploadImageToOss(fileName, file);
            url = ossImageDomain + fileName;
        } catch (Exception ex) {
            log.error("OssService upload file={} err ", file.getOriginalFilename(), ex);
        }
        return url;
    }

    private void uploadImageToOss(String fileName, MultipartFile uploadFile) {
        InputStream fis = null;
        PutObjectResult result = null;
        try {
            fis = uploadFile.getInputStream();
             result = ossClient.putObject(new PutObjectRequest(bucket, fileName, fis));
        } catch (Exception e) {
            log.error("OssService uploadImageToOss err, result={}", result, e);
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

}
