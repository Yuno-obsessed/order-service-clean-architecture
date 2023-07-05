package sanity.nil.tourservice.service;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanity.nil.tourservice.SpringProjectApplication;
import sanity.nil.tourservice.entity.City;
import sanity.nil.tourservice.entity.Country;
import sanity.nil.tourservice.entity.Tour;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static sanity.nil.tourservice.util.EntityGenerator.*;

@SpringBootTest(classes = SpringProjectApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
public class TourServiceTest {

    @Autowired
    private TourService tourService;
    @Captor
    private ArgumentCaptor<Tour> tourArgumentCaptor;

    private City city1;
    private City city2;
    private Country country1;

    @BeforeEach
    public void initTest() {
        country1 = generateCountry();
        city1 = generateCity(country1);
        city2 = generateCity(country1);
    }

    @Test
    @DisplayName("tour saved if entity is valid")
    @Transactional
    public void successSaveValidTour() {
        Tour tour = generateTour(city1);
        tourService.save(tour);

        assertThat(tourService.get(tour.getTourId()).getTourId()).isEqualTo(tour.getTourId());
        assertThat(tourService.get(tour.getTourId())).isEqualTo(tour);
    }

    @Test
    @DisplayName("tours inserted are successfully extracted")
    @Transactional
    public void successGetAll() {
        Tour tour1 = generateTour(city1);
        Tour tour2 = generateTour(city2);
        tourService.save(tour1);
        tourService.save(tour2);
        List<Tour> list = new ArrayList<>(Arrays.asList(tour1,tour2));
        Set<Tour> set = new HashSet<>(list);
        assertThat(tourService.getAll()).hasSize(2);
        assertThat(tourService.getAll()).isEqualTo(list);
        assertThat(new HashSet<>(tourService.getAll())).isEqualTo(set);
    }

    @Test
    @DisplayName("tour is updated successfully")
    @Transactional
    @SneakyThrows
    public void successUpdate() {
        Tour tour1 = generateTour(city1);
        Tour tour2 = (Tour) tour1.clone();
        assertThat(tour1).isEqualTo(tour2);
        tourService.save(tour1);
        tour1.setCity(city2);
        tourService.update(tour1);
        assertThat(tourService.get(tour1.getTourId())).isNotEqualTo(tour2);
    }

    @Test
    @DisplayName("tour deleted if entity with id exists")
    @Transactional
    public void successDeleteExistingTour(){
        Tour tour = generateTour(city1);
        tourService.save(tour);
        assertThat(tourService.get(tour.getTourId())).isEqualTo(tour);
        tourService.delete(tour.getTourId());

        assertThat(tourService.get(tour.getTourId())).isNull();
    }
}
