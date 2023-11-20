package sanity.nil.mailservice.application.interfaces.persistence;

import sanity.nil.mailservice.application.dto.mail.MailDTO;

public interface MailDAO {

    MailDTO save(MailDTO mailDTO);
}
