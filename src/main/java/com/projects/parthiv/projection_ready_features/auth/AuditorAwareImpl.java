package com.projects.parthiv.projection_ready_features.auth;

import com.projects.parthiv.projection_ready_features.entities.AuditableEntity;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get security context
        //get authentication
        //get principle user
        //get username
        return Optional.of("Parthiv V Nair");
    }
}
