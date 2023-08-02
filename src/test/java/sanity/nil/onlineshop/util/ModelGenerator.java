package sanity.nil.onlineshop.util;

import java.util.Random;
import java.util.UUID;

public class ModelGenerator {

    private static Random random = new Random();

    private static int randomInt() {
        return Math.abs(random.nextInt(15000));
    }

    private static int randomInt(Integer bound) {
        return random.nextInt(bound);
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
//
//    public static UserModel generateUser() {
//        return generateUser(UUID.randomUUID().toString().substring(0,16), UUID.randomUUID().toString().substring(0,20));
//    }
//
//    public static UserModel generateUser(String identifier, String email){
//        UserModel userModel = new UserModel();
//        userModel.setUserId(UUID.randomUUID());
//        userModel.setIdentifier(identifier);
//        userModel.setEmail(email);
//        userModel.setRank(1);
//        userModel.setPassword("wordpass");
//        userModel.setEmailConfirmed(false);
//        userModel.setCreatedAt(LocalDateTime.now());
//        userModel.setUpdatedAt(LocalDateTime.now());
//        userModel.setFirstName("Daniel");
//        return userModel;
//    }
//
//    public static Role generateRole() {
//        Role role = new Role();
//        role.setId((short) randomInt(100));
//        role.setType("");
//        return role;
//    }
//
//    public static Tour generateTour(CityModel cityModel) {
//        Tour tour = new Tour();
//        tour.setTourId(UUID.randomUUID());
//        tour.setShortDescription(randomString(20));
//        tour.setPrice(randomDouble());
//        tour.setCityModel(cityModel);
//        tour.setReviews(null);
//        return tour;
//    }
//
//    public static CityModel generateCity(CountryModel countryModel) {
//       return generateCity(countryModel, null, null, null);
//    }
//
//    public static CityModel generateCity(CountryModel countryModel, List<Image> images, List<Sight> sights, List<Review> reviews) {
//        CityModel cityModel = new CityModel();
//        cityModel.setCityId(randomInt());
//        cityModel.setName(randomString(10));
//        cityModel.setDescription(randomString(30));
//        cityModel.setCountryModel(countryModel);
//        cityModel.setImages(images);
//        cityModel.setSights(sights);
//        cityModel.setReviews(reviews);
//        return cityModel;
//    }
//
//    public static CountryModel generateCountry() {
//        CountryModel countryModel = new CountryModel();
//        countryModel.setCountryId(randomInt());
//        countryModel.setName(randomString(20));
//        countryModel.setDescription(randomString(40));
//        return countryModel;
//    }
//
//    public static Review generateReview() {
//        Review review = new Review();
//        review.setReviewId(UUID.randomUUID());
//        review.setDislikes(randomInt());
//        review.setLikes(randomInt());
//        return review;
//    }
}
