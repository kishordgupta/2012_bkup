<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <style>
            .fb-comments,
            .fb-comments span,
            .fb-comments iframe[style]{
                width: 100% !important;
            }
        </style>
    </head>
    <body>
        <script type="text/javascript">
            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=390455484394295";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <div id="cc" class="fb-comments" id="post_comments" data-href="http://wallpaper.lilait.com/big_bang_theory/gallery/<?php echo @$_GET['url']; ?>" data-num-posts="10"></div>
    </body>
</html>