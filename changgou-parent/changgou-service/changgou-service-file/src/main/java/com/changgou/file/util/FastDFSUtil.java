package com.changgou.file.util;

import com.changgou.file.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/2 12:29 下午
 */
public class FastDFSUtil {
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取TrackerServer
     */
    public static TrackerServer getTrackerServer() throws IOException {
        // 1.创建Tracker访问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        // 2.通过TrackerClient访问TrackerServer服务, 获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    /**
     * 获取StorageClient
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer) {
        // 3.通过TrackerServer的链接信息可以获取Storage的链接信息, 创建StorageClient对象存储Storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 文件上传
     * @param file 文件
     */
    public static String[] upload(FastDFSFile file) throws Exception {
        /*
         * 4.通过StorageClient访问Storage, 实现文件上传, 并且获取文件上传后的存储信息
         * upload_file的参数:
         *   1.上传文件的字节数组
         *   2.文件的扩展名  jpg
         *   3.附加参数  比如: 设备名称(HUAWEI P30), 拍摄地址(杭州)
         * uploads[]:
         *   [0]: 文件上传Storage的组名 (group1)
         *   [1]: 文件存储到Storage上的文件名字 (M00/02/44/abc.jpg)
         */
        String[] uploads = getStorageClient(getTrackerServer()).upload_file(file.getContent(), file.getExt(), null);
        return uploads;
    }

    /**
     * 获取文件信息
     * @param groupName 文件的组名 gruop1
     * @param remoteFileName 文件的存储路径名字 M00/00/00/CtM3B16FIdyAKrKlAAyZSjxO6S4349.jpg
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        // 4.获取文件信息
        FileInfo fileInfo = getStorageClient(getTrackerServer()).get_file_info(groupName, remoteFileName);
        return fileInfo;
    }

    /**
     * 下载文件
     * @param groupName 文件的组名 gruop1
     * @param remoteFileName 文件的存储路径名字 M00/00/00/CtM3B16FIdyAKrKlAAyZSjxO6S4349.jpg
     * @return 字符流对象
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws IOException, MyException {
        // 4.下载文件
        byte[] bytes = getStorageClient(getTrackerServer()).download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 删除文件
     * @param groupName 文件的组名 gruop1
     * @param remoteFileName 文件的存储路径名字 M00/00/00/CtM3B16FIdyAKrKlAAyZSjxO6S4349.jpg
     * @return
     */
    public static boolean deleteFile(String groupName, String remoteFileName) throws IOException, MyException {
        // 4.删除文件
        int i = getStorageClient(getTrackerServer()).delete_file(groupName, remoteFileName);
        return i == 1;
    }

    /**
     * 获取Storage信息
     */
    public static StorageServer getStorage() throws IOException {
        return new TrackerClient().getStoreStorage(getTrackerServer());
    }

    /**
     * 获取Storage信息的IP和端口信息
     * @return Storage组的信息
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        return new TrackerClient().getFetchStorages(getTrackerServer(), groupName, remoteFileName);
    }

    /**
     * 获取Tracker信息
     */
    public static String getTrackerInfo() throws IOException {
        int trackerPort = ClientGlobal.getG_tracker_http_port();
        String ip = getTrackerServer().getInetSocketAddress().getHostString();
        String url = "http://" + ip + ":" + trackerPort;
        return url;
    }
}
