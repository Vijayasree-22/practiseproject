package context;

import org.openqa.selenium.WebDriver;

public class WebDriverContext {

    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverInstance.set(driver);
    }

    public static WebDriver getDriver() {
        if (driverInstance.get() == null) {
            throw new IllegalStateException(
                "WebDriver has not been initialized. Please call setDriver() first."
            );
        }
        return driverInstance.get();
    }

    public static void tearDriver() {

        if (driverInstance.get() != null) {
            driverInstance.get().quit();
            driverInstance.remove();
        }
    }
}