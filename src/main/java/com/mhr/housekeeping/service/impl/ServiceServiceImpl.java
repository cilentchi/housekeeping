package com.mhr.housekeeping.service.impl;

import com.mhr.housekeeping.dao.ServiceMapper;
import com.mhr.housekeeping.entity.ServiceDO;
import com.mhr.housekeeping.entity.UserServiceDO;
import com.mhr.housekeeping.entity.vo.ServiceVO;
import com.mhr.housekeeping.entity.vo.UserServiceVO;
import com.mhr.housekeeping.service.ServiceService;
import com.mhr.housekeeping.service.UserServiceService;
import com.mhr.housekeeping.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br/>
 * Created by min on 2019/04/28
 */
@Service("serviceService")
public class ServiceServiceImpl implements ServiceService {

    private final static Logger LOG = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private UserServiceService userServiceService;

    @Override
    public Result addService(ServiceVO serviceVO) throws Exception {
        ServiceVO vo = serviceMapper.findServiceByName(serviceVO);
        if (vo != null) {
            return Result.getFailure("该服务名称已存在");
        } else {
            Integer count = serviceMapper.addService(serviceVO);
            if (count > 0) {
                return Result.getSuccess("操作成功");
            }
            return Result.getFailure("操作失败");
        }
    }

    @Override
    public Result updateService(ServiceVO serviceVO) throws Exception {
        Integer count = serviceMapper.updateService(serviceVO);
        if (count > 0) {
            return Result.getSuccess("操作成功");
        }
        return Result.getFailure("操作失败");
    }

    @Override
    public Result findDetailService(ServiceVO serviceVO) throws Exception {
        return null;
    }

    @Override
    public Result listService(ServiceVO serviceVO) throws Exception {
        //搜索条件不为空的时候，查询的如果是大类别，需要将他的小类别也返回,反之一样
        if (!serviceVO.getName().isEmpty()) {
            List<ServiceDO> listService = serviceMapper.listServiceByName(serviceVO);
            return new Result<>(listService);
        } else {
            List<ServiceDO> listService = serviceMapper.listService(serviceVO);
            return new Result<>(listService);
        }
    }

    @Override
    public Result listServicePage(ServiceVO serviceVO) throws Exception {
        return null;
    }

    @Override
    public Result countService(ServiceVO serviceVO) throws Exception {
        return null;
    }

    /**
     * 删除服务的父类   删除的同时还需要删除数据库中parent是此父类的记录
     * 还需要删除关联表里面有关的数据
     *
     * @param serviceVO
     * @return
     * @throws Exception
     */
    @Override
    public Result deleteParService(ServiceVO serviceVO) throws Exception {
//        select id from service where id=30 or parent=30
        //得到父类id以及父类下面子类的id
        List<Integer> ids = serviceMapper.getIds(serviceVO.getId());
        for (int i = 0; i < ids.size(); i++) {
            UserServiceVO userServiceVO = new UserServiceVO();
            userServiceVO.setServiceId(ids.get(i));
            List<UserServiceDO> list = userServiceService.listUserService(userServiceVO);
            if (list.size() > 0) {
                userServiceService.deleteUserServiceByServiceId(ids.get(i));
            }
        }
        Integer count = serviceMapper.deleteParService(serviceVO);
        if (count > 0) {
            return Result.getSuccess("删除成功");
        }
        return Result.getFailure("删除失败");
    }

    @Override
    public Result deleteService(ServiceVO serviceVO) throws Exception {
        //根据id查询关联表中是否有数据，有的话就删除
        UserServiceVO userServiceVO = new UserServiceVO();
        userServiceVO.setServiceId(serviceVO.getId());
        List<UserServiceDO> list = userServiceService.listUserService(userServiceVO);
        if (list.size() > 0) {
            Integer r = userServiceService.deleteUserServiceByServiceId(serviceVO.getId());
            if (r > 0) {
                Integer count = serviceMapper.deleteService(serviceVO);
                if (count > 0) {
                    return Result.getSuccess("删除成功");
                }
                return Result.getFailure("删除失败");
            }
            return Result.getFailure("删除失败");
        }
        Integer count = serviceMapper.deleteService(serviceVO);
        if (count > 0) {
            return Result.getSuccess("删除成功");
        }
        return Result.getFailure("删除失败");
    }


}