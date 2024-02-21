package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val chapterNameXPathList = listOf(
    "//*[@id=\"dle-content\"]/article/div[2]/h1",
    "/html/body/div[1]/div[3]/div/a/div[3]",
)

fun getChapterName(
    driver: ChromeDriver,
    index: Int,
): String {
    return WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(chapterNameXPathList[index]))
    ).text.split('\n')[0]
}