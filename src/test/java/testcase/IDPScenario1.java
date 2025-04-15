package testcase;

import base.BaseLogin;
import org.testng.annotations.Test;

import java.io.IOException;

public class IDPScenario1 extends BaseLogin {

    @Test
    public void scenario1() throws IOException, InterruptedException {
        login();
        
    }
}
