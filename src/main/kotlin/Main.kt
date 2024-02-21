import clicker.firstChapter
import clicker.nextChapter
import clicker.startDriver
import com.datastax.oss.driver.api.core.CqlSession
import info.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.http.ClientConfig
import java.io.File
import java.net.InetSocketAddress
import java.util.UUID

// список с главными страницами произведений
val url = listOf(
    //"https://ranobes.com/ranobe/305-i-shall-seal-the-heavens.html"
    //"https://ranobelib.me/omniscient-readers-viewpoint-novel?section=info"
    "https://ranobelib.me/suddenly-became-a-princess-one-day-novel?section=info"
)

// принимает ссылку на главную страницу произведения
// возвращает номер сайта с которого парсится это произведение
fun getSiteRank(link: String): Int {
    val ranobeLibPattern = "ranobelib".toRegex()
    val ranobeHubPattern = "ranobehub".toRegex()
    val ranobeSPattern = "ranobes".toRegex()

    return when {
        ranobeHubPattern.containsMatchIn(link) -> 2
        ranobeLibPattern.containsMatchIn(link) -> 1
        ranobeSPattern.containsMatchIn(link) -> 0
        else -> -1 // Возвращаем -1 для несоответствующих ссылок
    }
}

// запрос на добавление книги в бд
const val insertBook = """
        INSERT INTO books (
            book_id,
            name,
            en_name,
            image,
            description,
            rating,
            status,
            chapters,
            year,
            author,
            rating_count,
            genres,
            country
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """

// запрос на добавление главы в бд
const val insertChar = """
        INSERT INTO chapters (
            chapter_number,
            chapter_name,
            book_id,
            chapter_text
        )
        VALUES (?, ?, ?, ?)
    """

fun main() {
    getData()
}

fun getData(){

    val nowUrl = url[0]

    val urlIndex = getSiteRank(nowUrl)

    val session = CqlSession.builder()
        .withKeyspace("Bragi")
        .addContactPoint(InetSocketAddress("127.0.0.1", 9042))
        .withLocalDatacenter("datacenter1")
        .build()

    val options = ChromeOptions()
    options.addExtensions(File("./src/main/resources/extensions/AdBlock.crx"))
    options.addArguments("--no-sandbox")
    options.addArguments("disable-dev-shm-usage")
    options.addArguments("--disable-popup-blocking")

    val driver = ChromeDriver(options)

    try {
        startDriver(driver = driver, url = nowUrl, urlIndex)

        val bookDTO = BookDTO(
            name = getName(driver, urlIndex),
            enName = getEnName(driver, urlIndex),
            image = getImg(driver, urlIndex),
            description = getDescription(driver, urlIndex),
            rating = getRating(driver, urlIndex),
            status = getStatus(driver, urlIndex),
            chapters = getChapters(driver, urlIndex),
            year = getYear(driver, urlIndex),
            author = getAuthor(driver, urlIndex),
            ratingCount = getRatingCount(driver, urlIndex),
            //translator = getTranslator(driver, urlIndex),
            genres = getGenres(driver, urlIndex),
            country = getCountry(driver, urlIndex)
        )
        println(bookDTO)

        val uuid = UUID.randomUUID()
        session.execute(
            insertBook,
            uuid,
            bookDTO.name,
            bookDTO.enName,
            bookDTO.image,
            bookDTO.description,
            bookDTO.rating,
            bookDTO.status,
            bookDTO.chapters,
            bookDTO.year,
            bookDTO.author,
            bookDTO.ratingCount,
            //bookDTO.translator,
            bookDTO.genres,
            bookDTO.country
        )

        firstChapter(driver, urlIndex)
        for (i in 1..bookDTO.chapters){
            val chapterDTO = ChapterDTO(
                chapterNumber = i,
                chapterName = getChapterName(driver, urlIndex),
                bookId = uuid,
                chapterText = getChapterText(driver, urlIndex)
            )
            session.execute(
                insertChar,
                chapterDTO.chapterNumber,
                chapterDTO.chapterName,
                chapterDTO.bookId,
                chapterDTO.chapterText
            )
            nextChapter(driver, urlIndex)
        }
    }finally {
        session.close()
        driver.quit()
    }
}