package sanity.nil.order.domain.order.vo;

import java.time.LocalDateTime;

public class OrderInfo {

    private boolean deleted;
    private LocalDateTime deletedAt;

    public OrderInfo(boolean deleted, LocalDateTime deletedAt) {
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }

    public OrderInfo() {
        deleted = false;
        deletedAt = null;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
