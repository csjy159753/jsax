package com.jinhe.modules.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class PermissionItemDTO {
    private List<String> itemIds;
    private String sysPermissionId;
}
