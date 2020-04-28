package es.upm.miw.apaw_practice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {
    public static final String SYSTEM = "/system";

    public static final String APP_INFO = "/app-info";
    public static final String VERSION_BADGE = "/version-badge";

    @Value("${miw.application.name}")
    private String applicationName;
    @Value("${miw.build.version}")
    private String buildVersion;
    @Value("${miw.build.timestamp}")
    private String buildTimestamp;
    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() { // http://localhost:8080/system/badge
        return new Badge().generateBadge("v" + buildVersion).getBytes();
    }

    @GetMapping(value = APP_INFO)
    public AppInfoDto applicationInfo() {
        return new AppInfoDto(this.applicationName, this.buildVersion, this.buildTimestamp, this.profile);
    }
}