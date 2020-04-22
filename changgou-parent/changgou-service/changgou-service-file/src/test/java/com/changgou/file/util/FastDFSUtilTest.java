package com.changgou.file.util;

import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/2 8:05 下午
 */
@SpringBootTest
public class FastDFSUtilTest {
    private static final String GROUP_NAME = "group1";
    private static final String REMOTE_FILE_NAME = "M00/00/00/CtM3B16FHtyAWejLAAyZSjxO6S4158.jpg";

    /**
     * 获取文件
     */
    @Test
    public void testGetFile() throws IOException, MyException {
        FileInfo fileInfo = FastDFSUtil.getFile(GROUP_NAME, REMOTE_FILE_NAME);
        System.out.println(fileInfo.getSourceIpAddr());
        System.out.println(fileInfo.getFileSize());
    }

    /**
     * 下载文件
     */
    @Test
    public void downloadFile() throws IOException, MyException {
        InputStream is = FastDFSUtil.downloadFile(GROUP_NAME, REMOTE_FILE_NAME);
        OutputStream os = new FileOutputStream("/Users/lishiquan/Downloads/101.jpg");
        byte[] buffer = new byte[1024];
        while (is.read(buffer) != -1) {
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();
    }

    /**
     * 删除文件
     */
    @Test
    public void testDeleteFile() throws IOException, MyException {
        boolean flag = FastDFSUtil.deleteFile(GROUP_NAME, REMOTE_FILE_NAME);
        assert flag;
    }

    /**
     * 获取Storage信息
     */
    @Test
    public void getStorage() throws IOException {
        StorageServer storage = FastDFSUtil.getStorage();
        System.out.println(storage.getStorePathIndex());
        // 获取IP
        System.out.println(storage.getInetSocketAddress().getHostString());
    }

    /**
     * 获取Storage组的IP和端口信息
     */
    @Test
    public void getServiceInfo() throws IOException {
        ServerInfo[] serverInfo = FastDFSUtil.getServerInfo(GROUP_NAME, REMOTE_FILE_NAME);
        for (ServerInfo info : serverInfo) {
            System.out.println(info.getIpAddr());
            System.out.println(info.getPort());
        }
    }

    /**
     * 获取url
     */
    @Test
    public void getTrackerInfo() throws IOException {
        String url = FastDFSUtil.getTrackerInfo();
        System.out.println(url);
    }
}
