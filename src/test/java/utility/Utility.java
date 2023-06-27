package utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public String getFakeFirstName() {
        return faker.name().firstName();
    }

    public String getFakeLastName() {
        return faker.name().lastName();
    }

    public String getFakeZipPostalCode() {
        return faker.address().zipCode();
    }

    public int getRandomArrayIndex(String[] stringArray) {
        int index = (int) (Math.random() * stringArray.length);
        if (index == stringArray.length) {
            return (int) (Math.random() * (stringArray.length - 1));
        } else {
            return index;
        }
    }

    public int getRandomListIndex(List<WebElement> list) {
        int index = (int) (Math.random() * list.size());
        if (index == list.size()) {
            return (int) (Math.random() * (list.size() - 1));
        } else {
            return index;
        }
    }

    public static String convertIntToString(int value) {
        if (value != 0) {
            return String.valueOf(value);
        }
        return "";
    }

    public int compare(String a, String b) {
        return a.compareTo(b);
    }
}
