function isNum(args) {
    args = args.toString();
    if (args.length === 0) {
        return false;
    }

    for (var i = 0; i < args.length; i++) {
        if (args.substring(i, i + 1) !== "-" && (args.substring(i, i + 1) < "0" || args.substring(i, i + 1) > "9")) {
            return false;
        }
    }
    return true;
}

function check_num(df) {
    var ad = df.value;
    if (!isNum(ad)) {
        if (ad != "") {
            ad = ad.substring(0, ad.length - 1);
        }
        df.value = ad;
    }
}

function change_page(url) {
    $.mobile.loading('show');
    setTimeout(function() {
        $.mobile.loading('hide');
    }, 1000);
    $.mobile.changePage(url, {transition: "slideup"});
}