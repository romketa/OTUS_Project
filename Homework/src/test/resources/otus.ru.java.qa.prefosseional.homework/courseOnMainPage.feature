#language: ru

  @smoke
  Функционал: Страница курса

    Структура сценария: : Поиск указанного курса
      Пусть Я открываю браузер <браузер>
      Если Найти урок <урок> на главной странице
      И Перейти на страницу урока <путь>
      Тогда На странице урока отображается <урок>

      Примеры:
        | браузер | урок                              | путь                  |
        | chrome  | C++ Developer. Professional       | /cpp-professional     |
        | firefox | Специализация Administrator Linux | /linux-specialization |
        | opera   | Scala-разработчик                 | /scala                |
