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
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
/**
 *
 * @author u137679
 */
public class fbCrawler {
    public fbCrawler(){
    }
    public void login(String email,String password){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\u137679\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--mute-audio");
        List<String> noms = new ArrayList<String>();
        try{
            WebDriver driver = new ChromeDriver(options);
            //driver = driver.Chrome("C:/Users/michael/Downloads/chromedriver_win32/chromedriver.exe");
            
            driver.get("https://en-gb.facebook.com");
            // filling the form
            WebElement searchBox;
            searchBox = driver.findElement(By.name("email"));
            searchBox.sendKeys(email);
            searchBox = driver.findElement(By.name("pass"));
            searchBox.sendKeys(password);
            //login in
            driver.findElement(By.id("loginbutton")).click();
            
            
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@title='Profile']")).click();
            Thread.sleep(1000);
            
            
            String frinedsCount = driver.findElement(By.xpath("//*[@data-tab-key='friends']")).getText().substring(7);
            int count = Integer.parseInt(frinedsCount);
            System.out.println("number of friends: "+ count);
            //click on frineds tab
            driver.findElement(By.xpath("//*[@data-tab-key='friends']")).click();
            Thread.sleep(1000);
            List<WebElement> frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
            int found = frineds.size();
            
            while (found <= 80){
                Thread.sleep(500);
                //scroll to the last friend found from the current loaded friend list
                Coordinates coordinate = ((Locatable) frineds.get(found-1)).getCoordinates();
                coordinate.onPage();
                coordinate.inViewPort();
                Thread.sleep(500);
                frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
                found = frineds.size();

                // break and print frined list if the condition found frineds = count of frined list
                if (80 == found){
                    System.out.println(found);
                    System.out.println("---Printing FriendList---");
                    for (int i=0; i<found; i++){
                        noms.add(frineds.get(i).getText());
                    }
                    break;
                }
            }
            //Scroll To Top
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0, 0);");
            
            //Friends' mutual friends
            Thread.sleep(1000);
            int i = 0;
            for (WebElement friend : frineds) {
                
                System.out.println(friend.getText());
                /*friend.click();
                Thread.sleep(500);
                driver.navigate().back();
                Thread.sleep(500);*/
                    
                
                
            }
            int index=0;
            /*
            while (found1 <= 80){
                Thread.sleep(500);
                //scroll to the last friend found from the current loaded friend list
                Coordinates coordinate = ((Locatable) frineds1.get(found1-1)).getCoordinates();
                coordinate.onPage();
                coordinate.inViewPort();
                Thread.sleep(500);
                frineds1 = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
                found1 = frineds1.size();
                

            //driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + Keys.HOME);
            //
            Jonathan, based on your original post: When you leave the page and come back, the WebElement is no longer linked. The mixed up way to accomplish what you're trying is: (1) Get the list so you know how many links 
            you have (2) Start a loop with the count of the links (3) INSIDE the loop, get the list again (different variable) (4) Using the second variable as the link to click (5) Repeat the loop. Basically, you need to re-read 
            in the links every time you come to the page so that they are not "stale". But you need to read the list in once so you know how many time to iterate the loop. 
            
                                   
            }
        */
        }
        catch(InterruptedException e){
            System.out.println("error:" + e);
            //System.out.println("could not login in");
        } catch (NumberFormatException e) {
            System.out.println("error:" + e);
            //System.out.println("could not login in");
        }
        
}
}
