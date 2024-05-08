package com.flowsand.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flowsand.project.model.entity.InterfaceInfo;

/**
* @author flowsand
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-05-08 08:01:22
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
