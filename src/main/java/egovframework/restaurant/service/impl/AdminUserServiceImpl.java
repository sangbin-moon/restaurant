package egovframework.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.restaurant.service.AdminUserService;
import egovframework.restaurant.vo.AdminUserVO;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUserVO login(AdminUserVO adminUserVO) {

        return adminUserMapper.login(adminUserVO);

    }

}