package sanity.nil.userservice.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.userservice.application.query.GetUserByEmailAndPasswordQuery;

@RequiredArgsConstructor
public class UserQueryService {

    public final GetUserByEmailAndPasswordQuery getUserByEmailAndPasswordQuery;
}
