package sanity.nil.mailservice.infrastructure.database.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import sanity.nil.mailservice.infrastructure.database.models.MailHistoryModel;

import java.util.UUID;

public interface MailORM extends JpaRepository<MailHistoryModel, UUID> {
}
