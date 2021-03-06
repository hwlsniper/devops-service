package io.choerodon.devops.infra.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import io.choerodon.devops.infra.dataobject.ApplicationLatestVersionDO;
import io.choerodon.devops.infra.dataobject.ApplicationVersionDO;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * Created by younger on 2018/3/28.
 */
public interface ApplicationVersionMapper extends BaseMapper<ApplicationVersionDO> {

    List<ApplicationVersionDO> listApplicationVersion(
            @Param("projectId") Long projectId,
            @Param("appId") Long appId,
            @Param("searchParam") Map<String, Object> searchParam,
            @Param("param") String param);

    List<ApplicationLatestVersionDO> listAppLatestVersion(@Param("projectId") Long projectId,
                                                          @Param("projectIds") List<Long> projectIds);

    List<ApplicationVersionDO> listByAppIdAndEnvId(@Param("projectId") Long projectId,
                                                   @Param("appId") Long appId,
                                                   @Param("envId") Long envId);

    String queryValue(@Param("versionId") Long versionId);

    List<ApplicationVersionDO> selectByAppId(@Param("appId") Long appId,
                                             @Param("isPublish") Boolean isPublish);

    List<ApplicationVersionDO> selectDeployedByAppId(@Param("projectId") Long projectId,
                                                     @Param("appId") Long appId);

    List<ApplicationVersionDO> listApplicationVersionInApp(
            @Param("projectId") Long projectId,
            @Param("appId") Long appId,
            @Param("searchParam") Map<String, Object> searchParam,
            @Param("param") String param);

    List<ApplicationVersionDO> getAllPublishedVersion(@Param("applicationId") Long applicationId);

    List<Long> selectVersionsByAppId(@Param("applicationId") Long applicationId);

    List<ApplicationVersionDO> selectUpgradeVersions(@Param("appVersionId") Long appVersionId);

    Integer checkProIdAndVerId(@Param("projectId") Long projectId, @Param("appVersionId") Long appVersionId);

    ApplicationVersionDO getLatestVersion(@Param("appId") Long appId);

}
