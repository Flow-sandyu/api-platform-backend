package com.flowsand.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flowsand.project.common.ErrorCode;
import com.flowsand.project.exception.BusinessException;
import com.flowsand.project.mapper.InterfaceInfoMapper;
import com.flowsand.project.model.entity.InterfaceInfo;
import com.flowsand.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author flowsand
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2024-05-08 08:01:22
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        String name = interfaceInfo.getName();
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

}




