var feed_url = 'https://gdata.youtube.com/feeds/api/videos?q=cricket+highlights+-live+-click&orderby=published&format=1,6'
var YoutubeTimerID;
var player_ready = false;
var player;
var playlist_loaded = false;
var videoID;
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

$(function() {
    $(window).bind("pagecontainercreate", function(event) {
        $.mobile.firstPage = $("#index");
    });

    //page videw_gallery
    $('#index').on('pagebeforeshow', function(event, ui) {
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

    $.mobile.initializePage();
//    YoutubeTimerID = setInterval('trySetupPlayer()', 500);

});
