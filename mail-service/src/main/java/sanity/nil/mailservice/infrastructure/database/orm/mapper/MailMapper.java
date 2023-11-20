package sanity.nil.mailservice.infrastructure.database.orm.mapper;

import sanity.nil.mailservice.application.dto.mail.MailDTO;
import sanity.nil.mailservice.infrastructure.database.models.MailHistoryModel;

import java.util.UUID;

public class MailMapper {

    public static MailDTO convertMailModelToDTO(MailHistoryModel model) {
        return new MailDTO(model.getTopic(), model.getMailContent(), model.getRecipients(), model.getSendAt());
    }

    public static MailHistoryModel convertMailDTOToModel(MailDTO dto) {
        return new MailHistoryModel(UUID.randomUUID(), dto.topic, dto.mailContent, dto.recipients, dto.sendAt);
    }
}
