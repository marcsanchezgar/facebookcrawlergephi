/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.chrome.ChromeOptions;
 import org.openqa.selenium.interactions.Actions;
/**
 *
 * @author u137679
 */
public class fbCrawler {
    
    static void login(String email,String password){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--mute-audio");
        
        try{
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://en-gb.facebook.com");
            // filling the form
            WebElement searchBox;
            searchBox = driver.findElement(By.name("email"));
            searchBox.sendKeys(email);
            searchBox = driver.findElement(By.name("pass"));
            searchBox.sendKeys(password);
            //login in
            searchBox = driver.findElement(By.id("loginbutton"));
            searchBox.click();
                                   
        }   
        catch(Exception e){
            System.out.println("could not login in");
        }
        
}
}
