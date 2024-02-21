package info

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

val nameXPathList = listOf(
        "/html/body/div[1]/div/div/div[2]/div/article/main/div[1]/div/div[1]/h1",
        "//*[@id=\"media_type\"]/div[1]/div[1]",
        )

val nameEnXPathList = listOf(
    "/html/body/div[1]/div/div/div[2]/div/article/main/div[1]/div/div[1]/h1",
    "//*[@id=\"media_type\"]/div[1]/div[2]",
)

fun getName(
    driver: ChromeDriver,
    index: Int,
):String{
    val name = WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(nameXPathList[index]))
    ).text
    return name.split("\n")[0]
}

fun getEnName(
    driver: ChromeDriver,
    index: Int,
):String{
    var name = WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions
            .presenceOfElementLocated(By.xpath(nameEnXPathList[index]))
    ).text
    if (index==0) {
        name = name.split("\n")[1].trimStart('•').split("•")[0].trim()

        val client = HttpClient()
        runBlocking {
            val httpResponse: HttpResponse = client.get("https://api.mymemory.translated.net/get") {
                parameter("q", name)
                parameter("langpair", "ru|en")
            }
            val sttt: String = httpResponse.body()
            val gson = Gson()
            val map = gson.fromJson(sttt, Map::class.java)
            val responseData = map["responseData"] as Map<String, Any>
            val translatedText = responseData["translatedText"] as String
            name = translatedText.replace(" ", "_").lowercase()
        }
    }

    return name
}