#language: ru
  Функция: Задание 1
    @Test1
    Структура сценария: Поиск указанного курса (название курса задается в фиче) и его выбора (в случае если несколько, то выбирается случайный)
    Допустим Я открываю "<browser>"
    Затем Поиск указанного курса "<nameCourse>"
      Примеры:
      |browser|nameCourse|
      |CHROME |Java|

