package com.mhr.housekeeping.dao;

import com.mhr.housekeeping.entity.UserDO;
import com.mhr.housekeeping.entity.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>
 * Created by mhr on 2019/04/07
 */
@Repository
public interface UserMapper {

    Integer addUser(UserVO userVO);

    Integer updateUser(UserVO userVO);

    UserDO findDetailUser(UserVO userVO);

    List<UserDO> listUser(UserVO userVO);

    List<UserDO> listUserPage(UserVO userVO);

    Integer countUser(UserVO userVO);

    Integer deleteUser(UserVO userVO);

    UserVO getUserByUsername(String username);

    UserVO getUserByPhone(String phone);

    UserVO getUserByIdCard(String idCard);

    UserVO getUserByBankCard(String bankCard);

    Integer updateUserByUsername(UserVO userVO);
}
