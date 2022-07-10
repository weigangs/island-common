package com.lkyl.island.common.service.controller.common;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.VerifyCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 15:18
 */
@RestController
public class CaptchaCodeEndpoint {
    @GetMapping("/verify/code")
    public ResponseEntity<?> code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //创建验证码生成器实例取得生成图片和随机字符串
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        //随机字符串存入session中
        HttpSession session = req.getSession();
        session.setAttribute(CommonCode.CAPTCHA_CODE_KEY, vc.getText());
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            throw new CommonException("CM002");
        }

        //用流传输
        return ResponseEntity.ok(new CommonResult().setCode(CommonCode.SUCCESS).setMsg(CommonCode.SUCCESS_MSG).setData(org.apache.commons.codec.binary.Base64.encodeBase64(os.toByteArray())));

    }
}
