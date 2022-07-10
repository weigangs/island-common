package com.lkyl.island.common.service.config;

import com.lkyl.oceanframework.log.operator.Operator;
import com.lkyl.oceanframework.log.service.IOperatorGetService;
import com.lkyl.oceanframework.security.security.OceanUserPrincipal;
import com.lkyl.oceanframework.web.util.BusinessContextUtil;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.security.Principal;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 16:02
 */
@Component
public class IslandUserGetService implements IOperatorGetService {
    private static final Operator operator = new Operator("","", false);

    @Override
    public Operator getUser() {
        Principal principal = BusinessContextUtil.getBusinessContext().getUser();
        if (ObjectUtils.isEmpty(principal)) {
            operator.setOperatorName("guest");
        } else {
            operator.setOperatorName(principal.getName());
            if(principal instanceof OAuth2Authentication) {
                if (((OAuth2Authentication) principal).getPrincipal() instanceof OceanUserPrincipal) {
                    operator.setOperatorId(((OceanUserPrincipal) ((OAuth2Authentication) principal).getPrincipal()).getUserId().toString());
                    operator.setLogin(true);
                }
            }else {
                operator.setLogin(false);
                operator.setOperatorId("");
            }
        }
        return operator;
    }
}
