package sanity.nil.library.web.interfaces;

public interface WebTemplate<Response, Params> {

    Response get(Params params);

}
