package sanity.nil.mailservice.application.interfaces;

public interface WebTemplate<Response, Params> {

    Response get(Params params);

}
