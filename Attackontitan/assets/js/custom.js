var obTrivia = new Trivia();  // object of the Trivia class
var feed_url = 'https://gdata.youtube.com/feeds/api/videos?q=spanish+video+song&orderby=published';
var fb_url = 'http://graph.facebook.com/Cristiano/photos/uploaded';
var YoutubeTimerID;
var quiz_id = 1;
var player_ready = false;
var player;
var playlist_loaded = false;
var videoID;
var dbms = {
    initialize_db: function() {
        var context = this,
                questions = context.get('questions'),
                countdown = context.get('countdown'),
                level = context.get('level');
        if (questions === undefined) {
            questions = getQuestionFromServer('dbms');
        }
        if (countdown === undefined) {
            context.set('countdown', parseInt($('#timer').val()));
        } else {
            $('#timer option').each(function(index, val) {
                $(this).prop('selected', false);
            });
            $('#timer option[value="' + countdown + '"]').prop('selected', true);
        }
        if (isNaN(level)) {
            context.set('level', parseInt($('.level:checked').val()));
        } else {
            $('.level').each(function(index, val) {
                if ($(this).val() == level) {
                    $(this).prop('checked', true);
                } else {
                    $(this).prop('checked', false);
                }
            });
        }
    },
    get: function(field) {
        return localStorage[field];
    },
    set: function(field, value) {
        localStorage[field] = value;
    }
};
var tag = document.createElement('script');

tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
var viewportwidth = $(window).width();
var viewportheight = $(window).height() - 42;
if (viewportheight > viewportwidth) {
    viewportheight = Math.floor(viewportwidth / 1.777777778);
} else {
    viewportwidth = Math.floor(viewportheight * 1.777777778);
}
function onYouTubeIframeAPIReady() {
    player_ready = true;
}
function trySetupPlayer() {
    if (player_ready) {
        player = new YT.Player('player', {
            height: viewportheight,
            width: viewportwidth,
            videoId: videoID
        });
    }
    player_loaded = true;
    $.mobile.loading('hide');
    clearInterval(YoutubeTimerID);
}

function alert_pop_up(id, alert_text) {
    $.mobile.loading('hide');
    if (alert_text !== '')
        $('#' + id + '_text').html(alert_text);
    $('#' + id).popup('open');
}
function getQuestionFromServer(call_from) {
    if (navigator.onLine) {
        $.ajax({
            url: 'http://apps.despicableapps.com/quiz/api/index?callback=?',
            type: "POST",
            data: {func_id: 1, quiz_id: quiz_id},
            dataType: "jsonp",
            jsonpCallback: "localJsonpCallback",
            success: function(response) {
                if (XMLHttpRequest.status = 0) {
                    alert_pop_up('alert_box', 'Failed to Load Question. Please Try Letter.');
                } else {
                    dbms.set('questions', response);
                    if (call_from == 'update') {
                        alert_pop_up('alert_box', 'Question Updated Succesfully');
                    }
                }
            },
            error: function(response) {
                alert_pop_up('alert_box', 'Failed to Load Question. Please Try Letter.');
            }
        });
    } else {
        var msg = (call_from == 'dbms') ? 'to start. Internet needs only 1st time' : 'to update questions.';
        alert_pop_up('alert_box2', "Please connect your device to Internet " + msg + ".");
    }
}
function show_answers() {
    $('#quiz_answers').popup('open');
}
function start_loading() {
    $(window).bind("pagecontainercreate", function(event) {
        $.mobile.firstPage = $("#index");
    });
    $('#index').on('pagebeforeshow', function(event, ui) {

    });
    $('#quiz').on('pagebeforeshow', function(event, ui) {
        var questions = $.parseJSON(dbms.get('questions'));
        var total_question = questions.length;
        var selected_questions = new Array(10);
        var i = 0, random_num, selected = [];
        while (1) {
            random_num = Math.floor(Math.random() * total_question);
            if (i == 0) {
                selected_questions[i] = questions[random_num];
                selected.push(random_num);
                i++;
            } else {
                if (selected.indexOf(random_num) === -1) {
                    selected_questions[i] = questions[random_num];
                    selected.push(random_num);
                    i++;
                }
            }
            if (i == 10)
                break;
        }
        //console.log(selected_questions);
        dbms.set('current_sesion_question', selected_questions);
        obTrivia.resetTrivia();
        obTrivia.setQuizJSON(selected_questions);
        obTrivia.sQuiz('start');
    });

    //page videw_gallery
    $('#video_gallery').on('pagebeforeshow', function(event, ui) {
        if (navigator.onLine) {
            $('.playlist .no-data').remove();
            $.mobile.loading('show');
            $.jQRSS(feed_url, {count: 25}, function(newsFeed) {
                var playList = $(".playlist");
                if (!newsFeed) {
                    if ($('.playlist li').length == 0) {
                        $("<li />", {'class': 'no-data'}).html('No Video in Feed').appendTo(playList);
                        playList.listview("refresh");
                    }
                    return false;
                }
                playList.empty();
                for (var i = 0; i < newsFeed.entries.length; i++) {
                    var entry = newsFeed.entries[i];

                    var categories = entry['categories'] ? entry['categories'] : new Array(),
                            content = entry['content'] ? entry['content'] : "",
                            contentSnippet = entry['contentSnippet'] ? entry['contentSnippet'] : "",
                            link = entry['link'] ? entry['link'] : "",
                            publishedDate = entry['publishedDate'] ? entry['publishedDate'] : "",
                            title = entry['title'] ? entry['title'] : "";

                    var patt = new RegExp(' [0-9][0-9]:[0-9][0-9]:[0-9][0-9] [-|+][0-9][0-9][0-9][0-9]');
                    var video_id = link.split('?')[1].split('&')[0].split('=')[1];
                    $li = $("<li />").appendTo(playList);
                    $ancor = $("<a />", {href: '#youtube_video', 'data-videoid': video_id}).appendTo($li);
                    $('<img />', {src: ('https://i.ytimg.com/vi/' + video_id + '/mqdefault.jpg')}).appendTo($ancor);
                    $('<h3 />').html(title).appendTo($ancor);
                    $('<p />').html(publishedDate.replace(patt, "")).appendTo($ancor);
                }
                playList.listview("refresh");
                $(".playlist a").bind('tap', function() {
                    videoID = $(this).data('videoid');
                });
                $.mobile.loading('hide');
            });
        } else {
            var playList = $(".playlist");
            if ($('.playlist li').length == 0) {
                $("<li />", {'class': 'no-data'}).html('No Internet Connection Available').appendTo(playList);
                playList.listview("refresh");
            }
        }
    });
    //end page video_gallery

    //start page youtube_video
    $('#youtube_video').on('pagebeforeshow', function(event, ui) {
        $.mobile.loading('show');
        if (player_ready) {
            player = new YT.Player('player', {
                height: viewportheight,
                width: viewportwidth,
                videoId: videoID
            });
            $.mobile.loading('hide');
        } else {
            YoutubeTimerID = setInterval('trySetupPlayer()', 500);
        }
        $('#direct_play').prop('href', 'http://www.nativeplay.com?v_id=' + videoID);
        //player.loadVideoById(videoID);
    }).on('pagebeforehide', function(event, ui) {
        player.destroy();
    });
    //end page youtube_video

    //start_page add_question
    $('#add_question').on('pagebeforeshow', function(event, ui) {
        $('#add_question_form').find('input[type="text"]').val('');
        var validator = $('#add_question_form').validate({
            messages: {
                email: 'Invalid Email'
            }
        });
        $('#add_question_form').submit(function() {
            if (navigator.onLine) {
                if ($('#add_question_form').valid() && $.trim($('#ia2').val()) != '' && $.trim($('#ia3').val()) != '') {
                    $.mobile.loading('show');
                    var $data = {
                        'func_id': 2,
                        'quiz_id': quiz_id,
                        'email': $('#email').val(),
                        'que': $('#que').val(),
                        'ca': $('#ca').val(),
                        'ia': [$('#ia1').val(), $('#ia2').val(), $('#ia3').val()]
                    };
                    $.ajax({
                        url: 'http://apps.despicableapps.com/quiz/api/index',
                        type: 'POST',
                        contentType: "application/x-www-form-urlencoded",
                        data: $data,
                        success: function(response) {
                            alert_pop_up('alert_box2', 'Question Submitted! Waiting For Approval.');
                            $('#add_question_form').find('input[type="text"]').val('');
                        },
                        error: function(response) {
                            alert_pop_up('alert_box2', 'Question Submitted! Waiting For Approval.');
                            $('#add_question_form').find('input[type="text"]').val('');
                        }
                    });
                } else {
                    validator.element('#ia2, #ia3');
                    validator.element('#ia3');
                }
            } else {
                alert_pop_up('alert_box2', "Please connect your device to Internet.");
            }
            return false;
        });
    });
    //end page add_question

    //start page image_gallery
    $('#image_gallery').on('pagebeforeshow', function(event, ui) {
        if ($(".image_list a").length == 0) {
            if (navigator.onLine) {
                load_images(fb_url);
                $('.load_new_img').bind('tap', function() {
                    if (!$(this).hasClass('ui-disabled')) {
                        load_images($(this).attr('href'));
                    }
                    return false;
                });
            } else {
                var imageList = $(".image_list");
                if ($('.playlist li').length == 0) {
                    $("<li />", {'class': 'no-data'}).html('No Internet Connection Available').appendTo(imageList);
                    imageList.listview("refresh");
                }
            }
        }
    });

    $.mobile.initializePage();
}
function load_images(url) {
    $('.image_list .no-data').remove();
    $.mobile.loading('show');
    $.ajax({
        url: url,
        dataType: 'jsonp',
        success: function(data) {
            var imageList = $(".image_list");
            var $totalImg = data.data.length;
            imageList.empty();
            $.each(data.data, function(k2, pictureObject) {
                var $a = $('<a />').prop({
                    href: '#popupImage',
                    'data-rel': 'popup',
                    'data-position-to': 'window',
                    'data-transition': 'flip'
                }).appendTo(imageList);
                $('<img />').prop({src: pictureObject.picture}).attr({'data-large-img': pictureObject.images[0].source}).appendTo($a);
                if (k2 === 0)
                    $a.addClass('first');
                if ($totalImg == (k2 + 1))
                    $a.addClass('last');
            });
            if (data.paging.previous !== undefined) {
                $('.load_new_img.previous').removeClass('ui-disabled');
                $('.load_new_img.previous').prop({href: data.paging.previous});
            } else {
                $('.load_new_img.previous').addClass('ui-disabled');
                $('.load_new_img.previous').prop({href: '#'});
            }
            if (data.paging.next !== undefined) {
                $('.load_new_img.next').removeClass('ui-disabled');
                $('.load_new_img.next').prop({href: data.paging.next});
            } else {
                $('.load_new_img.next').addClass('ui-disabled');
                $('.load_new_img.next').prop({href: '#'});
            }
            $('.image_list a').bind('tap', function(event, ui) {
                var src = $(this).find('img').data("large-img");
                $('.popphoto').prop({src: src});
                $('#download_image').prop({href: 'http://www.download.com?url=' + encodeURI(src)});
                $('#share_image').prop({href: 'http://www.share.com?url=' + encodeURI(src)});
                $('#set_as_wallpaper').prop({href: 'http://www.setaswallpaper.com?url=' + encodeURI(src)});
                $('.image_list a').removeClass('selectedimg');
                $(this).addClass('selectedimg');
                $(".popphoto", "#popupImage").load(function() {
                    var height = $(this).height(),
                            width = $(this).width();
                    // Set height and width attribute of the image
                    $(this).prop({"height": height, "width": width});
                    // Open the popup
                    $("#popupImage").popup("open");
                    // Clear the fallback
                    clearTimeout(fallback);
                });
                var fallback = setTimeout(function() {
                    $('#popupImage').popup('open');
                }, 2000);
                return false;
            });
            // Set a max-height to make large images shrink to fit the screen.
            $(document).on("popupbeforeposition", "#popupImage", function() {
                // 68px: 2 * 15px for top/bottom tolerance, 38px for the header.
                var maxHeight = $(window).height() - 68 + "px";

                $("img.popphoto", this).css("max-height", maxHeight);
            });

        },
        complete: function() {
            if (jQuery.active !== 0) {
                setTimeout(arguments.callee, 100);
                return;
            }
            $.mobile.loading('hide');
        }
    });
}
function resizeImageThumbs() {
    $('.image_list a img').each(function(index, val) {
        var $width = $(this).width(), $height = $(this).height();
        if ($width > $height) {
            $(this).css({height: '100%'});
        } else {
            $(this).css({'width': '100%'});
        }
    });
}
function get_style() {
    return 'width:' + (viewportwidth - 30) + 'px';
}
function gonext() {
    var current = $('a.selectedimg');
    if (current.hasClass('last')) {
        var next = $('a.first');
    } else {
        var next = current.next();
    }

    var src = next.find('img').data("large-img");
    next.addClass('selectedimg');
    current.removeClass('selectedimg');
    $('.popphoto').prop({src: src});
//    $('.popphoto').prop({style: get_style()});
}
function goprev() {
    var current = $('a.selectedimg');
    if (current.hasClass('first')) {
        var prev = $('a.last');
    } else {
        var prev = current.prev();
    }
    var src = prev.find('img').data("large-img");
    prev.addClass('selectedimg');
    current.removeClass('selectedimg');
    $('.popphoto').prop({src: src});
//    $('.popphoto').prop({style: get_style()});
}
$(function() {
    $.when(dbms.initialize_db()).then(start_loading());
    $('#update_quiz').bind('tap', function() {
        $.mobile.loading('show');
        getQuestionFromServer('update');
        return false;
    });
    $('#timer').change(function() {
        dbms.set('countdown', parseInt($(this).val()));
    });
    $('.level').bind('tap', function() {
        dbms.set('level', parseInt($(this).val()));
    });
    YoutubeTimerID = setInterval('trySetupPlayer()', 500);

//    $('#popupImage').width(viewportwidth - 40);
    $('#nextbtn').bind('tap', function(event, ui) {
        gonext();
    });
    $('#imgshow').bind('swipeleft', function(event, ui) {
        gonext();
    });

    $('#prevbtn').bind('tap', function(event, ui) {
        goprev();
    });
    $('#imgshow').bind('swiperight', function(event, ui) {
        goprev();
    });
});
