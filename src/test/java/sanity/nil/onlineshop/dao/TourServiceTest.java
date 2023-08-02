package sanity.nil.onlineshop.dao;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sanity.nil.onlineshop.SpringProjectApplication;
import sanity.nil.onlineshop.infrastructure.model.CountryModel;

@SpringBootTest(classes = SpringProjectApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
public class TourServiceTest {

//    @Autowired
//    private TourService tourService;
//    @Captor
//    private ArgumentCaptor<Tour> tourArgumentCaptor;
//
//    private CityModel cityModel1;
//    private CityModel cityModel2;
//    private CountryModel countryModel1;
//
//    @BeforeEach
//    public void initTest() {
//        countryModel1 = generateCountry();
//        cityModel1 = generateCity(countryModel1);
//        cityModel2 = generateCity(countryModel1);
//    }
//
//    @Test
//    @DisplayName("tour saved if entity is valid")
//    @Transactional
//    public void successSaveValidTour() {
//        Tour tour = generateTour(cityModel1);
//        tourService.save(tour);
//
//        assertThat(tourService.get(tour.getTourId()).getTourId()).isEqualTo(tour.getTourId());
//        assertThat(tourService.get(tour.getTourId())).isEqualTo(tour);
//    }
//
//    @Test
//    @DisplayName("tours inserted are successfully extracted")
//    @Transactional
//    public void successGetAll() {
//        Tour tour1 = generateTour(cityModel1);
//        Tour tour2 = generateTour(cityModel2);
//        tourService.save(tour1);
//        tourService.save(tour2);
//        List<Tour> list = new ArrayList<>(Arrays.asList(tour1,tour2));
//        Set<Tour> set = new HashSet<>(list);
//        assertThat(tourService.getAll()).hasSize(2);
//        assertThat(tourService.getAll()).isEqualTo(list);
//        assertThat(new HashSet<>(tourService.getAll())).isEqualTo(set);
//    }
//
//    @Test
//    @DisplayName("tour is updated successfully")
//    @Transactional
//    @SneakyThrows
//    public void successUpdate() {
//        Tour tour1 = generateTour(cityModel1);
//        Tour tour2 = (Tour) tour1.clone();
//        assertThat(tour1).isEqualTo(tour2);
//        tourService.save(tour1);
//        tour1.setCityModel(cityModel2);
//        tourService.update(tour1);
//        tourService.getAll().forEach(System.out::println);
//        assertThat(tourService.get(tour1.getTourId())).isNotEqualTo(tour2);
//    }
//
//    @Test
//    @DisplayName("tour deleted if entity with id exists")
//    @Transactional
//    public void successDeleteExistingTour(){
//        Tour tour = generateTour(cityModel1);
//        tourService.save(tour);
//        assertThat(tourService.get(tour.getTourId())).isEqualTo(tour);
//        tourService.delete(tour.getTourId());
//
//        assertThat(tourService.get(tour.getTourId())).isNull();
//    }
}
