package com.lkyl.island.common.common.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.codec.Base64;
import com.lkyl.island.common.common.enums.CommonExceptionEnum;
import com.lkyl.oceanframework.common.utils.exception.BusinessExceptionFactory;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public CommonResult<String> code(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
//ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();



        //创建验证码生成器实例取得生成图片和随机字符串
//        VerifyCode vc = new VerifyCode();
//        BufferedImage image = vc.getImage();
        //随机字符串存入session中
//        HttpSession session = req.getSession();
//        session.setAttribute(CommonCode.CAPTCHA_CODE_KEY, captcha.getCode());
        // 转换流信息写出

        try
        {
//            ImageIO.write(image, "jpg", os);
            //图形验证码写出，可以写出到文件，也可以写出到流
            captcha.write(os);
        }
        catch (Exception e)
        {
            throw BusinessExceptionFactory.getException(CommonExceptionEnum.CAPTCHA_ERR);
        }

        //用流传输
        return CommonResult.ok(Base64.encode(os.toByteArray()));

    }
}
