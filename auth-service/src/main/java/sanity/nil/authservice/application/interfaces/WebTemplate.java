package sanity.nil.authservice.application.interfaces;

public interface WebTemplate<Response, Params> {

    Response get(Params params);
}
