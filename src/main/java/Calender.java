import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Calender {

	public static WebDriver driver;

	@Test
	public void calender() throws Exception {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://www.cleartrip.com/");
		waitFor(2000);
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
		Thread.sleep(4000);

		waitFor(5000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
		// Xpath was wrong.
		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination
		waitFor(5000);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		// Calling Method for Calender
		cal(29, 12, 2023);

	}

	// Calender
	public static void cal(int date, int month, int year) {
		// month  bec in website month is starting from 0 so we need to give month
		month--;
		int count = 1;
		while (count <= 20) {
			try {
				driver.findElement(By.xpath("//td[@data-month='"+month +"'and @data-year='" + year + "']/a[text()='" + date + "']")).click();
				break;
			} catch (Exception e) {
				// It will click on next button
				driver.findElement(By.xpath(" //div[@id='ui-datepicker-div']/div[2]/div/a[@class='nextMonth ' and @title='Next']")).click();
				count++;
				
				if(count>20) {
					System.out.println("Invalid Input");
				}
				
			}

		}

	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

}
