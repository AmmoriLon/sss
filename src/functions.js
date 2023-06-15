// функция отправляет сообщение с оценкой в чат
function sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score) {
    var token = $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().request.data.chatId;
    var url = "https://api.telegram.org/bot" + token + "/sendPhoto";
    var options = {
        body: {
            "chat_id": chat_id,
            "photo": photo,
            "caption": caption,
            "reply_markup":{
                "inline_keyboard":[[
                        {   "text": "⬅️ Назад️",
                            "callback_data": clb_back
                        },
                        {   "text": "Смотреть ⏯",
                            "url": url_video
                        },
                        {
                            "text": "Вперед ➡️",
                            "callback_data": clb_forward
                        }],
                        [{
                            "text": "скачать PDF",
                            "url": url_pdf
                        },
                        {   "text": txt_pass,
                            "callback_data": clb_hw
                        },
                        {
                            "text": txt_score,
                            "callback_data": "123"
                        }
                    ]
                ]
            }
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}

// функция листает учебные карточки
function editMessageResponce(id, photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score) {
    var token = $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().request.data.chatId;
    $jsapi.context().response.replies = $jsapi.context().response.replies || [];
    $jsapi.context().response.replies.push({
        "type": "raw",
            "body": {
            "chat_id": chat_id,
            "message_id": id,
            "media":{
                "type": "photo",
                "media": photo,
                "caption": caption,
                },
            "reply_markup":{
                    "inline_keyboard":[[
                        {   "text": "⬅️ Назад️",
                            "callback_data": clb_back
                        },
                        {   "text": "Смотреть ⏯",
                            "url": url_video
                        },
                        {
                            "text": "Вперед ➡️",
                            "callback_data": clb_forward                
                        }],
                        [{
                            "text": "скачать PDF",
                            "url": url_pdf
                        },
                        {   "text": txt_pass,
                            "callback_data": clb_hw
                        },
                        {
                            "text": txt_score,
                            "callback_data": "Ok"                
                        }
                    ]
                    ]
                },
            },   
            "method": "editMessageMedia"
    });
    return true;
}

// установка переменных
function setVariable() {
    var session = $jsapi.context().session;
    var client = $jsapi.context().client;
    session.score = 0;
    session.step = 1;
    session.txt_score1 = 0;
    session.txt_score2 = 0;
    session.txt_score3 = 0;
    session.txt_score4 = 0;
    session.txt_score5 = 0;
    session.txt_score6 = 0;
    session.txt_score7 = 0;
    session.txt_score8 = 0;
    session.txt_pass1 = "Сдать ДЗ";
    session.txt_pass2 = "Сдать ДЗ";
    session.txt_pass3 = "Сдать ДЗ";
    session.txt_pass4 = "Сдать ДЗ";
    session.txt_pass5 = "Сдать ДЗ";
    session.txt_pass6 = "Сдать ДЗ";
    session.txt_pass7 = "Сдать ДЗ";
    session.txt_pass8 = "Сдать ДЗ";
}

// функция отправки сообщений ментору + кнопка с ответом себе в чат
function sendMessageWithButton(message) {
    var id = $jsapi.context().injector.mentor_chat_id;
    var token =  $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendMessage";
    var options = {
        body: {
            "chat_id": id,
            "text": message,
            "reply_markup":{
                "inline_keyboard":[
                    [
                        {
                            "text": "Отлично",
                            "callback_data": "Отлично"
                        },
                        {
                            "text": "Хорошо",
                            "callback_data": "Хорошо"
                        }],
                        [{
                            "text": "Неплохо",
                            "callback_data": "Неплохо"
                        },
                        {
                            "text": "Переделать",
                            "callback_data": "Переделать"
                        }
                    ]
                ]
            }
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}

//функция отправки сообщений куратору
function sendMessageToMentor(message) {
    var id = $jsapi.context().injector.mentor_chat_id;
    var token =  $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendMessage";
    var options = {
        dataType: "json",
        body: {
            "chat_id": id, 
            "text": message 
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}
// функция меняет кнопки в группе кураторов, показывая, что работу уже проверили
function hw_verified(id2) { 
    var token =  $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().injector.mentor_chat_id
    var url = "https://api.telegram.org/bot"+token+"/editMessageReplyMarkup";
    var options = {
        body: {
            "chat_id": chat_id,
            "message_id": id2,
//            "text": message,
            "reply_markup":{
                "inline_keyboard":[
                    [
                        {
                            "text": "Переоценить",
                            "callback_data": "Re_Verified"
                        }
                    ]
                ]
            }
        }
    };
    var response = $http.post(url, options); 
    return response.isOk ? response.data : false;
}
// функция возвращает кнопки в группе кураторов после нажатия №переоценить"
function re_verified(id2) { 
    var token =  $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().injector.mentor_chat_id
    var url = "https://api.telegram.org/bot"+token+"/editMessageReplyMarkup";
    var options = {
        body: {
            "chat_id": chat_id,
            "message_id": id2,
//            "text": message,
            "reply_markup":{
                "inline_keyboard":[
                    [
                        {
                            "text": "Отлично",
                            "callback_data": "Отлично"
                        },
                        {
                            "text": "Хорошо",
                            "callback_data": "Хорошо"
                        }],
                        [{
                            "text": "Неплохо",
                            "callback_data": "Неплохо"
                        },
                        {
                            "text": "Переделать",
                            "callback_data": "Переделать"
                        }
                    ]
                ]
            }
        }
    };
    var response = $http.post(url, options); 
    return response.isOk ? response.data : false;
}

//функция удаления сообщений  из чата
function deleteMessegeFromChat(msgId) {
    var token =  $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().request.data.chatId;
    var url = "https://api.telegram.org/bot"+token+"/deleteMessage";
    var options = {
        dataType: "json",
        body: {
            "chat_id": chat_id,
            "message_id": msgId
            }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}

//функция отправки сообщений + кнопка себе в чат
function sendMessageToUser(user_id, message, clb_data) {
//    var id = $jsapi.context().injector.mentor_chat_id;
    var token =  $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendMessage";
    var options = {
        dataType: "json",
        body: {
            "chat_id": user_id, 
            "text": message,
            "reply_markup":{
                "inline_keyboard":[
                    [
                        {
                            "text": "OK",
                            "callback_data": clb_data
                        }
                    ]
                ]
            }            
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}

// функция убирает кнопку "ОК" после нажатия
function noButtonOk(id3) { 
    var token =  $jsapi.context().injector.bot_token;
    var chat_id = $jsapi.context().request.data.chatId;
    var url = "https://api.telegram.org/bot"+token+"/editMessageReplyMarkup";
    var options = {
        body: {
            "chat_id": chat_id,
            "message_id": id3,
            "reply_markup":{
            }
        }
    };
    var response = $http.post(url, options); 
    return response.isOk ? response.data : false;
}

//функция отправки сообщений из бота по ID получателя
function sendMessageByID(sendID, messageFor) {
    var token =  $jsapi.context().injector.bot_token;
    var url = "https://api.telegram.org/bot"+token+"/sendMessage";
    var options = {
        dataType: "json",
        body: {
            "chat_id": sendID, 
            "text": messageFor,
            "reply_markup":{
                "inline_keyboard":[
                    [
                        {
                            "text": "OK",
                            "callback_data": "NoOK"
                        }
                    ]
                ]
            }                   
        }
    };
    var response = $http.post(url, options);
    return response.isOk ? response.data : false;
}