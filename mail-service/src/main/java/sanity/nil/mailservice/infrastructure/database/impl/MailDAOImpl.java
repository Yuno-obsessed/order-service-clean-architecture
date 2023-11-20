package sanity.nil.mailservice.infrastructure.database.impl;

import lombok.RequiredArgsConstructor;
import sanity.nil.mailservice.application.dto.mail.MailDTO;
import sanity.nil.mailservice.application.interfaces.persistence.MailDAO;
import sanity.nil.mailservice.application.interfaces.persistence.MailReader;
import sanity.nil.mailservice.infrastructure.database.models.MailHistoryModel;
import sanity.nil.mailservice.infrastructure.database.orm.MailORM;
import sanity.nil.mailservice.infrastructure.database.orm.mapper.MailMapper;

@RequiredArgsConstructor
public class MailDAOImpl implements MailDAO, MailReader {

    private final MailORM mailORM;

    @Override
    public MailDTO save(MailDTO mailDTO) {
        MailHistoryModel savedMail = mailORM.save(MailMapper.convertMailDTOToModel(mailDTO));
        return MailMapper.convertMailModelToDTO(savedMail);
    }
}
