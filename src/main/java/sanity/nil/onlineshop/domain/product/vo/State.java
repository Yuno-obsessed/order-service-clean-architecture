package sanity.nil.onlineshop.domain.product.vo;

import java.time.LocalDateTime;

public class State {

    private boolean deleted;
    private LocalDateTime deletedAt;

    public State(boolean deleted) {
        this.deleted = deleted;
        if (deleted) {
            this.deletedAt = LocalDateTime.now();
        }
    }

    public State(boolean deleted, LocalDateTime deletedAt) {
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
