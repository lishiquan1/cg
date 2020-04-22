package com.changgou.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;

/**
 * Demo MySQL数据监听
 *
 * @author lishiquan
 * @date 2020/4/7 4:39 下午
 */
@CanalEventListener
public class CanalDataEventListener {
    /**
     * InsertListenPoint 增加数据监听
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        // getAfterColumnsList(): 获取新增后的数据
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            String key = column.getName();
            String value = column.getValue();
            System.out.println(key + ": " + value);
        }
    }

    /**
     * UpdateListenPoint 增加数据监听
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        // getBeforeColumnsList(): 获取修改前的数据
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            String key = column.getName();
            String value = column.getValue();
            System.out.println(key + ": " + value);
        }
        System.out.println("修改后");
        // getAfterColumnsList(): 获取修改后的数据
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            String key = column.getName();
            String value = column.getValue();
            System.out.println(key + ": " + value);

        }
    }

    /**
     * DeleteListenPoint 增加数据监听
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        // getBeforeColumnsList(): 获取删除前的数据
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            String key = column.getName();
            String value = column.getValue();
            System.out.println(key + ": " + value);
        }
    }

    /**
     * ListenPoint 自定义监听
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @ListenPoint(
            // 指定类型
            eventType ={CanalEntry.EventType.DELETE, CanalEntry.EventType.UPDATE},
            // 指定监听的数据库
            schema = {"changgou_content"},
            // 指定监听的表
            table = {"tb_content"},
            // 指定实例的地址
            destination = "example"
    )
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        // getBeforeColumnsList(): 获取操作前的数据
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            String key = column.getName();
            String value = column.getValue();
            System.out.println(key + ": " + value);
        }
    }

}
