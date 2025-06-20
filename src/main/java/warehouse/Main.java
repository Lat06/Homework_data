package warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProductRepository repo = new InMemoryProductRepository();
        ProductService service = new ProductServiceImpl(repo);

        service.create(new Product(null, "Apple", "Fruit", 50, new BigDecimal("1.20")));
        service.create(new Product(null, "Desk", "Furniture", 3, new BigDecimal("199.99")));

        ProductFilter filter = new ProductFilter();
        filter.category = "Fruit";

        List<Product> results = service.search(filter, 0, 10);

        System.out.println("Found products:");
        results.forEach(System.out::println);
    }
}
