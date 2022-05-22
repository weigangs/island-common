package com.lkyl.island.common.service.util;

import com.alibaba.druid.util.StringUtils;
import com.lkyl.island.common.service.enums.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月20日 0:12
 */
public class RoleUtil {
    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for(GrantedAuthority role : roles) {
            if(StringUtils.equals(RoleEnum.ADMIN.getCode(), ((GrantedAuthority) role).getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
