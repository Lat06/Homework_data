package warehouse;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<UUID, Product> storage = new HashMap<>();

    public Product save(Product p) {
        if (p.getId() == null) p.setId(UUID.randomUUID());
        storage.put(p.getId(), p);
        return p;
    }

    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
