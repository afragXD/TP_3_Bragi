package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val yearXPathList = listOf(
    "//*[@id=\"fs-info\"]/div[2]/ul[1]/li[5]/span/a",
    "//*[@id=\"main-page\"]/div/div[2]/div/div[1]/div[3]/a[2]/div[2]",
)

fun getYear(
    driver: ChromeDriver,
    index: Int,
): Short {
    return WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(yearXPathList[index]))
    ).text.toShort()
}