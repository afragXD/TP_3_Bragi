# BRAGI парсер

Парсер книг(пока только ранобэ) с использованием selenium и хранение их в Cassandra.
С каких сайтов на данный момент можно парсить:
* [ranobes.com](https://ranobes.com/)
* [ranobelib.me](https://ranobelib.me/)
* [ranobehub.org](https://ranobehub.org/)

## Требования к запуску
- Apache Cassandra.
- Скрипты создания таблиц и пространства ключей(keyspace).
  ```CQL
  CREATE KEYSPACE bragi
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };
  ```

  ```CQL
  use bragi;
  CREATE TABLE IF NOT EXISTS books (
            book_id UUID PRIMARY KEY,
            name TEXT,
            en_name TEXT,
            image TEXT,
            description TEXT,
            rating FLOAT,
            status TEXT,
            chapters SMALLINT,
            year SMALLINT,
            author TEXT,
            rating_count INT,
            country TEXT, 
            genres SET<TEXT>
        );
  ```

  ```CQL
  use bragi;
  create table IF NOT EXISTS chapters(
            chapter_number int,
            chapter_name text,
            book_id UUID,
            chapter_text text,
            PRIMARY KEY ((book_id), chapter_number)
        )
        WITH CLUSTERING ORDER BY (chapter_number ASC);
  ```

## Общая структура
### Структура директорий
* clicker - различные нажатия на кнопки и прочее(первая глава, следующая глава и тд)
* info - получение различной информации о книге(название, рейтинг и тд) и информации о главе(название и текст)
* resources/extensions - для адблока
