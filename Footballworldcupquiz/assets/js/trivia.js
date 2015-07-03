// Trivia class
function Trivia() {
    // http://coursesweb.net/
    var obth = this;       // stores the object contained in 'this'
    var objfiles = {'General': 'general_json.txt', 'Animals': 'animals_json.txt'};     // stores the object with "category":"file_name" elements, received in setCateg()
    var level = 'level1';  // 'level1' with variant of answers, 'level2' with text field to write the answer
    var stopct = 0;        // if not 0, countdown-timer not continue, in startCT()
    var ctsec = 15;         // number of seconds for each quiz
    var nquizzes = 0;      // total number of quizzes
    var nquiz = 0;         // stores number of current quiz
    var canswer = '';      // for correct answer of current quiz

    var nqansw = 0;       // number of answered quizzes
    var nca = 0;          // number of correct answers
    var nia = 0;          // number of incorrect answers

    var TimerID;
    var qctimer = localStorage['countdown'];         // for countdown-timer (if is 1), shown in #sctimer
    var iquizzes = [];       // array with indexes of quizzes that must to get randomly

    // property /array stores the trivia elements ('que'=question, 'ca'=correct answer, 'ia'=array with incorrect answers)
    var quizzes = [];

    // this public function gets the JSON object and stores it in quizzes property
    // receives the property name that has the file to access, in objfiles object
    this.setQuizJSON = function(tobj) {
        obth.resetTrivia();
        quizzes = [];
        iquizzes = [];
        quizzes = tobj;
        nquizzes = quizzes.length; // total number of quizzes

        // adds in #totalq the total number of quizzes
        $('#ntotalq').html(nquizzes);
    };

    // creates the countdown timer
    var startCT = function() {
        // if ctsec is > 1
        if (ctsec >= 0) {
            // if countdown-timer not stoped (stopct is 0)
            if (stopct == 0 && document.getElementById('sctimer')) {
                document.getElementById('sctimer').innerHTML = ctsec;    // shows the countdown timer
                TimerID = setTimeout(startCT, 1000);        // Auto-calls the function after 1 second
            }
            ctsec--;         // decreases the value with one unit
        } else {
            obth.getAnswer(0);       // to show the correct answer
        }
    };

    // sets the array with quizzes index, returns a random index (stn is for 'start', or 'next')
    var setIQrandom = function(stn) {
        // if stn is 'start', sets iquizzes from beginning, else, removes the element with current quiz number from iquizzes
        if (stn == 'start') {
            for (var i = 0; i < nquizzes; i++) iquizzes[i] = i;
        } else {
            // gets the index of item with value of number in nquiz, and removes it
            var nqr = iquizzes.length;     // number of quizzes index in iquizzes
            for (var i = 0; i < nqr; i++) {
                if (iquizzes[i] == nquiz) {
                    iquizzes.splice(i, 1);
                    break;
                }
            }
            iquizzes.sort();       // resorts the array
        }
        return iquizzes[Math.floor(Math.random() * iquizzes.length)];
    };

    // starts and shows the quiz, receives the way: 'start' or 'next' to set the index of current quiz
    this.sQuiz = function(stn) {
        nquiz = setIQrandom(stn);
        canswer = quizzes[nquiz]['ca'];       // stores correct answer

        if (document.getElementById('quiz_viewport')) {
            // to add countdown-timer if qctimer is different from 0
            (qctimer == 0) ? $('#sctimer').hide() : $('#sctimer').show();

            // increments the number of answered quizzes, calls answered() to display it
            nqansw++;
            answered();

            if (stn == 'next') {
                $('#qansw').fadeOut(200, function() {
                    $('#quiz_viewport').html('<h4 id="tquestion">' + quizzes[nquiz]['que'] +
                            '</h4><div id="qansw">' + qAnswerL1() + '</div>');
                    $('#qansw').fadeIn(200);
                    $('<h4 />').html('Q. ' + quizzes[nquiz]['que']).appendTo('.answer_viewport');
                    $('<p />').html('Ans: ' + canswer).appendTo('.answer_viewport');
                });
            } else {
                $('#quiz_viewport').html('<h4 id="tquestion">' + quizzes[nquiz]['que'] +
                        '</h4><div id="qansw">' + qAnswerL1() + '</div>');
            }
            // shows the quiz number, quiz question, and element for answer (according to level)

            //stn == 'next' ? $('#qansw').fadeIn() : '';
            // if qctimer is 1, sets the stopct and ctsec, calls the function for countdown-timer
            if (qctimer == 1) {
                stopct = 0;
                ctsec = 15;
                startCT();
            }
        }
    };

    // resets the trivia game, index-number of quiz, the value in #nquiz field, alerts message
    this.resetTrivia = function() {
        nquiz = 0;             // index-number of quiz
        qctimer = localStorage['countdown'];           // to disable countdown-timer

        // reset answered data
        nqansw = 0;
        nca = 0;
        nia = 0;
        answered();
        if (TimerID)
            clearTimeout(TimerID);

//        deBtns(['level1', 'qctimer', 'squiz'], 'enable');     // to enable buttons (disable Reset)
//        document.getElementById('quiz').innerHTML = '<h3>Welcome to Trivia Game</h3><strong>Levels:</strong><ul><li>Level 1 - Easy - It is displayed a list of answers to each quiz. Click the correct answer.</li><li>Level 2 - Difficult - It is displayed a text box in which you must write the answer, then click on Send button.</li></ul><strong>Mode</strong><ul><li>Consecutive - The quiestions start from the quiz with index number added into a text field, and are added in their order till the last quiz.</li><li>Random - The quizzes are choosed randomly, till the last quiz, without repeat.</li></ul>&bull; <strong>Countdown Timer</strong> - If that button is checked, you have 15 seconds to answer till the next quiz is added automatically.<br/><br/> - Click the Start button to start the quizzes. The Reset button resets the Trivia.<div id="tfrom">From: <a href="http://coursesweb.net/" title="Web Programming and Development Courses">http://CoursesWeb.net</a></div>';
    };

    // to disable /enable buttons when Start /Reset Trivia, receives an Array with IDs of buttons, and 'disable' or 'enable'
    var deBtns = function(ids, de) {
        var nrids = ids.length;
        for (var i = 0; i < nrids; i++) {
            (de == 'disable') ? $('#' + ids[i]).prop('disabled', true) : $('#treset').prop('disabled', false);
        }
        // removes 'disabled' from Reset (when start), or adds 'disabled' to Reset (when Reset)
        (de == 'disable') ? $('#treset').prop('disabled', false) : $('#treset').prop('disabled', true);
    };

    // sets and returns the variant of answers (for Level 1)
    var qAnswerL1 = function() {
        var qhtml = '<ul>';       // quiz question
        var nr_ia = quizzes[nquiz]['ia'].length;     // number of incorrect answers

        // random number used to set randomly the position of correct answer
        var randomnr = Math.floor(Math.random() * (nr_ia));

        // creates the options with answers
        for (var i = 0; i < nr_ia; i++) {
            // if 'i' = randomnr, adds correct answer, and decrease the index, else, incorrect answer
            if (i == randomnr) {
                qhtml += '<li class="qanswer" onclick="obTrivia.getAnswer(this)">' + canswer + '</li>';
                randomnr = -1;        // to not be again equal to i
                i--;
            }
            else
                qhtml += '<li class="qanswer" onclick="obTrivia.getAnswer(this)">' + quizzes[nquiz]['ia'][i] + '</li>';
        }

        return qhtml + '</ul>';
    };

    // adds data in #answered box, number of answered quizzes, correct and incorrect answers
    var answered = function() {
        $('#nqansw').html(nqansw);
    };

    // method called when an answer is clicked, receives the object with answer
    this.getAnswer = function(answ) {
        stopct = 1;           // to stop countdown-timer

        // sets the Correct /Incorrect, answ is 0 when auto-calls from startCT()
        if (document.getElementById('qansw')) {
            // gets the Answer, delete white-spaces and tags
            if (answ != 0)
                var qanswer = answ.innerHTML.replace(/^\s+|\s+$/g, '');
            var seeansw = '';
            // sets Correct or Incorrect answer
            if (answ == 0) {
                nia++;       // incorrect answer
                seeansw = '<h4 id="iansw">Time expired</h4>'; // + '<h4 id="cansw">The Correct answer is:</h4><div id="canswer">' + canswer + '</div>';
            } else if (qanswer.toLowerCase() == canswer.toLowerCase()) {
                nca++;       // correct answer
                seeansw = '<div id="canswer">' + qanswer + '</div><h4 id="cansw">&gt; Correct</h4>';
            } else {
                nia++;       // incorrect answer
                seeansw = '<strike>' + qanswer + '</strike><h4 id="iansw">Incorrect</h4>'; //+ '<div id="canswer">' + canswer + '</div>';
            }

            answered();        // to show answered data

            // if 'qindex' mode and last quiz, adds message, else, adds Next Button
            console.log(iquizzes.length);
            var onclick_event = iquizzes.length == 1 ? 'obTrivia.show_result()' : "obTrivia.sQuiz('next')";
            var text = iquizzes.length == 1 ? 'Show Rresult' : "Next";
            seeansw += '<button id="nextq" class="ui-btn-left ui-btn ui-shadow ui-btn-corner-all ui-mini ui-btn-icon-left ui-btn-up-a" onclick="'
                    + onclick_event + '"><span class="ui-btn-inner"><span class="ui-btn-text">'
                    + text + '</span><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></span></button>';
            $('#nextq').button('refresh');
            $('#qansw').fadeOut(200, function() {
                $('#qansw').html(seeansw).fadeIn(200);
            });
        }
    };
    this.show_result = function() {
        $('#tquestion').fadeOut(200);
        $('#qansw').fadeOut(200, function() {
            var btn = '<button id="nextq" class="ui-btn-left ui-btn ui-shadow ui-btn-corner-all ui-mini ui-btn-icon-left ui-btn-up-a" onclick="show_answers();"><span class="ui-btn-inner"><span class="ui-btn-text">Show Answers</span><span class="ui-icon ui-icon-check ui-icon-shadow">&nbsp;</span></span></button>';
            $('#qansw').html('<h4 id="iansw">Correct Ans: ' + nca + '<br/>Incorrect Ans: ' + nia + '</h4>' + btn).fadeIn(200);
        });
    };

    this.cancel_quiz = function() {
        //console.log(iquizzes.length);
        if (iquizzes.length > 1) {
            $('#cancel_quiz').popup('open');
            return false;
        } else {
            return true;
        }
    };

    /* register events */
    // onclick on Start button
    $('#squiz').click(function() {
        obth.sQuiz('start');
    });
    // onclick on Reset button
    $('#treset').click(function() {
        obth.resetTrivia();
    });
}
