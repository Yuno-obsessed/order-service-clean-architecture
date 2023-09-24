package sanity.nil.order.domain.common.vo;

import java.time.LocalDateTime;

public class Deleted {

    private boolean deleted;
    private LocalDateTime deletedAt;

    public Deleted(boolean deleted, LocalDateTime deletedAt) {
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }

    public Deleted() {
        deleted = false;
        deletedAt = null;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
