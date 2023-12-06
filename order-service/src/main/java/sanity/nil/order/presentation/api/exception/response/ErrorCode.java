package sanity.nil.order.presentation.api.exception.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR(0, "Internal Server Error"),
    ACCESS_DENIED(1, "Access Denied"),
    BAD_REQUEST(2, "Bad Request"),
    NOT_FOUND(3, "Not Found");

    final int code;
    final String reason;

    private static final Map<Integer, ErrorCode> codes;

    static {
        codes = Stream.of(values()).collect(Collectors.toMap(ErrorCode::getCode, Function.identity()));
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ErrorCode fromValue(final int x) {
        return Objects.requireNonNull(codes.get(x));
    }

    @JsonValue
    public int getValue() {
        return this.code;
    }
}
