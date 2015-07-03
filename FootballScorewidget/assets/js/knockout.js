var C = {
    "BR": "Brazil",
    "AR": "Argentina",
    "CO": "Colombia",
    "UY": "Uruguay",
    "ES": "Spain",
    "DE": "Germany",
    "BE": "Belgium",
    "CH": "Switzerland",
    "JP": "Japan",
    "AU": "Australia",
    "IR": "Iran",
    "KR": "Korea Republic",
    "US": "USA",
    "CR": "Costa Rica",
    "HN": "Honduras",
    "MX": "Mexico",
    "CL": "Chile",
    "EC": "Ecuador",
    "GH": "Ghana",
    "CM": "Cameroon",
    "CI": "Côte d'Ivoire",
    "NG": "Nigeria",
    "DZ": "Algeria",
    "BF": "Burkina Faso*",
    "FR": "France",
    "NL": "Netherlands",
    "IT": "Italy",
    "BA": "Bosnia-Herzeg.",
    "UK": "England",
    "HR": "Croatia",
    "RU": "Russia",
    "UA": "Ukraine*",
    "GR": "Greece",
    "PT": "Portugal"
};
var ks = {
    "m49": ["1A2B", "2014-06-28", "13:00:+3", "", ""],
    "m50": ["1C2D", "2014-06-28", "17:00:+3", "", ""],
    "m53": ["1B2A", "2014-06-29", "13:00:+3", "", ""],
    "m54": ["1D2C", "2014-06-29", "17:00:+3", "", ""],
    "m51": ["1E2F", "2014-06-30", "13:00:+3", "", ""],
    "m52": ["1G2H", "2014-06-30", "17:00:+3", "", ""],
    "m55": ["1F2E", "2014-07-01", "13:00:+3", "", ""],
    "m56": ["1H2G", "2014-07-01", "17:00:+3", "", ""],
    "m57": ["1A2B1C2D", "2014-07-04", "17:00:+3", "", ""],
    "m58": ["1E2F1G2H", "2014-07-04", "13:00:+3", "", ""],
    "m59": ["1B2A1D2C", "2014-07-05", "17:00:+3", "", ""],
    "m60": ["1F2E1H2G", "2014-07-05", "13:00:+3", "", ""],
    "m61": ["1A2B1C2D1E2F1G2H", "2014-07-08", "17:00:+3", "", ""],
    "m62": ["1B2A1D2C1F2E1H2G", "2014-07-09", "17:00:+3", "", ""],
    "m63": ["3RD", "2014-07-12", "17:00:+3", "", ""],
    "m64": ["FINAL", "2014-07-13", "16:00:+3", "", ""]
},
timezoned = new Date(),
        timezone = timezoned.getTimezoneOffset() / 60,
        urlp = {};
$(function() {
    if (location.hash.match(/\#!(.+)/)) {
        var kv = RegExp.$1.split("&");
        for (i = 0, iLen = kv.length; i < iLen; i++) {
            var k = kv[i].split("=")[0];
            var v = kv[i].split("=")[1];
            urlp[k] = decodeURIComponent(v);
        }
    }
    if (urlp["m49"])
        $("#warning").show();
    for (i in urlp) {
        if (ks[i]) {
            ks[i][3] = urlp[i].split("-")[0];
            ks[i][4] = urlp[i].split("-")[1];
        }
    }

    for (i in ks) {
        if (ks[i][3] != "") {
            $("div.ks-" + ks[i][0] + " div.team:eq(0) span.f").html("<div class='flag-24 flag-24-" + ks[i][3] + "'></div>");
            $("div.ks-" + ks[i][0] + " div.team:eq(0) span.n").html(C[ks[i][3]]).addClass("tc-" + ks[i][3]);
            $("div.ks-" + ks[i][0] + " div.team:eq(0) span.ns").html(ks[i][3]).addClass("tc-" + ks[i][3]);
            $("div.ks-" + ks[i][0] + " div.team:eq(0) input").attr("data-tc", ks[i][3]);
        }
        if (ks[i][4] != "") {
            $("div.ks-" + ks[i][0] + " div.team:eq(1) span.f").html("<div class='flag-24 flag-24-" + ks[i][4] + "'></div>");
            $("div.ks-" + ks[i][0] + " div.team:eq(1) span.n").html(C[ks[i][4]]).addClass("tc-" + ks[i][4]);
            $("div.ks-" + ks[i][0] + " div.team:eq(1) span.ns").html(ks[i][4]).addClass("tc-" + ks[i][4]);
            $("div.ks-" + ks[i][0] + " div.team:eq(1) input").attr("data-tc", ks[i][4]);
        }
    }

    loadFixture();
    calc();

    $(document)
            .on("change", "input.timezone", function() {
                loadFixture();
            })
            .on("mouseup", "#t4 input", function() {
                $(this).select();
            })
            .on("change", "#t4 input", function() {
                calc();
            })
            .on("keyup", "#t4 input", function() {
                calc();
            });
});

function loadFixture() {
    for (i in ks) {
        var s = ks[i];
        if ($("input[name='timezone']:checked").val() == "y" && timezone != undefined) {
            var ts = +new Date(s[1].split("-")[0], s[1].split("-")[1] - 1, s[1].split("-")[2], s[2].split(":")[0], s[2].split(":")[1]);
            ts += s[2].split(":")[2] * 3600000;
            ts -= timezone * 3600000;
            var d = new Date(ts),
                    gdate = ("0" + (d.getMonth() + 1)).slice(-2) + "/" + ("0" + d.getDate()).slice(-2),
                    gtime = ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
        } else {
            var gdate = s[1].split("-")[1] + "/" + s[1].split("-")[2],
                    gtime = s[2].split(":")[0] + ":" + s[2].split(":")[1];
        }
        if (s[0] == "3RD" || s[0] == "FINAL")
            $("div.ks-" + s[0] + " div.dt").html(gdate + " " + gtime);
        else
            $("div.ks-" + s[0] + " div.dt").html(gdate + "&nbsp;<br>" + gtime);
    }
}

function clear() {
    $("#t4 input").val("");
    calc();
}

function calc() {
    $("#t4 div.ks").each(function() {
        var a = $(this).find("input:eq(0)");
        var b = $(this).find("input:eq(1)");
        var av = a.val();
        var bv = b.val();
        var ac = a.attr("data-tc");
        var bc = b.attr("data-tc");
        var ar = a.attr("data-rank");
        var br = b.attr("data-rank");
        var wtc = "";
        var wfl = "";
        var wnm = "";
        var ltc = "";
        var lfl = "";
        var lnm = "";
        if (av.match(/^\d+$/) && bv.match(/^\d+$/) && ac && bc && av != bv) {
            if (av > bv) {
                wtc = ac;
                ltc = bc;
            } else {
                wtc = bc;
                ltc = ac;
            }
            wfl = "<div class='flag-24 flag-24-" + wtc + "'></div>";
            lfl = "<div class='flag-24 flag-24-" + ltc + "'></div>";
            wnm = C[wtc];
            lnm = C[ltc];
        }
        $("#t4 div.ks-" + ar + br + "-w span.f").html(wfl);
        $("#t4 div.ks-" + ar + br + "-w span.n").html(wnm);
        $("#t4 div.ks-" + ar + br + "-w span.ns").html(wtc);
        $("#t4 div.ks-" + ar + br + "-w input").attr("data-tc", wtc);
        $("#t4 div.ks-" + ar + br + "-l span.f").html(lfl);
        $("#t4 div.ks-" + ar + br + "-l span.n").html(lnm);
        $("#t4 div.ks-" + ar + br + "-l span.ns").html(ltc);
        $("#t4 div.ks-" + ar + br + "-l input").attr("data-tc", ltc);
    });
}