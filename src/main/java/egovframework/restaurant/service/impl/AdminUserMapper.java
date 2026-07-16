package egovframework.restaurant.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.restaurant.vo.AdminUserVO;

@EgovMapper("adminUserMapper")
public interface AdminUserMapper {

    AdminUserVO login(AdminUserVO adminUserVO);

}