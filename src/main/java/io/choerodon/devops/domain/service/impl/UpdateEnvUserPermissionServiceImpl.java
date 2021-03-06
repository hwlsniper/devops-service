package io.choerodon.devops.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import io.choerodon.core.convertor.ApplicationContextHelper;
import io.choerodon.devops.domain.application.entity.DevopsEnvUserPermissionE;
import io.choerodon.devops.domain.application.repository.DevopsEnvUserPermissionRepository;
import io.choerodon.devops.domain.application.repository.DevopsEnvironmentRepository;
import io.choerodon.devops.domain.service.UpdateUserPermissionService;
import io.choerodon.devops.infra.common.util.TypeUtil;

/**
 * Created by n!Ck
 * Date: 2018/11/21
 * Time: 16:42
 * Description:
 */
public class UpdateEnvUserPermissionServiceImpl extends UpdateUserPermissionService {
    private DevopsEnvironmentRepository devopsEnviromentRepository;
    private DevopsEnvUserPermissionRepository devopsEnvUserPermissionRepository;

    public UpdateEnvUserPermissionServiceImpl() {
        this.devopsEnviromentRepository = ApplicationContextHelper.getSpringFactory()
                .getBean(DevopsEnvironmentRepository.class);
        this.devopsEnvUserPermissionRepository = ApplicationContextHelper.getSpringFactory()
                .getBean(DevopsEnvUserPermissionRepository.class);
    }

    @Override
    public Boolean updateUserPermission(Long projectId, Long id, List<Long> userIds, Integer option) {
        // 更新以前所有有权限的用户
        List<Long> currentUserIds = devopsEnvUserPermissionRepository.listAll(id).stream()
                .map(DevopsEnvUserPermissionE::getIamUserId).collect(Collectors.toList());
        // 待添加的用户
        List<Long> addUserIds = userIds.stream().filter(e -> !currentUserIds.contains(e)).collect(Collectors.toList());
        // 待删除的用户
        List<Long> deleteUserIds = currentUserIds.stream().filter(e -> !userIds.contains(e))
                .collect(Collectors.toList());
        // 更新gitlab权限
        Integer gitlabProjectId = TypeUtil
                .objToInteger(devopsEnviromentRepository.queryById(id).getGitlabEnvProjectId());

        super.updateGitlabUserPermission(gitlabProjectId, addUserIds.stream().map(TypeUtil::objToInteger).collect(
                Collectors.toList()), deleteUserIds.stream().map(TypeUtil::objToInteger).collect(Collectors.toList()));
        devopsEnvUserPermissionRepository.updateEnvUserPermission(id, addUserIds, deleteUserIds);
        return true;
    }
}
