package io.choerodon.devops.api.dto;

import java.util.List;

/**
 * Created by younger on 2018/4/10.
 */
public class ApplicationUpdateDTO {

    private Long id;
    private String name;
    private List<Long> userIds;
    private Boolean permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }
}
