package com.jerry.distributed.rpc.discovery;

import com.alibaba.fastjson.JSON;
import com.jerry.distributed.rpc.server.register.MyZkSerializer;
import com.jerry.distributed.rpc.utils.PropertiesUtils;
import lombok.SneakyThrows;
import org.I0Itec.zkclient.ZkClient;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * description: ZookeeperServiceInfoDiscover <br>
 * date: 2021/1/3 23:09 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/323:09
 * @version: 1.0 <br>
 */
public class ZookeeperServiceInfoDiscover implements ServiceInfoDiscover {

    ZkClient zkClient;

    private String centerRootPath = "/Rpc-framework";

    public ZookeeperServiceInfoDiscover() {
        String addr = PropertiesUtils.getProperties("zk.address");
        zkClient = new ZkClient(addr);
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @SneakyThrows
    public List<ServiceInfo> getServiceInfos(String name) {
        String servicePath = centerRootPath + "/" + name + "/service";
        List<String> children = zkClient.getChildren(servicePath);
        List<ServiceInfo> resources = new ArrayList<ServiceInfo>();
        for (String child : children){
            String childDe = URLDecoder.decode(child,"UTF-8");
            ServiceInfo serviceInfo = JSON.parseObject(childDe, ServiceInfo.class);
            resources.add(serviceInfo);
        }
        return resources;
    }

}
