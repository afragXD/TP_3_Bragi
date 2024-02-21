package clicker

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val firstChapterXPathList = listOf(
    "//*[@id=\"fs-chapters\"]/div/div[3]/a[1]",
    "-",
)

val urlFirstChapter = listOf(
    "https://ranobelib.me/suddenly-became-a-princess-one-day-novel/v1/c1?bid=3654"
)

fun firstChapter(
    driver: ChromeDriver,
    index: Int,
    ){
    if (index!=1) {
        WebDriverWait(driver, Duration.ofSeconds(5)).until(
            ExpectedConditions
                .presenceOfElementLocated(By.xpath(firstChapterXPathList[index]))
        ).click()
    }else{
        driver.get(urlFirstChapter[0])
    }
}