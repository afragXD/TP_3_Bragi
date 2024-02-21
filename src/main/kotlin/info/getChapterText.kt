package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val chapterTextXPathList = listOf(
    "//*[@id=\"arrticle\"]",
    "/html/body/div[1]/div[4]",
)

fun getChapterText(
    driver: ChromeDriver,
    index: Int,
): String {
    return WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(chapterTextXPathList[index]))
    ).text
}