package info

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val ratingCountXPathList = listOf(
    "/html/body/div[1]/div/div/div[2]/div/article/main/aside/div[1]/div[1]/div[2]/div/div[1]/div/div/div/span[2]/span",
    "//*[@id=\"media_type\"]/div[2]/div/div[3]",
)

fun getRatingCount(
    driver: ChromeDriver,
    index: Int,
): Int {
    val stringRatingCount = WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(ratingCountXPathList[index]))
    ).text
    return convertStringToNumber(stringRatingCount)
}

fun convertStringToNumber(input: String): Int {
    return if (input.endsWith("K")) {
        val numberPart = input.substring(0, input.length - 1).toDouble()
        (numberPart * 1000).toInt()
    } else {
        input.toInt()
    }
}