import com.sun.javafx.PlatformUtil;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	WebDriver driver = new ChromeDriver();

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException {

		setDriverPath();

		driver.get("https://www.cleartrip.com/");
		waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();
		
		
		Thread.sleep(3000);
		//Need to switch the frame. bec its present in frame
		driver.switchTo().frame("modal_window");
		System.out.println("Test");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@id='signInButton']")).click();
		
		
    	

		String errors1 = driver.findElement(By.id("errors1")).getText();
	Assert.assertTrue(errors1.contains("There were errors in your submission"));
		//driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
