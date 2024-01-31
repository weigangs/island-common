package com.lkyl.island.common.login.service;

import com.lkyl.island.common.api.request.LoginDTO;
import com.lkyl.island.common.api.response.SysUserVO;

import javax.servlet.http.HttpServletRequest;

public interface ILoginService {

    SysUserVO login(LoginDTO loginDTO, HttpServletRequest request);

    SysUserVO getInfo();

    void logout(HttpServletRequest request);
}
