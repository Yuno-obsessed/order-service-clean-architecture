package sanity.nil.tourservice.util;

import sanity.nil.tourservice.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EntityGenerator {

    private static Random random = new Random();

    private static int randomInt() {
        return Math.abs(random.nextInt(15000));
    }

    private static double randomDouble() {
        return Math.abs(random.nextDouble());
    }

    private static String randomString(int length) {
        if (length > 36) {
            length = 36;
        }
        return UUID.randomUUID().toString().substring(0,length);
    }

    public static User generateUser(String identifier, String email){
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setIdentifier(identifier);
        user.setEmail(email);
        user.setRank(1);
        user.setPassword("wordpass");
        user.setEmailConfirmed(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setFirstName("Daniel");
        return user;
    }

    public static Tour generateTour(City city) {
        Tour tour = new Tour();
        tour.setTourId(UUID.randomUUID());
        tour.setShortDescription(randomString(20));
        tour.setPrice(randomDouble());
        tour.setCity(city);
        tour.setReviews(null);
        return tour;
    }

    public static City generateCity(Country country) {
       return generateCity(country, null, null, null);
    }

    public static City generateCity(Country country, List<Image> images, List<Sight> sights, List<Review> reviews) {
        City city = new City();
        city.setCityId(randomInt());
        city.setName(randomString(10));
        city.setDescription(randomString(30));
        city.setCountry(country);
        city.setImages(images);
        city.setSights(sights);
        city.setReviews(reviews);
        return city;
    }

    public static Country generateCountry() {
        Country country = new Country();
        country.setCountryId(randomInt());
        country.setName(randomString(20));
        country.setDescription(randomString(40));
        return country;
    }

    public static Review generateReview() {
        Review review = new Review();
        review.setReviewId(UUID.randomUUID());
        review.setDislikes(randomInt());
        review.setLikes(randomInt());
        return review;
    }
}
