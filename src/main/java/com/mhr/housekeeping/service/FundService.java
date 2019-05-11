package com.mhr.housekeeping.service;


import com.mhr.housekeeping.entity.vo.FundVO;
import com.mhr.housekeeping.utils.Result;

/**
 * <br/>
 * Created by min on 2019/05/11
 */
public interface FundService {

	Result addFund(FundVO fundVO) throws Exception;

	Result updateFund(FundVO fundVO) throws Exception;
	
	Result findDetailFund(FundVO fundVO) throws Exception;
	
	Result listFund(FundVO fundVO) throws Exception;

    Result listFundPage(FundVO fundVO) throws Exception;
	
	Result countFund(FundVO fundVO) throws Exception;
	
	Result deleteFund(FundVO fundVO) throws Exception;
}