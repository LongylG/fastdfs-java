package com.github.LonglG.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FastDFSClient {

    private TrackerClient trackerClient;

    private TrackerServer trackerServer;

    private StorageClient storageClient;

    private StorageServer storageServer;

    public FastDFSClient(String configFile) throws IOException, MyException {

            ClientGlobal.init(configFile);

            trackerClient = new TrackerClient();

            trackerServer = trackerClient.getConnection();

            storageServer = null;

            storageClient = new StorageClient(trackerServer, storageServer);

    }

    /**
     *
     * @param filePath 上传文件路径
     * @param storeExtName 存储后拓展名
     * @param metaHeads 元数据
     * @return
     * @throws IOException
     * @throws MyException
     */
    public Map<String, String> upload(String filePath, String storeExtName, Map<String, String> metaHeads) throws IOException, MyException {
        NameValuePair[] nvp = new NameValuePair[metaHeads.size()];
        AtomicInteger index = new AtomicInteger();
        metaHeads.forEach((k, v) -> {
            NameValuePair valuePair = new NameValuePair();
            valuePair.setName(k);
            valuePair.setValue(v);
            nvp[index.get()] = valuePair;
            index.getAndIncrement();
        });

        String[] results = storageClient.upload_file(filePath, storeExtName, nvp);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("group", results[0]);
        resultMap.put("path", results[1]);
        return resultMap;
    }

    /**
     *
     * @param group 组名
     * @param storePath 文件系统存储路径
     * @param localPath 本地存储路劲
     * @throws IOException
     * @throws MyException
     */
    public void download(String group, String storePath, String localPath) throws IOException, MyException {
        byte[] fileBytes = storageClient.download_file(group, storePath);

        FileOutputStream fileOutputStream = new FileOutputStream(localPath);

        fileOutputStream.write(fileBytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }


    public static void main(String[] args) throws IOException, MyException {

        FastDFSClient client = new FastDFSClient("/home/liyulong/IdeaProjects/fastdfs-java/conf/client.conf");
//        NameValuePair nvp [] = new NameValuePair[]{
//            new NameValuePair("age", "18"),
//            new NameValuePair("sex", "male")
//        };
//
//        String filedIds[] = storageClient.upload_file(upload_file,"png",nvp);
//        System.out.println(filedIds.length);
//        System.out.println("组名:" + filedIds[0]);
//        System.out.println("路径:" + filedIds[1]);
//         byte[] fileBytes = storageClient.download_file("group1","M00/00/00/rBNSRl1aa1qAbeF6AABl9rFJJfY262.png");

//        FileOutputStream fileOutputStream = new FileOutputStream("/home/liyulong/test.png");
//
//        fileOutputStream.write(fileBytes);
//        fileOutputStream.flush();
//        fileOutputStream.close();
    }
}
