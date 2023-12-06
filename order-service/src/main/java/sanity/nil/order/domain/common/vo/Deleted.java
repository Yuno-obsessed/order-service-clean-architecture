package sanity.nil.order.domain.common.vo;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deleted deleted1 = (Deleted) o;
        return deleted == deleted1.deleted &&
                Objects.equals(deletedAt, deleted1.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleted, deletedAt);
    }
}
