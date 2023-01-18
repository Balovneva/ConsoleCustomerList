import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        String regexEmail = "\\w{1,15}@{1}\\w{2,15}.{1}(com|ru){1}";
        boolean matchesEmail = components[INDEX_EMAIL].matches(regexEmail);
        String regexPhone = "\\+?[7-8]{1}\\d{10}";
        boolean matchesPhone = components[INDEX_PHONE].matches(regexPhone);

        if (components.length != 4) {
            throw new IllegalArgumentException("Incorrect components length");
        }
        if (!matchesEmail) {
            throw new IllegalArgumentException("Wrong email format");
        }
        if (!matchesPhone) {
            throw new IllegalArgumentException("Wrong number format");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}