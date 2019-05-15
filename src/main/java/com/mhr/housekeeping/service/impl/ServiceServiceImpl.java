package com.mhr.housekeeping.service.impl;

import com.mhr.housekeeping.dao.OrdersMapper;
import com.mhr.housekeeping.dao.ServiceMapper;
import com.mhr.housekeeping.dao.UserMapper;
import com.mhr.housekeeping.entity.ServiceDO;
import com.mhr.housekeeping.entity.UserDO;
import com.mhr.housekeeping.entity.UserServiceDO;
import com.mhr.housekeeping.entity.vo.OrdersVO;
import com.mhr.housekeeping.entity.vo.ServiceVO;
import com.mhr.housekeeping.entity.vo.UserServiceVO;
import com.mhr.housekeeping.entity.vo.UserVO;
import com.mhr.housekeeping.service.ServiceService;
import com.mhr.housekeeping.service.UserServiceService;
import com.mhr.housekeeping.utils.DataUtils;
import com.mhr.housekeeping.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * Created by min on 2019/04/28
 */
@Service("serviceService")
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final static Logger LOG = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Resource
    private ServiceMapper serviceMapper;
    @Resource
    private UserServiceService userServiceService;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;
    ;

    @Override
    public Result addService(ServiceVO serviceVO) throws Exception {
        ServiceVO vo = serviceMapper.findServiceByName(serviceVO);
        if (vo != null) {
            return Result.getFailure("该服务名称已存在,操作失败");
        } else {
            Integer count = serviceMapper.addService(serviceVO);
            if (count > 0) {
                return Result.getSuccess("操作成功");
            } else return Result.getFailure("操作失败");
        }
    }

    @Override
    public Result updateService(ServiceVO serviceVO) throws Exception {
        //编辑服务名称的时候，名称不能重复
        ServiceVO serviceByName = serviceMapper.findServiceByName(serviceVO);
        if (serviceByName != null && !serviceByName.getId().equals(serviceVO.getId())) {
            return Result.getFailure("该服务名称已存在");
        } else {
            Integer count = serviceMapper.updateService(serviceVO);
            if (count > 0) {
                return Result.getSuccess("操作成功");
            }
            return Result.getFailure("操作失败");
        }
    }

    @Override
    public ServiceDO findDetailService(ServiceVO serviceVO) throws Exception {
        return serviceMapper.findDetailService(serviceVO);

    }

    @Override
    public Result listService(ServiceVO serviceVO) throws Exception {
        //搜索条件不为空的时候，查询的如果是大类别，需要将他的小类别也返回,反之一样
        if (serviceVO != null && !DataUtils.isEmpty(serviceVO.getName())) {
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
     * 查询此服务或者此服务的子类是否已经生成了订单，如果有的话，则不可删除
     * 如果没有生成订单的话 -  删除serviceUser关联表里面有关的数据---删除service表里的数据
     *
     * @param serviceVO
     * @return
     * @throws Exception
     */
    @Override
    public Result deleteParService(ServiceVO serviceVO) throws Exception {
        Integer tmp = 0;
//        select id from service where id=30 or parent=30
        //得到父类id以及父类下面子类的id
        List<ServiceVO> vos = serviceMapper.getIds(serviceVO.getId());
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < vos.size(); i++) {
            ids.add(vos.get(i).getId());
        }
        for (int i = 0; i < ids.size(); i++) {
            OrdersVO ordersVO = new OrdersVO();
            ordersVO.setServiceId(ids.get(i));
            List<OrdersVO> voList = ordersMapper.listOrders(ordersVO);
            if (voList != null && voList.size() > 0) {
                tmp++;
            }
        }
        if (tmp == 0) {//这一大项服务没有被下过单
            for (int i = 0; i < ids.size(); i++) {
                UserServiceVO userServiceVO = new UserServiceVO();
                userServiceVO.setServiceId(ids.get(i));
                List<UserServiceVO> list = userServiceService.listUserService(userServiceVO);
                if (list != null && list.size() > 0) {//人员服务等级表里有这项服务  删除
                    userServiceService.deleteUserServiceByServiceId(ids.get(i));
                }
            }
            Integer count = serviceMapper.deleteParService(serviceVO);
            if (count > 0) {
                return Result.getSuccess("删除成功");
            } else return Result.getFailure("删除失败");
        } else return Result.getFailure("此服务的子类别已经被下订单，不可删除");
    }

    /**
     * 删除子类别，直接根据id
     *
     * @param serviceVO
     * @return
     * @throws Exception
     */
    @Override
    public Result deleteService(ServiceVO serviceVO) throws Exception {
        //根据id查询是否有相关服务的订单，如果有，则不可删除，如果没有，则可以进行删除操作
        OrdersVO ordersVO = new OrdersVO();
        ordersVO.setServiceId(serviceVO.getId());
        List<OrdersVO> voList = ordersMapper.listOrders(ordersVO);
        UserServiceVO userServiceVO = new UserServiceVO();
        userServiceVO.setServiceId(serviceVO.getId());
        List<UserServiceVO> list = userServiceService.listUserService(userServiceVO);
        //如果订单中出现了此项服务，则提示不可删除
        if (voList != null && voList.size() > 0) {
            return Result.getFailure("此服务已经被下订单，不可删除");
        } else {
            if (list != null && list.size() > 0) {
                userServiceService.deleteUserServiceByServiceId(serviceVO.getId());
            }
            Integer count = serviceMapper.deleteService(serviceVO);
            if (count > 0) {
                return Result.getSuccess("删除成功");
            }
            return Result.getFailure("删除失败");
        }
    }

    @Override
    public Result findServiceByUserId(Integer id) {
        List<ServiceDO> serviceByUserId = serviceMapper.findServiceByUserId(id);
        return new Result<>(serviceByUserId);
    }

    @Override
    public ServiceDO findServiceByOrder(Integer orderId) {
        return serviceMapper.findServiceByOrder(orderId);
    }

    /**
     * 查询订单与服务，并且统计服务
     *
     * @param serviceId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ServiceVO> serviceStatic(Integer serviceId, Integer startTime, Integer endTime) {
        List<ServiceVO> vos = serviceMapper.serviceStatic(serviceId, startTime, endTime);
        if (vos != null && vos.size() > 0) {
            vos.forEach(serviceVO -> {
                //根据人员id查询账号
                UserDO employee = userMapper.findDetailUser(new UserVO(serviceVO.getEmployeeId()));
                UserDO employer = userMapper.findDetailUser(new UserVO(serviceVO.getEmployerId()));
                serviceVO.setEmployeeUsername(employee.getUsername());
                serviceVO.setEmployerUsername(employer.getUsername());
                serviceVO.setEmployerName(employer.getName());
                serviceVO.setEmployeeName(employee.getName());
            });
            return vos;
        }
        return null;
    }

    /**
     * 查询雇主所有下单的服务
     *
     * @param employerId
     * @return
     */
    @Override
    public List<ServiceDO> getServiceByUserOrder(Integer employerId) {
        return serviceMapper.getServiceByUserOrder(employerId);
    }

    @Override
    public List<ServiceVO> serviceStaticByEid(Integer serviceId, Integer startTime, Integer endTime, Integer eid, Integer role) {
        List<ServiceVO> vos = new ArrayList<>();
        if (role == 200) {
            vos = serviceMapper.serviceStaticByEid(serviceId, startTime, endTime, eid);
        } else if (role == 300) {
            vos = serviceMapper.serviceStaticByEid2(serviceId, startTime, endTime, eid);
        }
        if (vos != null && vos.size() > 0) {
            vos.forEach(serviceVO -> {
                //根据人员id查询账号
                UserDO employee = userMapper.findDetailUser(new UserVO(serviceVO.getEmployeeId()));
                UserDO employer = userMapper.findDetailUser(new UserVO(serviceVO.getEmployerId()));
                serviceVO.setEmployeeUsername(employee.getUsername());
                serviceVO.setEmployerUsername(employer.getUsername());
                serviceVO.setEmployerName(employer.getName());
                serviceVO.setEmployeeName(employee.getName());
            });
            return vos;
        }
        return null;
    }


}