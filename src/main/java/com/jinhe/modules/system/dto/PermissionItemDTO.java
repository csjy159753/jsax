package com.jinhe.modules.system.dto;



import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class PermissionItemDTO implements Serializable {
    private List<String> itemIds;
    private String sysPermissionId;

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public String getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(String sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }
}
