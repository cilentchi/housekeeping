package com.mhr.housekeeping.service.impl;

import com.mhr.housekeeping.dao.UserServiceMapper;
import com.mhr.housekeeping.entity.UserServiceDO;
import com.mhr.housekeeping.entity.vo.UserServiceVO;
import com.mhr.housekeeping.service.UserServiceService;
import com.mhr.housekeeping.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br/>
 * Created by min on 2019/04/28
 */
@Transactional
@Service("userServiceService")
public class UserServiceServiceImpl implements UserServiceService {

    private final static Logger LOG = LoggerFactory.getLogger(UserServiceServiceImpl.class);

    @Resource
    private UserServiceMapper userServiceMapper;

    @Override
    public Result addUserService(UserServiceVO userServiceVO) throws Exception {
        Integer count = userServiceMapper.addUserService(userServiceVO);
        if (count > 0) {
            return Result.getSuccess("关联添加成功");
        }
        return Result.getFailure("关联添加失败");
    }

    @Override
    public Result updateUserService(UserServiceVO userServiceVO) throws Exception {
        Integer count = userServiceMapper.updateUserService(userServiceVO);
        if (count > 0) {
            return Result.getSuccess("更新成功");
        }
        return Result.getFailure("更新失败");
    }

    @Override
    public Result findDetailUserService(UserServiceVO userServiceVO) throws Exception {
        return null;
    }

    @Override
    public List<UserServiceVO> listUserService(UserServiceVO userServiceVO) throws Exception {
        List<UserServiceVO> list = userServiceMapper.listUserService(userServiceVO);
        return list;
    }

    @Override
    public Result listUserServicePage(UserServiceVO userServiceVO) throws Exception {
        return null;
    }

    @Override
    public Result countUserService(UserServiceVO userServiceVO) throws Exception {
        return null;
    }

    @Override
    public Result deleteUserService(UserServiceVO userServiceVO) throws Exception {
        return null;
    }

    @Override
    public Integer deleteUserServiceByRanId(Integer id) {
        return userServiceMapper.deleteUserServiceByRanId(id);
    }

    @Override
    public Integer deleteUserServiceByServiceId(Integer id) {
        return userServiceMapper.deleteUserServiceByServiceId(id);
    }

    @Override
    public Integer updateUserServiceRank(UserServiceVO userServiceVO) {
        return userServiceMapper.updateUserServiceRank(userServiceVO);
    }

    @Override
    public UserServiceVO findUserServiceByOther(UserServiceVO userServiceVO) {
        return userServiceMapper.findUserServiceByOther(userServiceVO);
    }


}