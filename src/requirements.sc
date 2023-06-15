# запускаемые модули/библиотеки встроенных функций
require: slotfilling/slotFilling.sc
    module = sys.zb-common

# подключение справочника городов
require: city/city.sc
    module = sys.zb-common  
    
# подключение болталки offTopic
require: newOfftopic/newOfftopic.sc
    module = sys.zb-common  

# подключение внешней библиотеки для получения времени
require: dateTime/moment.min.js
    module = sys.zb-common 

# подключение словарей
#словарь для хранения текстов ответов
require: dicts/answers.yaml
    var = answers
    name = answers
    
require: dicts/comments.yaml
    var = comments
    name = comments    
 
#словарь для хранения ссылок   
require: dicts/links.yaml
    var = links
    name = links
    
#словарь для хранения текста напоминаний   
require: dicts/reminder.yaml
    var = reminder
    name =  reminder  
    
require: dicts/score.yaml
    var = score
    name =  score    
    
# файлы сценария (код разделен на модули)
# стейт для обработки события telegramCallbackQuery
require: callback.sc

# файлы с функциями на JS
require: functions.js 

# файл с отправкой домашних заданий
require: homework.sc

# файл сценария с паттернами локальных переменных
require: localpatterns.sc

# стейты для отладки и тестирования
require: score.sc

# стейты для отладки и тестирования
require: servicezone.sc

init:
    bind("postProcess", function($context) {
        $context.session.lastState = $context.currentState;
    });
