package com.lkyl.island.common.ps.service;

import com.lkyl.oceanframework.security.security.UserPrincipal;

public interface UserService {

    UserPrincipal login(String username);
}
