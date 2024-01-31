package com.lkyl.island.common.user.service;

public interface ISysUserDao {

    int updateUserPasswordByUserName(String userName, String newPassword);

}
