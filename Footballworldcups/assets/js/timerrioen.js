
function timercountxx() {

    var x1day = new Date();
    var x1second = Math.floor(Date.UTC(2014, 5, 12, 0, 0, 0) / 1000);
    var x1nowsecond = Math.floor(x1day.getTime() / 1000);
    var x1timeofset = 180;
    x1nowsecond = x1nowsecond - (x1timeofset * 60);
    var x1_nisu = (x1second - x1nowsecond);
    var x1_nisu2 = (x1second - x1nowsecond) / 60;
    var x1_nisu3 = (x1second - x1nowsecond) / 3600;
    var x1_nisu4 = (x1second - x1nowsecond) / (86400);
    var x1_date = Math.floor(x1_nisu4);
    var x1_hour = Math.floor(x1_nisu3) - (x1_date * 24);
    var x1_minite = Math.floor(x1_nisu2) - (((x1_date * 24) * 60) + (x1_hour * 60));
    var x1_second = Math.floor(x1_nisu) - (((((x1_date * 24) * 60) + (x1_hour * 60)) * 60) + x1_minite * 60);
    var x1insertstr = "";
    if (x1_date > 0) {
        x1insertstr = x1insertstr + x1_date + "<span class=\"days\">days</span>";
    }
    if (x1_hour > 0 || x1_nisu > 86400) {
        x1insertstr = x1insertstr + x1_hour + "<span class=\"days\">hours</span>";
    }
    if (x1_minite > 0 || x1_nisu > 3600) {
        x1insertstr = x1insertstr + x1_minite + "<span class=\"days\">min</span>";
    }
    x1insertstr = x1insertstr + x1_second + "<span class=\"days\">sec</span>";
    if (x1_nisu < 1) {
        x1insertstr = "0<span class=\"days\">days</span>0<span class=\"days\">hours</span>0<span class=\"days\">min</span>0<span class=\"days\">sec</span>";
    }

    document.getElementById('counttimerx').innerHTML = '<span class="days">Days Left:</span>' + x1insertstr;
}


function starttimerxx() {
    timercountxx();
    setInterval("timercountxx()", 1000);

}


var UAUA = 0;
var UA = navigator.userAgent;
if (UA.indexOf('iPhone') > -1 || UA.indexOf('iPod') > -1 || UA.indexOf('Android') > -1) {
    document.write('<link rel="stylesheet" href="../mobile.css">');
    UAUA = 1;
}

