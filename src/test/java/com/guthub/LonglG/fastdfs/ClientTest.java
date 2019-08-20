package com.guthub.LonglG.fastdfs;

import com.github.LonglG.fastdfs.FastDFSClient;

import org.csource.common.MyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class ClientTest {

    private FastDFSClient client;

    private static String group = "group1";

    @Before
    public void init() {
        try {
            client = new FastDFSClient("/home/liyulong/IdeaProjects/fastdfs-java/conf/client.conf");
        } catch (Exception e) {
            System.out.println("初始化fastDFS客户端失败");
            e.printStackTrace();

        }

    }


    @Test
    public void upload() throws IOException, MyException {
        Map<String, String> map = new HashMap<>();
        map.put("type", "rar");
        Map<String, String> result = client.upload("/home/liyulong/保存文件.rar", "zip", map);
        System.out.println("分组："+result.get("group"));
        System.out.println("存储路劲："+result.get("path"));
    }

    @Test
    public void download() throws IOException, MyException {
//        client.download(group, "M00/00/00/rBNSRl1aa1qAbeF6AABl9rFJJfY262.png", "/home/liyulong/test.jpg");
        client.download(group, "M00/00/00/rBNQ4V1bVmmAbQRiBHFJfEZOHi8662.zip", "/home/liyulong/test.rar");
    }

}
