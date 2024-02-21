package info

import java.util.UUID

data class BookDTO(
    val name:String,
    val enName: String,
    val image: String,
    val description: String,
    val rating:Float,
    val status: String,
    val chapters: Short,
    val year: Short,
    val author: String,

    val ratingCount: Int,
    //val translator: String,
    val genres: Set<String>,
    val country: String

    )

data class ChapterDTO(
    val chapterNumber: Int,
    val chapterName: String,
    val bookId: UUID,
    val chapterText: String,

)