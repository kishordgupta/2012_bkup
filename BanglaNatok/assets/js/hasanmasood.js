//var feed_url = 'https://gdata.youtube.com/feeds/api/videos?q=bangla+full+natok+-click+-https&orderby=published&format=1,6';
var feed_url = 'https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&q=bangla+full+natok+hasan+masud+-click+-https&type=video&videoDefinition=any&key=AIzaSyCWTvA2k2saWsHwIKx88s7rEKhvG8lFIJQ&maxResults=20';
var YoutubeTimerID;
var player_ready = false;
var player;
var playlist_loaded = false;
var videoID;
var tag = document.createElement('script');

function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}


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

function generatePlayList(video_feeds) {
    var playList = $(".playlist");
    if (!video_feeds) {
        if ($('.playlist li').length == 0) {
            $("<li />", {'class': 'no-data'}).html('No Video in Feed').appendTo(playList);
            playList.listview("refresh");
        }
        return false;
    }
    playList.empty();
    for (var i = 0; i < video_feeds.items.length; i++) {
        var entry = video_feeds.items[i];

        var publishedDate = entry.snippet.publishedAt;
        var title = entry.snippet.title;
        var video_id = entry.id.videoId;
        var patt = new RegExp('[0-9][0-9]:[0-9][0-9]:[0-9][0-9] [-|+][0-9][0-9][0-9][0-9]');
        
        $li = $("<li />").appendTo(playList);
        $ancor = $("<a />", {href: '#youtube_video', 'data-videoid': video_id}).appendTo($li);
        $('<img />', {src: entry.snippet.thumbnails.medium.url}).appendTo($ancor);
        $('<h3 />').html(title).appendTo($ancor);
        $('<p />').html(publishedDate.replace(patt, "")).appendTo($ancor);
    }
    playList.listview("refresh");
    $(".playlist a").bind('tap', function () {
        videoID = $(this).data('videoid');
    });
    $.mobile.loading('hide');

} //end of generatePlayList

$(function () {
    $(window).bind("pagecontainercreate", function (event) {
        $.mobile.firstPage = $("#index");
    });

    //page videw_gallery
    $('#index').on('pagebeforeshow', function (event, ui) {
        if (navigator.onLine) {

            $('.playlist .no-data').remove();
            $.mobile.loading('show');
            $.ajax({
                type: "GET",
                url: feed_url,
                dataType: 'jsonp',
                jsonpCallback: 'generatePlayList', // the function to call
                jsonp: 'callback', // name of the var specifying the callback in the request
                error: function (xhr, errorType, exception) {
                        var errorMessage = exception || xhr.statusText;
                        //alert("Excep:: " + exception + "Status:: " + xhr.statusText);
                    }
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
    $('#youtube_video').on('pagebeforeshow', function (event, ui) {
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
    }).on('pagebeforehide', function (event, ui) {
        player.destroy();
    });
    //end page youtube_video

    $.mobile.initializePage();
//    YoutubeTimerID = setInterval('trySetupPlayer()', 500);

});
