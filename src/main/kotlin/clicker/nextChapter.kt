package clicker

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val nextChapterXPathList = listOf(
    "//*[@id=\"next\"]",
    "/html/body/div[1]/div[3]/div/div[2]/a[2]",
)

fun nextChapter(
    driver: ChromeDriver,
    index: Int,
){
    WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(nextChapterXPathList[index]))
    ).click()
}

//  //*[@id="reader-next"]/div/div/a

//  //*[@id="reader-next"]/div/div/a[2]