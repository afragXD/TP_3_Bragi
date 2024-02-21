package clicker

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun startDriver(
    driver: ChromeDriver,
    url:String,
    index: Int,
){
    driver.get(url)
    runBlocking {
        delay(10000)
    }
    if (index == 0) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div"))
            ).click()
        }catch (e:Exception){
            println(e)
        }
    }
}