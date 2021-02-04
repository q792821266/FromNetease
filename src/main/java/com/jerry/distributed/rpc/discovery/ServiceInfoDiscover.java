package com.jerry.distributed.rpc.discovery;

import java.util.List;

/**
 * description: ServiceInfoDiscover <br>
 * date: 2021/1/3 23:07 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/323:07
 * @version: 1.0 <br>
 */
public interface ServiceInfoDiscover {
    List<ServiceInfo> getServiceInfos(String name);
}
