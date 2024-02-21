package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val authorXPathList = listOf(
    "//*[@id=\"fs-info\"]/div[2]/ul[1]/li[7]/span/a",
    "//*[@id=\"main-page\"]/div/div[2]/div/div[1]/div[3]/div[2]/div[2]/a",
)

fun getAuthor(
    driver: ChromeDriver,
    index: Int,
): String {
    return WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(authorXPathList[index]))
    ).text
}