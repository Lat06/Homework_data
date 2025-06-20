package warehouse;

import java.util.*;

public interface ProductService {
    Product create(Product p);
    Optional<Product> read(UUID id);
    Product update(Product p);
    void delete(UUID id);
    List<Product> search(ProductFilter filter, int page, int size);
}
