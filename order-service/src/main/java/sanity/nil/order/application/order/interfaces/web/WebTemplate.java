package sanity.nil.order.application.order.interfaces.web;

public interface WebTemplate<Response, Params> {

    Response get(Params params);

}
