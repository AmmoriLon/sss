theme: /
    # в зависимости от того, за какой модуль выставили оценку, тот и появится на экране.
    state: Score
        script:
            $session.score = $session.txt_score1 + $session.txt_score2 + $session.txt_score3 + $session.txt_score4 + $session.txt_score5 + $session.txt_score6 + $session.txt_score7 + $session.txt_score8;
            if ($session.lesson == 1 || $session.hw == "01") {
                var photo = links["photo1"];
                var caption = comments["caption1"] + score[$session.score];
                var clb_back = "8";
                var clb_forward = "2";
                var url_video = links["video1"];
                var url_pdf = links["conspektus1"];
                var txt_pass = $session.txt_pass1;
                var clb_hw = "HW1";
                var txt_score = $session.txt_score1;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
                
            } else if ($session.lesson == 2 || $session.hw == "02") {
                var photo = links["photo2"];
                var caption = comments["caption2"] + score[$session.score];
                var clb_back = "1";
                var clb_forward = "3";
                var url_video = links["video2"];
                var url_pdf = links["conspektus2"];
                var txt_pass = $session.txt_pass2;
                var clb_hw = "HW2";
                var txt_score = $session.txt_score2;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
 
            } else if ($session.lesson == 3 || $session.hw == "03") { 
                var photo = links["photo3"];
                var caption = comments["caption3"] + score[$session.score];
                var clb_back = "2";
                var clb_forward = "4";
                var url_video = links["video3"];
                var url_pdf = links["conspektus3"];
                var txt_pass = $session.txt_pass3;
                var clb_hw = "HW3";
                var txt_score = $session.txt_score3;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);

            } else if ($session.lesson == 4 || $session.hw == "04") { 
                var photo = links["photo4"];
                var caption = comments["caption4"] + score[$session.score];
                var clb_back = "3";
                var clb_forward = "5";
                var url_video = links["video4"];
                var url_pdf = links["conspektus4"];
                var txt_pass = $session.txt_pass4;
                var clb_hw = "HW4";
                var txt_score = $session.txt_score4;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
                
            } else if ($session.lesson == 5 || $session.hw == "05") { 
                var photo = links["photo5"];
                var caption = comments["caption5"] + score[$session.score];
                var clb_back = "4";
                var clb_forward = "6";
                var url_video = links["video5"];
                var url_pdf = links["conspektus5"];
                var txt_pass = $session.txt_pass5;
                var clb_hw = "HW5";
                var txt_score = $session.txt_score5;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
                
            } else if ($session.lesson == 6 || $session.hw == "06") { 
                var photo = links["photo6"];
                var caption = comments["caption6"] + score[$session.score];
                var clb_back = "5";
                var clb_forward = "7";
                var url_video = links["video6"];
                var url_pdf = links["conspektus6"];
                var txt_pass = $session.txt_pass6;
                var clb_hw = "HW6";
                var txt_score = $session.txt_score6;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);                
                
            } else if ($session.lesson == 7 || $session.hw == "07") { 
                var photo = links["photo7"];
                var caption = comments["caption7"] + score[$session.score];
                var clb_back = "6";
                var clb_forward = "8";
                var url_video = links["video7"];
                var url_pdf = links["conspektus6"];
                var txt_pass = $session.txt_pass7;
                var clb_hw = "HW7";
                var txt_score = $session.txt_score7;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
                
            } else if ($session.lesson == 8 || $session.hw == "08") {
                var photo = links["photo8"];
                var caption = comments["caption8"] + score[$session.score];
                var clb_back = "7";
                var clb_forward = "1";
                var url_video = links["video8"];
                var url_pdf = links["conspektus8"];
                var txt_pass = $session.txt_pass8;
                var clb_hw = "HW8";
                var txt_score = $session.txt_score8;
                $temp.response = sendMessageToUserChatWithScore(photo, caption, clb_back, clb_forward, url_video, url_pdf, txt_pass, clb_hw, txt_score);
            }
            # проверка для первого старта, когда еще нечего удалять
            if ($session.message_id1) {
                var msgId = $session.message_id1;
                $session.message_id1 = $temp.response.result.message_id;
                $temp.response = deleteMessegeFromChat(msgId);
            } else {
                $session.message_id1 = $temp.response.result.message_id;
            }
            delete $session.lesson;
            delete $session.hw;
            