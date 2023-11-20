package sanity.nil.mailservice.application.consts;

public enum MailType {

    CREATED_ORDER("Your order was created."),
    ADVERTISEMENT("Here are some products you may like.");

    private String value;

    MailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
