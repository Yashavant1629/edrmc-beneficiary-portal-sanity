package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private static final String TEST_DATA_FILE = "testconfigs/testdata/testdata.json";

    @DataProvider(name = "testDataProvider")
    public static Object[][] provideTestData(Method method) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TestData testData = objectMapper.readValue(new File(TEST_DATA_FILE), TestData.class);

        List<Object[]> data = new ArrayList<>();

        switch (method.getName()) {

            case "resetPassword":
                if (testData.getEmails() != null) {
                    for (String email : testData.getEmails()) {
                        data.add(new Object[]{email});
                    }
                } else {
                    throw new NullPointerException("Emails list is null in testdata.json");
                }
                break;

            case "login":
                if (testData.getEmails() != null && testData.getPassword() != null) {
                    for (String email : testData.getEmails()) {
                        data.add(new Object[]{email, testData.getPassword()});
                    }
                } else {
                    throw new NullPointerException("Emails list or Password is null in testdata.json");
                }
                break;

            default:
                throw new IllegalArgumentException("No test data available for method: " + method.getName());
        }

        return data.toArray(new Object[0][]);
    }
}
