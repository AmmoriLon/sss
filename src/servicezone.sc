theme: /
    # отладочный стейт, посмотреть, есть ли напоминание. если есть - отобразиться его номер    
    state: ViewRemind
        q!: view
        script:
            $reactions.answer("Напоминание: {{toPrettyString($client.event_arr)}}");
            
    state: Length
        q!: укоротить
        script:
            $client.event_arr.length = 0;
            $reactions.answer("Массив после укорочения: " + toPrettyString($client.event_arr)); 
            
    # отладочный стейт Restart для сброса переменных, закрытия старой и открытия новой сессии
    state: reStart
        q!: $regex</restart>
        script: 
            $context.client = {};
            $jsapi.stopSession();
            $reactions.newSession({message: "/start", data: $request.data});
            
    #стейт для сброса клиентских и сессионных переменных
    state: Reset
        q!: reset
        a: сброшены клиентские и сессионные переменные
        script:
            $client = {};
            $session = {};        
            
    # сервисный стейт для получения id_group
    state: getID
        q!: getid
        script:
            if ($request.channelType.indexOf("chatwidget") > -1) {
                $reactions.answer("id чата: {{$request.channelUserId}}");
            } else {
                $reactions.answer("id чата: {{$request.data.chatId}}");
            }
            
    # стейт для получения json-объекта после события.
    state: file
        event!: noMatch 
        event!: fileEvent
        event!: telegramAnyMessage
        event!: telegramApiRequestFailed
        a: получено: {{toPrettyString($request)}}  
        
    # отправка сообщений из Бота пользователю по его ID        
    state: SendMessage
        q!: sendmessage
        a: Введите ID пользователя, которому хотите написать через Бота

        state: insertID
            q: $regex<\d{6,11}>
            script:
                $temp.sendID = $request.query;
                var regul_id = /\d{8,11}/;          // регулярка user_id
                var msgF = $temp.sendID;
                var sendID = msgF.match(regul_id);
                $session.sendID = Number(sendID);
    #        a: Вы ввели: {{$session.sendID}}, теперь введите сообщение:
            a: Хорошо, теперь введите сообщение:
                
            state: sendMSG
                q: *
                script:
                    var messageFor = $request.query + '\n\nНажите "ОК", чтобы обновить окно';
                    var sendID = $session.sendID;
                    $temp.response = sendMessageByID(sendID, messageFor);
                    if ($temp.response) {
                    $reactions.answer("Ваше сообщение доставлено пользователю с ID: {{$session.sendID}}");
                    } else {
                    $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                    }        
        
   
