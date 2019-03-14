
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Amazon {

	public static void main(String[] args) throws InterruptedException   {

		System.setProperty("webdriver.chrome.driver",
				"C:\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://spinbot.com/Login");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		Assert.assertEquals(title, title);
		
		String str1 = driver.findElement(By.tagName("b")).getText();
		Assert.assertEquals(str1, "Article Spinning, Text Rewriting, Content Creation Tool.");
		
		String str2 = driver.findElement(By.xpath("//h1[contains(text(),'Use your email')]")).getText();
		Assert.assertEquals(str2, "Use your email address to log in.");
		
		String usernameLabel = driver.findElement(By.xpath("//label[@for='Email']")).getText();
		Assert.assertEquals(usernameLabel, "User email");
		
		driver.findElement(By.cssSelector("#Email")).sendKeys("aravind3");
		
		String passwordLabel = driver.findElement(By.xpath("//label[@for='Password']")).getText();
		Assert.assertEquals(passwordLabel, "Password");
		
		driver.findElement(By.cssSelector("#Password")).sendKeys("Aravind*18");
		
		String CheckboxString = driver.findElement(By.cssSelector("label[for='RememberMe']")).getText();
		Assert.assertEquals(CheckboxString, "Remember me?");
		
		driver.findElement(By.cssSelector("#RememberMe")).click();
		
		List<WebElement> frameList = driver.findElements(By.tagName("iframe"));
		int frameCount = frameList.size();
		Assert.assertNotEquals(frameCount, 0);
		System.out.println(frameCount);
		Amazon a = new Amazon();
		By elementToClick = By.cssSelector("#recaptcha-anchor-label");
		int frameIndex1 = frameSwitcher(driver,frameCount, elementToClick );
		driver.switchTo().frame(frameIndex1);

		String capchaString = driver.findElement(By.cssSelector("#recaptcha-anchor-label")).getText();
		Assert.assertEquals(capchaString, "I'm not a robot");
		System.out.println("Assertion complete");
		
		driver.findElement(By.cssSelector(".recaptcha-checkbox-checkmark")).click();
		driver.switchTo().defaultContent();
		
		int frameIndex2 = frameSwitcher(driver,frameCount, elementToClick );
		driver.switchTo().frame(frameIndex2);
		driver.findElement(By.id("recaptcha-verify-button")).click();
		
	}
		
		public static int frameSwitcher(WebDriver driver, int frameCount, By by){
		int i;
		for (i = 0; i <= frameCount; i++){
			
			driver.switchTo().frame(i);
			int count = driver.findElements(by).size();
			if (count > 0) {
				driver.switchTo().defaultContent();
				break;
			}
			else{
				System.out.println("Switching to nextframe");
				driver.switchTo().defaultContent();
			}
		}
		return i;
	}
}