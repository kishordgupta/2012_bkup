$(function() {
    $('#image_gallery').click(function() {
        $.ajax({
            url: 'http://graph.facebook.com/Cristiano/photos',
            dataType: 'jsonp',
            success: function(data) {
                $.each(data.data, function(k2, pictureObject) {
                    var $img = $('<img/>').prop({src: pictureObject.picture}).appendTo('body');
                });
            }
        });
    });

});