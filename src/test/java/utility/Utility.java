package utility;

import com.github.javafaker.Faker;

public class Utility {
    private Faker faker = new Faker();

    public Utility() {
    }

    public String getFakeUsername() {
        return faker.name().username();
    }

    public String getFakePassword() {
        return faker.internet().password();
    }
}
