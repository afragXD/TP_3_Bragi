package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val ratingXPathList = listOf(
    "//*[@id=\"rate_b\"]/div/div/div/span[1]",
    "//*[@id=\"media_type\"]/div[2]/div/div[2]",
)

fun getRating(
    driver: ChromeDriver,
    index: Int,
): Float {
    var rating = WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(ratingXPathList[index]))
    ).text.toFloat()
    if (index==0)
        rating *= 2
    return rating
}