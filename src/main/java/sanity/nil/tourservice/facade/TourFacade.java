package sanity.nil.tourservice.facade;

import lombok.RequiredArgsConstructor;
import sanity.nil.tourservice.service.TourService;
import sanity.nil.tourservice.service.UserService;
import sanity.nil.tourservice.service.CountryService;

@RequiredArgsConstructor
public class TourFacade {

    private TourService tourService;
    private UserService userService;
    private CountryService countryService;
}
