package sanity.nil.tourservice.application.country.exceptions;

public class CountryNotFound extends RuntimeException{

    public CountryNotFound(String message) {
        super(message);
    }

    public static String getCountryIdNotFoundMessage(Integer id) {
        return String.format("Country with id = %s wasn't found", id);
    }

    public static String getCityIdNotFoundMessage(Integer id) {
        return String.format("Country with city id = %s wasn't found", id);
    }
//    public static class ReasonMessage {
//
//        private Reason reason;
//        private Integer id;
//
//        public ReasonMessage(Reason reason, Integer id) {
//            this.reason = reason;
//            this.id = id;
//        }
//
//        public String getMessage() {
//            return reason.value + id + " wasn't found\n";
//        }
//
//        public enum Reason {
//            COUNTRY_ID("Country with id = "),
//            CITY_ID("Country with city id = ");
//
//            private final String value;
//
//            private Reason(String value) {
//                this.value = value;
//            }
//
//            public String getValue() {
//                return value;
//            }
//        }
//
//        public Reason getReason() {
//            return reason;
//        }
//
//        public void setReason(Reason reason) {
//            this.reason = reason;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//    }
}
