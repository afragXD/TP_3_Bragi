package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val imgXPathList = listOf(
    "//*[@id=\"dle-content\"]/article/main/div[1]/div/div[1]/div[2]/div[1]/a/img",
    "//*[@id=\"main-page\"]/div/div[2]/div/div[1]/div[1]/div/img",
)

fun getImg(
    driver: ChromeDriver,
    index: Int,
): String {
    return WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(imgXPathList[index]))
    ).getAttribute("src")
}