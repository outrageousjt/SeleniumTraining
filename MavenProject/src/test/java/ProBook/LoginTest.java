package ProBook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	
	// Déclaration des variables que nous allons utiliser dans ce script.
	String url = "http://probook.progideo.com";
    String expectedTitle1 = "Fil d'actualités - ProBook";
    String actualTitle1 = null;
    String actualTitle2 = null;
	String username = "outrageousjt@gmail.com";
	String password = "hanouna1986";
	WebDriver driver;
  @Test (dataProvider = "Data1")
  public void f(String username,String password,String expectedTitle2) {
		driver.findElement(By.linkText("Se connecter / s'inscrire")).click();
		actualTitle1 = driver.getTitle();
        // On vértifie le titre de la page
        Assert.assertEquals(actualTitle1, expectedTitle1);


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		driver.findElement(By.id("loginBtn")).click();


		 // On vérifie le titre de la page suite à la tentative de connexion
        actualTitle2 = driver.getTitle();
        // On vértifie le titre de la page
        Assert.assertEquals(actualTitle2, expectedTitle2);


  }
	@BeforeMethod
	public void beforeMethod() {
		String browser = "firefox";
		if (browser.equalsIgnoreCase("firefox")) {
	        // Chemin vers le driver Gecko (pour Firefox uniquement)
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\A632353\\eclipse\\drivers\\geckoDriver\\geckodriver-v0.23.0-win64\\geckodriver.exe");
	        // Invocation du navigateur Firefox, qui sera identifié avec le nom "driver".
	        driver = new FirefoxDriver();
	} else if (browser.equalsIgnoreCase("chrome")) {
			// Chemin vers le driver Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\A632353\\eclipse\\drivers\\chromeDriver\\chromedriver_win32\\chromedriver.exe");
	        // Invocation du navigateur Chrome, qui sera identifié avec le nom "driver".
	        driver = new ChromeDriver();
		}
      // Ouvrir la page "http://probook.progideo.com".
      driver.get(url);
	}


  @AfterMethod
  public void afterMethod() {
	//driver.close();
  }
  
  @DataProvider (name="Data1")
  public static Object[][] data1(){
	  return new Object[][] {
		  {"outrageousjt@gmail.com","hanouna1986", "(3) Fil d'actualités - ProBook"},{"testFail","testFail", "Fil d'actualités - ProBook"},
	  };
  }

}
