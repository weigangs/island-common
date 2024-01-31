package com.lkyl.island.common.utils;

import com.lkyl.island.common.common.enums.RoleKeyEnum;
import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
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
        return UserContext.getUser().getRoleList().stream()
                .anyMatch(role -> StringUtils.equals(RoleKeyEnum.ADMIN.getKey(), role));
    }
}
