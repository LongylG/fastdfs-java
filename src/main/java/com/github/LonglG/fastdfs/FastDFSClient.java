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


    private Map<String, String> upload(String filePath, String storeExtName, Map<String, String> metaHeads) throws IOException, MyException {
        NameValuePair[] nvp = new NameValuePair[metaHeads.size()];
        int index = 0;
        metaHeads.forEach((k, v) -> {
            nvp[index].setName(k);
            nvp[index].setValue(v);
        });

        String[] results = storageClient.upload_file(filePath, storeExtName, nvp);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("groupId", results[0]);
        resultMap.put("storePath", results[1]);
        return resultMap;
    }

    private void Download(String group, String storePath, String localPath) throws IOException, MyException {
        byte[] fileBytes = storageClient.download_file(group, storePath);

        FileOutputStream fileOutputStream = new FileOutputStream(localPath);

        fileOutputStream.write(fileBytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }


    public static void main(String[] args) throws IOException, MyException {

        FastDFSClient client = new FastDFSClient("/etc/fdfs/client.conf");
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
