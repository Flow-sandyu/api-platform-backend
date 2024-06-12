package com.flowsand.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flowsand.project.common.ErrorCode;
import com.flowsand.project.exception.BusinessException;
import com.flowsand.project.mapper.UserInterfaceInfoMapper;
import com.flowsand.project.model.entity.UserInterfaceInfo;
import com.flowsand.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author flowsand
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
 * @createDate 2024-06-10 10:42:25
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {


    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Long userId = userInterfaceInfo.getUserId();
        // 创建时，所有参数必须非空
        if (add) {
            if (interfaceInfoId <= 0 || userId <= 0 || interfaceInfoId == null || userId == null ) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        userInterfaceInfo.setUserId(userId);
        userInterfaceInfo.setInterfaceInfoId(interfaceInfoId);
        UserInterfaceInfo queriedUserInterfaceInfo = this.getOne(new QueryWrapper<>(userInterfaceInfo));

        if (queriedUserInterfaceInfo == null) {
            // 无调用记录，新增调用记录
            userInterfaceInfo.setTotalNum(1);
            userInterfaceInfo.setLeftNum(49);
            userInterfaceInfo.setStatus(1);
            userInterfaceInfo.setCreateTime(new Date());
            userInterfaceInfo.setUpdateTime(new Date());
            boolean result = this.save(userInterfaceInfo);
            if (!result) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
        } else {
            // 存在调用记录，调用次数 +1
            // todo 参考 PartnerMatching 实现事务和锁的逻辑
            UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("interfaceInfoId", interfaceInfoId);
            updateWrapper.eq("userId", userId);
            updateWrapper.gt("leftNum", 0);
            updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
            boolean result = this.update(updateWrapper);
            if (!result) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
        }
        return true;
    }
}




