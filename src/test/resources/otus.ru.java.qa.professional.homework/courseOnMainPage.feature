#language: ru

  @smoke
  Функционал: Курсы на главной странице

    @short
    Структура сценария: Поиск указанного курса
      Пусть Я открываю браузер '<браузер>'
      И Открываю главную страницу otus.ru
      Если Найти курс "QA" на главной странице
      И Перейти на страницу урока
      Тогда На странице урока отображается заголовок урока

      Примеры:
        | браузер |
        | chrome  |
        | firefox |
        | opera   |

    @long
    Структура сценария: Поиск курса по дате
      Пусть Я открываю браузер '<браузер>'
      И Открываю главную страницу otus.ru
      Если Найти курс по дате "28-07-2022" или позже этой даты, если курса за указанную дату нет
      Тогда Вывести найденный курс на консоль


      Примеры:
        | браузер |
        | chrome  |
        | firefox |
        | opera   |

    @short
    Структура сценария: Поиск самого дорогого и дешёвого курса
      Пусть Я открываю браузер '<браузер>'
      Если Открыть страницу курсов "online"
      Тогда Страница курсов "Подготовительные онлайн-курсы, обучение в OTUS c нуля, уроки для начинающих" открыта
      Если Найти самый дорогой курс
      Тогда Вывести информацию о них на консоль
      Если Найти самый дешёвый курс
      Тогда Вывести информацию о них на консоль


      Примеры:
        | браузер |
        | chrome  |
        | firefox |
        | opera   |