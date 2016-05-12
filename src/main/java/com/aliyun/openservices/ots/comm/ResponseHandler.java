/**
 * Copyright (C) Alibaba Cloud Computing
 * All rights reserved.
 * 
 * 版权所有 （C）阿里云计算有限公司
 */

package com.aliyun.openservices.ots.comm;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.ServiceException;

/**
 * 对返回结果进行处理。
 *
 */
public interface ResponseHandler {

    /**
     * 处理返回的结果
     */
    public void handle(ResponseMessage responseData)
            throws ServiceException, ClientException;
}