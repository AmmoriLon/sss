theme: /
    # стейт "ловит" факт нажатия на инлайн-кнопки в ТГ
    state: ReceiptCallbackQuery
        event!: telegramCallbackQuery
        if: $request.rawRequest.callback_query.data === "Score"     // начало обучения
            script:
                $session.hw = "01";
                $reactions.transition("/Score");
                
        if: $request.rawRequest.callback_query.data === "Help"     // справочник
            script: $reactions.transition("/Help");                
                
        if: $request.rawRequest.callback_query.data === "Delete"
            script:
                var id = $session.message_id1
                $response.replies = $response.replies || [];
                $response.replies.push({
                    "type": "raw",
                        "body": {
                        "chat_id": $request.data.chatId,     
                        "message_id": id
                    },
                    "method": "deleteMessage"
                });  
        # "листалка" карточек уроков с 1 по 8        
        if: $request.rawRequest.callback_query.data === "1"
            script:
                $session.step = "01";
                var id = $session.message_id1;
                var photo = links["photo1"];
                var caption = comments["caption1"] + score[$session.score];
                var clb_back = "8";
                var clb_forward = "2";
                var url_video = links["video1"];
                var url_pdf = links["conspektus1"];
                var txt_pass = $session.txt_pass1;
                var clb_hw = "HW1";
                var txt_score = $session.txt_score1;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)

        if: $request.rawRequest.callback_query.data === "2"
            script:
                $session.step = "02";
                var id = $session.message_id1;
                var photo = links["photo2"];
                var caption = comments["caption2"] + score[$session.score];
                var clb_back = "1";
                var clb_forward = "3";
                var url_video = links["video2"];
                var url_pdf = links["conspektus2"];
                var txt_pass = $session.txt_pass2;
                var clb_hw = "HW2";
                var txt_score = $session.txt_score2;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)    
                
        if: $request.rawRequest.callback_query.data === "3"
            script:
                $session.step = "03";
                var id = $session.message_id1;
                var photo = links["photo3"];
                var caption = comments["caption3"] + score[$session.score];
                var clb_back = "2";
                var clb_forward = "4";
                var url_video = links["video3"];
                var url_pdf = links["conspektus3"];
                var txt_pass = $session.txt_pass3;
                var clb_hw = "HW3";
                var txt_score = $session.txt_score3;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)         

        if: $request.rawRequest.callback_query.data === "4"
            script:
                $session.step = "04";
                var id = $session.message_id1;                
                var photo = links["photo4"];
                var caption = comments["caption4"] + score[$session.score];
                var clb_back = "3";
                var clb_forward = "5";
                var url_video = links["video4"];
                var url_pdf = links["conspektus4"];
                var txt_pass = $session.txt_pass4;
                var clb_hw = "HW4";
                var txt_score = $session.txt_score4;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)
                
        if: $request.rawRequest.callback_query.data === "5"
            script:
                $session.step = "05";
                var id = $session.message_id1; 
                var photo = links["photo5"];
                var caption = comments["caption5"] + score[$session.score];
                var clb_back = "4";
                var clb_forward = "6";
                var url_video = links["video5"];
                var url_pdf = links["conspektus5"];
                var txt_pass = $session.txt_pass5;
                var clb_hw = "HW5";
                var txt_score = $session.txt_score5;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)
                
        if: $request.rawRequest.callback_query.data === "6"
            script:
                $session.step = "06";
                var id = $session.message_id1;         
                var photo = links["photo6"];
                var caption = comments["caption6"] + score[$session.score];
                var clb_back = "5";
                var clb_forward = "7";
                var url_video = links["video6"];
                var url_pdf = links["conspektus6"];
                var txt_pass = $session.txt_pass6;
                var clb_hw = "HW6";
                var txt_score = $session.txt_score6;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)
                
        if: $request.rawRequest.callback_query.data === "7"
            script:
                $session.step = "07";
                var id = $session.message_id1;             
                var photo = links["photo7"];
                var caption = comments["caption7"] + score[$session.score];
                var clb_back = "6";
                var clb_forward = "8";
                var url_video = links["video7"];
                var url_pdf = links["conspektus6"];
                var txt_pass = $session.txt_pass7;
                var clb_hw = "HW7";
                var txt_score = $session.txt_score7;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)
                
        if: $request.rawRequest.callback_query.data === "8"
            script:
                $session.step = "08";
                var id = $session.message_id1;
                var photo = links["photo8"];
                var caption = comments["caption8"] + score[$session.score];
                var clb_back = "7";
                var clb_forward = "1";
                var url_video = links["video8"];
                var url_pdf = links["conspektus8"];
                var txt_pass = $session.txt_pass8;
                var clb_hw = "HW8";
                var txt_score = $session.txt_score8;
                $temp.responce = editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score)
                
        if: $request.rawRequest.callback_query.data === "HW1"      // для отправления домашнего задания с карточки урока
            script:
                $session.hw = "01";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW2"
            script:
                $session.hw = "02";
            go!: /Homework   
            
        if: $request.rawRequest.callback_query.data === "HW3"
            script:
                $session.hw = "03";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW4"
            script:
                $session.hw = "04";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW5"
            script:
                $session.hw = "05";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW6"
            script:
                $session.hw = "06";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW7"
            script:
                $session.hw = "07";
            go!: /Homework
            
        if: $request.rawRequest.callback_query.data === "HW8"
            script:
                $session.hw = "08";
            go!: /Homework 
        # "ловит" user_id и отправляет выставленные оценки пользователю
        if: $request.rawRequest.callback_query.data === "Отлично" 
            script:
                $temp.msg = $request.rawRequest.callback_query.message.text;
                var reg_id = /\d{8,11}/;          // регулярка user_id
                var reg_hw = /^\d{2}/;            // регулярка номера дом.работы
                var msg = $temp.msg;
                var user_id = msg.match(reg_id);  // получаем user_id из сообщения
                var user_hw = msg.match(reg_hw);  // получаем номер работы из сообщения
                var message = user_hw + ' Работа оценена на "Отлично".\nНажмите "ОК", чтобы баллы отобразились в системе.';
                var clb_data = "Send_Msg1";
                user_id = Number(user_id);        //преобразовываем тип переменной
                $temp.response = sendMessageToUser(user_id, message, clb_data);
                var id2 = $request.rawRequest.callback_query.message.message_id; //меняем клавиатуру в чате куратора на "Переоценить"
                $temp.response = hw_verified(id2);             

        if: $request.rawRequest.callback_query.data === "Хорошо" 
            script:
                $temp.msg = $request.rawRequest.callback_query.message.text;
                var reg_id = /\d{8,11}/;         
                var reg_hw = /^\d{2}/;           
                var msg = $temp.msg;
                var user_id = msg.match(reg_id);  
                var user_hw = msg.match(reg_hw);  
                var message = user_hw + ' Работа оценена на "Хорошо".\nНажмите "ОК", чтобы баллы отобразились в системе.';
                var clb_data = "Send_Msg2";
                user_id = Number(user_id);
                $temp.response = sendMessageToUser(user_id, message, clb_data);
                var id2 = $request.rawRequest.callback_query.message.message_id;
                $temp.response = hw_verified(id2); 
        
        if: $request.rawRequest.callback_query.data === "Неплохо" 
            script:
                $temp.msg = $request.rawRequest.callback_query.message.text;
                var reg_id = /\d{8,11}/;         
                var reg_hw = /^\d{2}/;           
                var msg = $temp.msg;
                var user_id = msg.match(reg_id);  
                var user_hw = msg.match(reg_hw);  
                var message = user_hw + ' Работа выполнена в целом неплохо, но есть куда двигаться. Вы можете пересдать ее при желании.\nНажмите "ОК", чтобы баллы отобразились в системе.';
                var clb_data = "Send_Msg3";
                user_id = Number(user_id);
                $temp.response = sendMessageToUser(user_id, message, clb_data);
                var id2 = $request.rawRequest.callback_query.message.message_id;
                $temp.response = hw_verified(id2); 
                
        if: $request.rawRequest.callback_query.data === "Переделать" 
            script:
                $temp.msg = $request.rawRequest.callback_query.message.text;
                var reg_id = /\d{8,11}/;         
                var reg_hw = /^\d{2}/;           
                var msg = $temp.msg;
                var user_id = msg.match(reg_id);  
                var user_hw = msg.match(reg_hw);  
                var message = user_hw + ' Работу нужно переделать. Свяжитесь с куратором для консультации.\nНажмите "ОК", чтобы продолжить.';
                var clb_data = "Send_Msg4";
                user_id = Number(user_id);
                $temp.response = sendMessageToUser(user_id, message, clb_data);
                var id2 = $request.rawRequest.callback_query.message.message_id;
                $temp.response = hw_verified(id2);                 
        
        # дешифратор оценок, "ловит" выставленные оценки за ДЗ после нажатия на ОК
        if: $request.rawRequest.callback_query.data === "Send_Msg1" 
            script:
                var id3 = $request.rawRequest.callback_query.message.message_id; //получаем message_id сообщения с кнопкой "ОК"
                $temp.response = noButtonOk(id3);
                $temp.msg = $request.rawRequest.callback_query.message.text;
                if ($temp.msg[0] == 0 && $temp.msg[1] == 1) {
                    $session.txt_score1 = 5;
                    $session.lesson = 1;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 2) { 
                    $session.txt_score2 = 5;
                    $session.lesson = 2;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 3) { 
                    $session.txt_score3 = 5;
                    $session.lesson = 3;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 4) { 
                    $session.txt_score4 = 5;
                    $session.lesson = 4;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 5) { 
                    $session.txt_score5 = 5;
                    $session.lesson = 5;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 6) { 
                    $session.txt_score6 = 5;
                    $session.lesson = 6;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 7) { 
                    $session.txt_score7 = 5;
                    $session.lesson = 7;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 8) { 
                    $session.txt_score8 = 5; 
                    $session.lesson = 8;
                } else {
                    $reactions.answer("Непонятно с 'Отлично'")
                }
                $reactions.transition("/Score");

        if: $request.rawRequest.callback_query.data === "Send_Msg2"
            script:
                var id3 = $request.rawRequest.callback_query.message.message_id; 
                $temp.response = noButtonOk(id3);
                $temp.msg = $request.rawRequest.callback_query.message.text;
                if ($temp.msg[0] == 0 && $temp.msg[1] == 1) {
                    $session.txt_score1 = 4;
                    $session.lesson = 1;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 2) { 
                    $session.txt_score2 = 4;
                    $session.lesson = 2;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 3) { 
                    $session.txt_score3 = 4;
                    $session.lesson = 3;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 4) { 
                    $session.txt_score4 = 4;
                    $session.lesson = 4;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 5) { 
                    $session.txt_score5 = 4;
                    $session.lesson = 5;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 6) { 
                    $session.txt_score6 = 4;
                    $session.lesson = 6;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 7) { 
                    $session.txt_score7 = 4;
                    $session.lesson = 7;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 8) { 
                    $session.txt_score8 = 4; 
                    $session.lesson = 8;
                } else {
                    $reactions.answer("Непонятно с 'Хорошо'")
                }
                $reactions.transition("/Score");
             
        if: $request.rawRequest.callback_query.data === "Send_Msg3"
            script:
                var id3 = $request.rawRequest.callback_query.message.message_id; 
                $temp.response = noButtonOk(id3);
                $temp.msg = $request.rawRequest.callback_query.message.text;
                if ($temp.msg[0] == 0 && $temp.msg[1] == 1) {
                    $session.txt_score1 = 3;
                    $session.txt_pass1 = "Можно пересдать";
                    $session.lesson = 1;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 2) { 
                    $session.txt_score2 = 3;
                    $session.txt_pass2 = "Можно пересдать";
                    $session.lesson = 2;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 3) { 
                    $session.txt_score3 = 3;
                    $session.txt_pass3 = "Можно пересдать";
                    $session.lesson = 3;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 4) { 
                    $session.txt_score4 = 3;
                    $session.txt_pass4 = "Можно пересдать";
                    $session.lesson = 4;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 5) { 
                    $session.txt_score5 = 3;
                    $session.txt_pass5 = "Можно пересдать";
                    $session.lesson = 5;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 6) { 
                    $session.txt_score6 = 3;
                    $session.txt_pass6 = "Можно пересдать";
                    $session.lesson = 6;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 7) { 
                    $session.txt_score7 = 3;
                    $session.txt_pass7 = "Можно пересдать";
                    $session.lesson = 7;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 8) { 
                    $session.txt_score8 = 3; 
                    $session.txt_pass8 = "Можно пересдать";
                    $session.lesson = 8;
                } else {
                    $reactions.answer("Непонятно с 'Неплохо'")
                } 
                $reactions.transition("/Score");
                
        if: $request.rawRequest.callback_query.data === "Send_Msg4"
            script:
                var id3 = $request.rawRequest.callback_query.message.message_id; 
                $temp.response = noButtonOk(id3);
                $temp.msg = $request.rawRequest.callback_query.message.text;
                if ($temp.msg[0] == 0 && $temp.msg[1] == 1) {
                    $session.txt_score1 = 0;
                    $session.txt_pass1 = "Пересдать ДЗ";
                    $session.lesson = 1;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 2) { 
                    $session.txt_score2 = 0;
                    $session.txt_pass2 = "Пересдать ДЗ";
                    $session.lesson = 2;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 3) { 
                    $session.txt_score3 = 0;
                    $session.txt_pass3 = "Пересдать ДЗ";
                    $session.lesson = 3;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 4) { 
                    $session.txt_score4 = 0;
                    $session.txt_pass4 = "Пересдать ДЗ";
                    $session.lesson = 4;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 5) { 
                    $session.txt_score5 = 0;
                    $session.txt_pass5 = "Пересдать ДЗ";
                    $session.lesson = 5;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 6) { 
                    $session.txt_score6 = 0;
                    $session.txt_pass6 = "Пересдать ДЗ";
                    $session.lesson = 6;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 7) { 
                    $session.txt_score7 = 0;
                    $session.txt_pass7 = "Пересдать ДЗ";
                    $session.lesson = 7;
                } else if ($temp.msg[0] == 0 && $temp.msg[1] == 8) { 
                    $session.txt_score8 = 0; 
                    $session.txt_pass8 = "Пересдать ДЗ";
                    $session.lesson = 7;
                } else {
                    $reactions.answer("Непонятно с 'Пересдать'")
                }             
                $reactions.transition("/Score");
                
        if: $request.rawRequest.callback_query.data === "Re_Verified"     //возвращает 4-х балльную клавиатуру в чате куратора 
            script:
                var id2 = $request.rawRequest.callback_query.message.message_id;
                $temp.response = re_verified(id2);
                
        if: $request.rawRequest.callback_query.data === "NoOK"
            script:
                var id3 = $request.rawRequest.callback_query.message.message_id; //получаем message_id сообщения с кнопкой "ОК"
                $temp.response = noButtonOk(id3);           // убираем кнопку "ОК"
                $session.hw = $session.step;                // обновляем окно
                $reactions.transition("/Score"); 