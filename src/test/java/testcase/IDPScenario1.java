package testcase;

import base.BaseLogin;
import listener.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(TestListeners.class)
@Test
public class IDPScenario1 extends BaseLogin {

    @Test
    public void scenario1() throws IOException, InterruptedException {
        login();
        
    }
}
