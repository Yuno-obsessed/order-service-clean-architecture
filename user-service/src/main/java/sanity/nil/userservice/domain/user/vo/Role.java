package sanity.nil.userservice.domain.user.vo;

import java.time.LocalDateTime;

public class Role {

    private String roleType;
    private LocalDateTime createdAt;

    public Role(String roleType, LocalDateTime createdAt) {
        this.roleType = roleType;
        this.createdAt = createdAt;
    }

    public String getRoleType() {
        return roleType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
