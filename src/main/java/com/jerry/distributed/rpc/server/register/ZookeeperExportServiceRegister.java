package com.jerry.distributed.rpc.server.register;

import com.alibaba.fastjson.JSON;
import com.jerry.distributed.rpc.discovery.ServiceInfo;
import com.jerry.distributed.rpc.utils.PropertiesUtils;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;

/**
 * description: ZookeeperExportServiceRegister <br>
 *     使用zookeeper的方式获取远程服务信息类
 * date: 2021/1/6 22:51 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/622:51
 * @version: 1.0 <br>
 */
public class ZookeeperExportServiceRegister extends DefaultServiceRegister implements ServiceRegister {

    private ZkClient zkClient;

    private String centerRootPath = "/Rpc-frameWork";

    public ZookeeperExportServiceRegister() {
        String addr = PropertiesUtils.getProperties("zk.address");
        zkClient = new ZkClient(addr);
        zkClient.setZkSerializer(new MyZkSerializer());

    }

    @Override
    public void register(ServiceObject service, String protocol, int port) throws Exception {
        super.register(service, protocol, port);
        ServiceInfo serviceInfo = new ServiceInfo();

        String host = InetAddress.getLocalHost().getHostAddress();
        String address = host + ":" + port;
        serviceInfo.setAddress(address);
        serviceInfo.setName(service.getInterf().getName());
        serviceInfo.setProtocol(protocol);
        this.exportService(serviceInfo);
    }

    private void exportService(ServiceInfo serviceInfo) {
        String serviceName = serviceInfo.getName();
        String uri = JSON.toJSONString(serviceInfo);
        try {
            uri = URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String servicePath = centerRootPath + "/" + serviceName + "/service";
        if(!zkClient.exists(servicePath)){
            zkClient.createPersistent(servicePath, true);
        }

        String uriPath = servicePath + "/" + uri;
        if (zkClient.exists(uriPath)) {
            zkClient.delete(uriPath);
        }
        //创建临时节点
       zkClient.createEphemeral(uriPath);

    }

}
