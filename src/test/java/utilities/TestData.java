package utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class TestData {

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getPassword() {
        return password.toString();
    }

    public void setPassword(String password) {
        this.password = Collections.singletonList(password);
    }

    private List<String> email;
    private List<String> password;

    private List<String> emails;


    public List<String> getEmails() {
        return emails;
    }
}
