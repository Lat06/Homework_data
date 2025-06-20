package warehouse;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService service;

    @BeforeEach
    void setup() {
        ProductRepository repo = new InMemoryProductRepository();
        service = new ProductServiceImpl(repo);
    }

    @Test
    void testCreateReadUpdateDelete() {
        Product p = new Product(null, "Apple", "Fruit", 50, new BigDecimal("1.20"));
        Product created = service.create(p);

        assertNotNull(created.getId());
        assertEquals("Apple", service.read(created.getId()).orElseThrow().getName());

        created.setPrice(new BigDecimal("1.50"));
        service.update(created);

        assertEquals(new BigDecimal("1.50"), service.read(created.getId()).get().getPrice());

        service.delete(created.getId());
        assertTrue(service.read(created.getId()).isEmpty());
    }

    @Test
    void testSearchWithFiltersAndPagination() {
        service.create(new Product(null, "Apple", "Fruit", 50, new BigDecimal("1.20")));
        service.create(new Product(null, "Banana", "Fruit", 30, new BigDecimal("0.99")));
        service.create(new Product(null, "Carrot", "Vegetable", 200, new BigDecimal("0.50")));
        service.create(new Product(null, "Desk", "Furniture", 3, new BigDecimal("199.99")));

        ProductFilter filter = new ProductFilter();
        filter.category = "Fruit";
        filter.minPrice = new BigDecimal("0.90");
        filter.maxPrice = new BigDecimal("2.00");

        List<Product> results = service.search(filter, 0, 10);

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(p -> p.getCategory().equalsIgnoreCase("Fruit")));
    }
}
