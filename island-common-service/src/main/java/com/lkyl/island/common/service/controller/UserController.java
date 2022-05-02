//package com.lkyl.island.common.service.controller;
//
//import com.lkyl.oceanframework.common.utils.constant.CommonCode;
//import com.lkyl.oceanframework.common.utils.constant.CommonResult;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@RestController
//@RequestMapping("user")
//public class UserController {
//
//    @Resource
//    private TokenStore tokenStore;
//
//    @DeleteMapping("/logout")
//    public ResponseEntity<?> login(HttpServletRequest httpServletRequest){
//        log.info("logout starting ...");
//        String accessToken = httpServletRequest.getHeader("Authorization");
//        if (StringUtils.isNotBlank(accessToken)) {
//            if(accessToken.startsWith("Bearer")){
//                accessToken = accessToken.replace("Bearer", "").trim();
//            }
//            OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
//            if (oAuth2AccessToken != null) {
//                this.tokenStore.removeAccessToken(oAuth2AccessToken);
//                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
//                this.tokenStore.removeRefreshToken(oAuth2RefreshToken);
//                this.tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
//            }
//        }
//
//        return ResponseEntity.ok(new CommonResult().
//                setCode(CommonCode.SUCCESS).
//                setMsg(CommonCode.LOGOUT_MSG).
//                setData(CommonCode.LOGOUT_MSG));
//    }
//}
