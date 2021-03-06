package com.mhr.housekeeping.dao;

import com.mhr.housekeeping.entity.RankDO;
import com.mhr.housekeeping.entity.vo.RankVO;
import com.mhr.housekeeping.utils.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>
 * Created by min on 2019/04/26
 */
@Repository
public interface RankMapper {

    Integer addRank(RankVO rankVO);

    Integer updateRank(RankVO rankVO);

    RankDO findDetailRank(RankVO rankVO);

    List<RankDO> listRank(RankVO rankVO);

    List<RankDO> listRankOrderByMoney(RankVO rankVO);

    List<RankDO> listRankPage(RankVO rankVO);

    Integer countRank(RankVO rankVO);

    Integer deleteRank(RankVO rankVO);

    RankDO getRankByName(RankVO rankVO);

    Result findRankByUserId(Integer id);

    RankDO findRankByOrder(Integer employeeId, Integer serviceId);

    RankDO getMostRank(Integer id);
}
