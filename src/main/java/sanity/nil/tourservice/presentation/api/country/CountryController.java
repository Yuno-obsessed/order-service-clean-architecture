package sanity.nil.tourservice.presentation.api.country;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.tourservice.application.country.dto.CreateCountryDTO;
import sanity.nil.tourservice.application.country.dto.CountryDTO;
import sanity.nil.tourservice.application.country.dto.UpdateCountryDTO;
import sanity.nil.tourservice.application.country.interfaces.interactors.CreateCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.DeleteCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.GetCountryInteractor;
import sanity.nil.tourservice.application.country.interfaces.interactors.UpdateCountryInteractor;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final CreateCountryInteractor createCountryInteractor;
    private final GetCountryInteractor getCountryInteractor;
    private final UpdateCountryInteractor updateCountryInteractor;
    private final DeleteCountryInteractor deleteCountryInteractor;

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Integer id){
        return ResponseEntity
                .status(201)
                .body(getCountryInteractor.getById(id));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CountryDTO> getCountryByCityId(@PathVariable Integer id) {
        return ResponseEntity
                .status(201)
                .body(getCountryInteractor.getByCityId(id));
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CreateCountryDTO createDTO) {
        return ResponseEntity
                .status(200)
                .body(createCountryInteractor.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody UpdateCountryDTO updateDTO) {
        return ResponseEntity
                .status(200)
                .body(updateCountryInteractor.update(updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCountryById(@PathVariable Integer id) {
       return ResponseEntity
               .status(404)
               .body(deleteCountryInteractor.delete(id));
    }

}
