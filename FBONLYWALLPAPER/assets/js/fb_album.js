var fb_url = 'http://graph.facebook.com/JenniferLawrenceFansPage/photos/uploaded';
function alert_pop_up(id, alert_text) {
    $.mobile.loading('hide');
    if (alert_text !== '')
        $('#' + id + '_text').html(alert_text);
    $('#' + id).popup('open');
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
}
$(function() {
    $(window).bind("pagecontainercreate", function(event) {
        $.mobile.firstPage = $("#index");
    });
    $('#index').on('pagebeforeshow', function(event, ui) {
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
    $('#nextbtn').bind('tap', function(event, ui) {
        gonext();
    });

    $('#prevbtn').bind('tap', function(event, ui) {
        goprev();
    });
});
