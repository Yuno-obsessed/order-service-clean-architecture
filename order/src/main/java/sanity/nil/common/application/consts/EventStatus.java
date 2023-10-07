package sanity.nil.common.application.consts;

import lombok.Getter;

@Getter
public enum EventStatus {
    STATUS_AWAITING(0, "Awaiting"),
    STATUS_PROCESSED(1, "Processed");

    private int code;
    private String value;

    private EventStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(int code) {
        for (EventStatus status : EventStatus.values()) {
            if (status.code == code) {
                return status.value;
            }
        }
        return null;
    }

    public static int getCodeByValue(String value) {
        for (EventStatus status : EventStatus.values()) {
            if (status.value.equals(value)) {
                return status.code;
            }
        }
        return 0;
    }
}
