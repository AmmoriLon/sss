theme: /
    
    state: Homework
        q: Отправить задание на проверку
        q: $regex</homework>
        a: Нажмите на скрепку (в окне набора сообщения). Выберите файл для отправки. Нажмите "Открыть". В комментарии к файлу укажите свою фамилию и название работы. Нажмите "Отправить".
        a: Либо разместите ссылку на свою работу, не забудьте комментарий.

        state: ShiftfileEvent
            # событие fileEvent - событие отправки файла пользователем
            event: fileEvent
            script:            
                # сохраняем ссылку на файл с выполненной работой
                $session.filereport = $request.data.eventData[0].url;
                if ($request.rawRequest.message.caption) {
                    $session.caption = $request.rawRequest.message.caption;
                    var message = $session.hw + " " + $client.user_id + " " +  $client.username + ", работа: " + $session.filereport + "\nкомментарий: " + $session.caption
                } else { var message = $session.hw + " " + $client.user_id + " " + $client.username + ", работа: " + $session.filereport + "\nкомментария нет";
                }
                # отправляем сообщение куратору
                $temp.response = sendMessageWithButton(message);
                if ($temp.response) {
                    $reactions.answer("Ваша работа успешно доставлена");
                } else {
                    $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                }
                $reactions.timeout({interval: 3, targetState: "/Score"});
            
            # стейт ловит ссылки студентов.    
        state: FileLink
            event: noMatch
            script:
                var message = $session.hw + " " + $client.user_id + " " + $client.username + ", работа: " + $request.query;
                $temp.response = sendMessageWithButton(message);
                if ($temp.response) {
                    $reactions.answer("Ваше сообщение успешно доставлено");
                } else {
                    $reactions.answer("Какие-то помехи на линии, продублируйте позже");
                }
                $reactions.timeout({interval: 3, targetState: "/Score"});