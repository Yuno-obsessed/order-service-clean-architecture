package sanity.nil.mailservice.application.interfaces;

public interface MailContentTemplate<Input> {

    String load(Input input);
}
