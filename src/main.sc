require: requirements.sc

theme: /

    state: Start
        q!: $regex</start>
        script:
            if ($request && $request.rawRequest && $request.rawRequest.message && $request.rawRequest.message.from && $request.rawRequest.message.from.username) {
                $client.username = "@"+$request.rawRequest.message.from.username;
            }
            # запоминаем user id 
            if ($request && $request.rawRequest && $request.rawRequest.message && $request.rawRequest.message.from && $request.rawRequest.message.from.id) {
                $client.user_id = $request.rawRequest.message.from.id
            }
        # устанавливаются необходимые переменные для первого запуска
            $temp.var = setVariable();
        go!: /Greeting
    
    state: Greeting
        q!: * $greeting *
        script:
        a: Я ознакомлю с расписанием занятий. \nПомогу изучить видеоматериалы, напомню о занятиях. \nОтправлю выполненные работы на проверку. Покажу оценки куратора. \nПомогу с ним связаться по команде "куратор".\nПриму оплату за учебу. \nСписок команд вы всегда можете посмотреть в Справочнике. Он открывается словом "help" или "помоги".\nИтак, начнем.
        inlineButtons: 
            { text: "Начать обучение", callback_data: "Score"}
            { text: "Справочник", callback_data: "Help"}

    state: Help
        q!: * $help *
        script:
            $response.replies = $response.replies || [];
            $response.replies.push( {
                type: "image",
                imageUrl: links["help_picture"],
                text: "Основное рабочее окно выглядит так 👆"
            });
        a: Эта линия️ ⬛️⬛️⬛️⬜️⬜️⬜️показывает прогресс вашего обучения.
        a: Эта линия 🟩🟩🟩🟥🟥🟥 показывает количество набранных баллов за выполненные работы.
        a: Кнопками "⬅️ Назад" и "Вперед ➡️" Вы можете перемещаться между видеоуроками. Кнопка "Смотреть ⏯" даст вам ссылку  на видеолекцию.
        a: Кнопка "Скачать PDF" позволит получить конспект открытой в окне лекции.
        a: Кнопка "Сдать ДЗ" позволит вам отправить работу по пройденному материалу лекции в окне на проверку своему куратору. После проверки куратором вы получите сообщение, а оценка отобразится в нижнем правом углу. Если результат вас не устроил, вы сможете пересдать работу. Сообщение об этом отобразиться на кнопке. Для связи с куратором отправьте в чат слово "куратор".
    
            
        inlineButtons:
            { text: "Вернуться к обучению", callback_data: "Score"}
        
        # стейт считает до 3-х попаданий в noMatch подряд
    state: NoMatch ||noContext = true
        event!: noMatch
        script:
            if ($session.lastState != "/NoMatch") {
                 $session.counter_err = 1;
                 $reactions.answer(selectRandomArg(["Извините, я не понял", "Переформулируйте, пожалуйста, свой вопрос"]));
            } else if ($session.counter_err < 2) {
                $session.counter_err += 1
                $reactions.answer(selectRandomArg(['Повторите, пожалуйста, я не понял (наберите help для помощи)', 'Вы сказали: "{{$request.query}}" - я не понял, может наберете help?']));
            } else {
                $reactions.answer("Извините, я так и не понял вас. \nСейчас я отправлю ваше сообщение куратору.");
                $reactions.transition("./Trouble");
            }
        # после третьего noMatch подряд бот готов отправить сообщение в чат кураторов о проблеме у Пользователя
        state: Trouble
            q!: * $mentor *
            a:  Введите сообщение об интересующем вас вопросе:
            
            state: sendTrouble
                event: noMatch
                script:
                    var message = "У студента " + $client.username + " есть сложности: " + $request.query
                    $temp.response = sendMessageToMentor(message);
                    if ($temp.response) {
                        $reactions.answer("Ваше сообщение передано куратору, в ближайшее время с вами свяжутся");
                    } else {
                        $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                    }
                    $session.hw = $session.step;
                    $reactions.timeout({interval: 3, targetState: "/Score"});
                
            # фильтрация обсценной лексики. всегда найдем, что ответить хаму
    state: Obscene || noContext = true
        q!: * @mlps-obscene.obscene * 
        script:
            $temp.index = $reactions.random(answers.obscene.phrases.length);
        a: {{answers.obscene.phrases[$temp.index]}}