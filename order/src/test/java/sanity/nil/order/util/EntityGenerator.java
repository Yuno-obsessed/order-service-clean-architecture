package sanity.nil.order.util;

import sanity.nil.order.application.common.domain.vo.Deleted;
import sanity.nil.order.application.common.domain.vo.Discount;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.ProductQueryDTO;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.domain.order.vo.AddressID;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.domain.product.vo.ProductID;
import sanity.nil.order.domain.product.vo.ProductStatistics;
import sanity.nil.order.domain.product.vo.ProductSubtype;
import sanity.nil.order.domain.product.vo.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static sanity.nil.order.application.common.domain.vo.Discount.DiscountType.getByDiscount;

public class EntityGenerator {

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

    public static Discount generateActiveDiscount(int discount) {
        return new Discount(getByDiscount(discount),
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(5));
    }

    public static Discount generateExpiredDiscount(int discount) {
       return new Discount(getByDiscount(discount),
               LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(2));
    }

    public static ProductSubtype generateProductSubtype(int id) {
        return new ProductSubtype(id, "subtype_test", "ST",
                new ProductType("type_test", "TT"));
    }

    public static ProductStatistics generateProductStatistics() {
        return new ProductStatistics(BigDecimal.valueOf(4.5), 10, 8);
    }

    public static Product generateProduct(UUID id, String name, double price, Discount discount) {
        return new Product(new ProductID(id), "desc", name,
                BigDecimal.valueOf(price), discount,
                4, true, new Deleted(),
                generateProductSubtype(1), generateProductStatistics());
    }

    public static Product generateProduct(Product product) {
        return new Product(product.getProductId(), product.getDescription(), product.getName(),
                product.getPrice(), product.getDiscount(), product.getQuantity(), product.isAvailable(),
                product.getDeleted(),product.getProductSubtype(),product.getProductStatistics());
    }

    public static Address generateAddress(UUID id) {
        return new Address(new AddressID(id), "Italy", "Rome",
                "via Venezia", 3, "00183");
    }

    public static AddressQueryDTO generateAddressQueryDTO() {
        return new AddressQueryDTO(UUID.randomUUID(), "Italy", "Rome", "via XX Settembre", 23, "00193");
    }

    public static ProductQueryDTO generateProductQueryDTO() {
        return new ProductQueryDTO(UUID.randomUUID(), "Laptop", BigDecimal.valueOf(1500), 50, 2, BigDecimal.valueOf(1500));
    }

    public static OrderQueryDTO generateOrderQueryDTO(UUID clientID) {
        return new OrderQueryDTO(UUID.randomUUID(), generateAddressQueryDTO(), clientID, List.of(generateProductQueryDTO()),
                "CASH", "ONE_TRANSFER", LocalDateTime.now(), LocalDateTime.now());
    }

}
