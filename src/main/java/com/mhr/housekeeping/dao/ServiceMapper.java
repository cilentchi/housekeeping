package com.mhr.housekeeping.dao;

import com.mhr.housekeeping.entity.ServiceDO;
import com.mhr.housekeeping.entity.vo.ServiceVO;
import com.mhr.housekeeping.entity.vo.ServiceVO2;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by min on 2019/04/28
 */
@Repository
public interface ServiceMapper {

    Integer addService(ServiceVO serviceVO);

    Integer updateService(ServiceVO serviceVO);

    ServiceDO findDetailService(ServiceVO serviceVO);

    List<ServiceDO> listService(ServiceVO serviceVO);

    List<ServiceDO> listServicePage(ServiceVO serviceVO);

    Integer countService(ServiceVO serviceVO);

    Integer deleteService(ServiceVO serviceVO);

    ServiceVO findServiceByName(ServiceVO serviceVO);

    Integer deleteParService(ServiceVO serviceVO);
}
