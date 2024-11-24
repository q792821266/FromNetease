package com.study.hc.foundation.jvm;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoaderTest {
    public static void main(String[] args) throws Exception {
        URL classUrl = new URL("file:E:\\");
        URLClassLoader loader = new URLClassLoader(new URL[]{classUrl});

        while(true) {
            if(loader == null) break;
            Class clazz = loader.loadClass("HelloService");
            System.out.println("HelloService 使用的类加载器：" + clazz.getClassLoader());
            Object newInstance = clazz.newInstance();
            Object value = clazz.getMethod("test").invoke(newInstance);
            System.out.println("调用方法获得返回值为：" + value);
            Thread.sleep(3000L);
            System.out.println();

            newInstance = null;
            loader = null;

        }

        System.gc();
        Thread.sleep(10000L);
    }
}
