package warehouse;

import java.util.*;
import java.util.stream.*;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product p) { return repo.save(p); }

    public Optional<Product> read(UUID id) { return repo.findById(id); }

    public Product update(Product p) {
        if (p.getId() == null || repo.findById(p.getId()).isEmpty())
            throw new NoSuchElementException("Not found");
        return repo.save(p);
    }

    public void delete(UUID id) { repo.deleteById(id); }

    public List<Product> search(ProductFilter f, int page, int size) {
        Stream<Product> s = repo.findAll().stream();

        if (f.name != null && !f.name.isBlank())
            s = s.filter(p -> p.getName().toLowerCase().contains(f.name.toLowerCase()));
        if (f.category != null)
            s = s.filter(p -> p.getCategory().equalsIgnoreCase(f.category));
        if (f.minQuantity != null)
            s = s.filter(p -> p.getQuantity() >= f.minQuantity);
        if (f.maxQuantity != null)
            s = s.filter(p -> p.getQuantity() <= f.maxQuantity);
        if (f.minPrice != null)
            s = s.filter(p -> p.getPrice().compareTo(f.minPrice) >= 0);
        if (f.maxPrice != null)
            s = s.filter(p -> p.getPrice().compareTo(f.maxPrice) <= 0);

        return s
                .sorted(Comparator.comparing(Product::getName))
                .skip((long) page * size)
                .limit(size)
                .toList();
    }
}
