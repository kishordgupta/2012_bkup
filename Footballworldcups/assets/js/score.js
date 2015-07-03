var curg, inig = "a", rgl = {}, calcmode = 0, timezoned = new Date, timezone = timezoned.getTimezoneOffset() / 60;
var winWidth = $(window).width(),
        winHeight = $(window).height();

$(function() {
    loadGroup(inig);
    $(document).on("mouseup", "#t1 input, #t1s input", function() {
        $(this).select();
    }).on("keyup", "#t1 input", function() {
        var tr_index = $(this).parents('tr').index(),
                this_index = $(this).index();
        $('#t1s tr:eq(' + tr_index + ') input:eq(' + this_index + ')').val($(this).val());
        calc();
    }).on("keyup", "#t1s input", function() {
        var tr_index = $(this).parents('tr').index(),
                this_index = $(this).index();
        $('#t1 tr:eq(' + tr_index + ') input:eq(' + this_index + ')').val($(this).val());
        calc();
    }).on("change", "#t1ope .timezone", function() {
        t1load();
    });
});
loadGroup = function(e) {
    $("#group a").removeClass("slct");
    $("#group a#g" + e).addClass("slct");
//    $("#group").css("border-bottom-color", $("#group a#g" + e).css("border-bottom-color"));
    curg = e;
    t1load();
    calc();
};
t1load = function(e) {
    $("#t1, #t1s").empty();
    for (i = 0; i < scdl[curg].length; i++) {
        var a = scdl[curg][i];
        e ? (sc1 = a[5], sc2 = a[6]) : (sc1 = null == a[7] ? a[5] : a[7], sc2 = null == a[8] ? a[6] : a[8]);
        if ("y" == $("input[name='timezone']:checked").val() && void 0 != timezone) {
            var b = +new Date(a[1].split("-")[0],
                    a[1].split("-")[1] - 1,
                    a[1].split("-")[2],
                    a[2].split(":")[0],
                    a[2].split(":")[1]),
                    b = b + 36E5 * a[2].split(":")[2],
                    b = b - 36E5 * timezone,
                    d = new Date(b),
                    b = d.getFullYear() + "-" +
                    ("0" + (d.getMonth() + 1)).slice(-2) + "-" +
                    ("0" + d.getDate()).slice(-2), d = ("0" + d.getHours()).slice(-2) +
                    ":" + ("0" + d.getMinutes()).slice(-2);
        }
        else {
            b = a[1], d = a[2].split(":")[0] + ":" + a[2].split(":")[1];
        }

//        if (winWidth < 650) {
        $("#t1s").append(
                "<tr>" +
//                "<th>" + a[0] + "</th>" +
                "<td class='d'>" + b + " &nbsp; " + d + "<br/>" + a[9] + "</td>" +
                "<td class='team'>" + C[team[a[3]].tc].n + "<div class='flag-24 flag-24-" + team[a[3]].tc + "'></div><br/><div class='flag-24'>VS</div><br/>" +
                C[team[a[4]].tc].n + "<div class='flag-24 flag-24-" + team[a[4]].tc + "'></div></td>" +
                "<td class='score'><input onkeyup=\"check_num(this)\" type='text' maxlength=1 data-team='" + a[3] + "' value='" + sc1 + "' pattern='[0-9]*' class='scorebox1'>" +
                "<input onkeyup=\"check_num(this)\" type='text' maxlength=1 data-team='" + a[4] + "' value='" + sc2 + "' pattern='[0-9]*' class='scorebox2'></td>"
                );
//        } else {
        $("#t1").append(
                "<tr>" +
//                "<th>" + a[0] + "</th>" +
                "<td class='d'>" + b + "</td>" +
                "<td class='t'>" + d + "</td>" +
                "<td class='v'>" + a[9] + "</td>" +
                "<td class='team rtxt'><div class='flag-24 flag-24-" + team[a[3]].tc + "'></div>" + C[team[a[3]].tc].n + "</td>" +
                "<td class='score ctxt'><input onkeyup=\"check_num(this)\" type='text' maxlength=1 data-team='" + a[3] + "' value='" + sc1 + "' pattern='[0-9]*'>\uff0d" +
                "<input onkeyup=\"check_num(this)\" type='text' maxlength=1 data-team='" + a[4] + "' value='" + sc2 + "' pattern='[0-9]*'>" + "</td>" +
                "<td class='team ltxt'><div class='flag-24 flag-24-" + team[a[4]].tc + "'></div>" + C[team[a[4]].tc].n + "</td>"
                );
//        }
    }
//    var f;
//    $("#t1 tbody tr").each(function(a, b) {
//        $(this).children("th").html() == f ? $(this).children("th").remove() : f = $(this).children("th").html();
//        c = 1;
//        for (j = a + 1; j < $("#t1 tbody tr").size() && $(this).children("th").html() == $("#t1 tbody tr:eq(" + j + ")").children("th").html(); j++)
//            c++;
//        $(this).children("th").attr("rowspan", c)
//    });
};
t2load = function() {
    group[curg].sort(function(a, b) {
        return team[b].f - team[a].f;
    });
    group[curg].sort(function(a, b) {
        return team[b].f - team[b].a - (team[a].f - team[a].a);
    });
    group[curg].sort(function(a, b) {
        return team[b].pts - team[a].pts;
    });
    0 == calcmode ? (group[curg].sort(function(a, b) {
        return team[a].pts == team[b].pts && team[a].f - team[a].a == team[b].f - team[b].a && team[a].f == team[b].f ? rgl[b + "x" + a + "f"] - rgl[b + "x" + a + "a"] - (rgl[a + "x" + b + "f"] - rgl[a + "x" + b + "a"]) : 0
    }), group[curg].sort(function(a, b) {
        return team[a].pts == team[b].pts &&
                team[a].f - team[a].a == team[b].f - team[b].a && team[a].f == team[b].f ? rgl[b + "x" + a + "k"] - rgl[a + "x" + b + "k"] : 0
    })) : 1 == calcmode && (group[curg].sort(function(a, b) {
        return team[a].pts == team[b].pts ? rgl[b + "x" + a + "f"] - rgl[b + "x" + a + "a"] - (rgl[a + "x" + b + "f"] - rgl[a + "x" + b + "a"]) : 0
    }), group[curg].sort(function(a, b) {
        return team[a].pts == team[b].pts ? rgl[b + "x" + a + "k"] - rgl[a + "x" + b + "k"] : 0
    }));
    $("#t2 tbody").empty();
    for (i = 0; i < group[curg].length; i++) {
        var e = team[group[curg][i]];
        $("#t2 tbody").append("<tr class='" + group[curg][i] + "'><th><div class='flag-24 flag-24-" +
                e.tc + "'></div><span class='full_country'>" + C[e.tc].n + "</span><span class='country_code'>" + e.tc + "</span></td>" +
                "<td class='btxt'>" + e.pts + "</td><td>" + e.p + "</td><td>" + e.w + "</td><td>" + e.d + "</td><td>" + e.l + "</td><td>" + e.f + "</td><td>" + e.a + "</td><td>" + (e.f - e.a) + "</td>");
    }
    t3load();
};
t3load = function() {
    var e = 0;
    for (i = 0; i < group[curg].length; i++)
        e += team[group[curg][i]].p;
    if (e == 2 * scdl[curg].length) {
        $("#t3 #" + curg + "1").fadeOut(100, function() {
            $(this).html("<div class='flag-24 flag-24-" + team[group[curg][0]].tc + "'></div>" + C[team[group[curg][0]].tc].n).fadeIn(300)
        }), $("#t3 #" + curg + "2").fadeOut(100, function() {
            $(this).html("<div class='flag-24 flag-24-" + team[group[curg][1]].tc + "'></div>" + C[team[group[curg][1]].tc].n).fadeIn(300)
        })
    } else {
        $("#t3 #" + curg + "1").html("1" + curg.toUpperCase()), $("#t3 #" +
                curg + "2").html("2" + curg.toUpperCase());
    }
    var e = ["m49=" + team[group.a[0]].tc + "-" + team[group.b[1]].tc,
        "m50=" + team[group.c[0]].tc + "-" + team[group.d[1]].tc,
        "m51=" + team[group.e[0]].tc + "-" + team[group.f[1]].tc,
        "m52=" + team[group.g[0]].tc + "-" + team[group.h[1]].tc,
        "m53=" + team[group.b[0]].tc + "-" + team[group.a[1]].tc,
        "m54=" + team[group.d[0]].tc + "-" + team[group.c[1]].tc,
        "m55=" + team[group.f[0]].tc + "-" + team[group.e[1]].tc,
        "m56=" + team[group.h[0]].tc + "-" + team[group.g[1]].tc],
            a = !0;
    for (i in team)
        if (3 != team[i].p) {
            a = !1;
            break
        }
    a ? $("#Knockoutstage").attr("href", "http://apps.lilait.com/fifa_android/knockout.html?" + e.join("&")).slideDown(200) : $("#Knockoutstage").slideUp(200)
};
calc = function() {
    initteam();
    initrgl();
    var e = 0;
    $("#t1 tr").each(function() {
        var a = $(this).find("input:eq(0)"), b = $(this).find("input:eq(1)"), d = a.data("team"), f = b.data("team"), a = a.val(), b = b.val();
        scdl[curg][e][7] = a;
        scdl[curg][e++][8] = b;
        a.match(/^\d+$/) && b.match(/^\d+$/) && (a = eval(a), b = eval(b), team[d].p++, team[f].p++, team[d].f += a, team[f].f += b, team[d].a += b, team[f].a += a, rgl[d + "x" + f + "f"] += a, rgl[f + "x" + d + "f"] += 2 * b, rgl[d + "x" + f + "a"] += b, rgl[f + "x" + d + "a"] += a, a > b ? (team[d].w++, team[f].l++, team[d].pts += 3, rgl[d + "x" + f +
                "k"] += 3) : a < b ? (team[d].l++, team[f].w++, team[f].pts += 3, rgl[f + "x" + d + "k"] += 3) : (team[d].d++, team[f].d++, team[d].pts++, team[f].pts++, rgl[d + "x" + f + "k"]++, rgl[f + "x" + d + "k"]++))
    });
    t2load();
};
realSet = function() {
    t1load(1);
    calc();
};
randSet = function() {
    var e = 0;
    $("#t1 input").each(function(index) {
//        console.log(index + ' ' + $(this).index());
        if ("" == $(this).val()) {
            t0 = team[$(this).data("team")].tc;
            t1 = team[$(this).siblings().data("team")].tc;
            var a = [];
            for (i = 0; 5 > i; i++)
                a.push(Math.random() * Math.random() * C[t0].c[0]);
            t0a = Math.max.apply(null, a);
            a = [];
            for (i = 0; 5 > i; i++)
                a.push(Math.random() * Math.random() * C[t1].c[1]);
            t1d = Math.max.apply(null, a);
            t0v = Math.floor(Math.max(Math.round(Math.random()), t0a - t1d));
            $(this).val(t0v);
            $("#t1s input:eq(" + index + ")").val(t0v);
            e++;
        }
    });
    0 < e && calc();
};
clear = function() {
    initT1();
    initT3();
    initteam();
    t2load();
};
initT1 = function() {
    $("#t1 input, #t1s input").val("");
    for (i = 0; i < group[curg].length; i++) {
        for (j = 0; j < scdl[curg].length; j++) {
            scdl[curg][j][7] = "", scdl[curg][j][8] = "";
        }
    }
};
initT3 = function() {
    $("#t3 #" + curg + "1").html("");
    $("#t3 #" + curg + "2").html("");
    $("#t3 #f3g3").html("");
    $("#t3 #e3f3").html("");
};
initrgl = function() {
    for (i = 0; i < group[curg].length; i++)
        for (j in group[curg])
            rgl[group[curg][i] + "x" + group[curg][j] + "k"] = 0, rgl[group[curg][i] + "x" + group[curg][j] + "f"] = 0, rgl[group[curg][i] + "x" + group[curg][j] + "a"] = 0
};
initteam = function() {
    for (i = 0; i < group[curg].length; i++)
        team[group[curg][i]].pts = 0, team[group[curg][i]].p = 0, team[group[curg][i]].w = 0, team[group[curg][i]].d = 0, team[group[curg][i]].l = 0, team[group[curg][i]].f = 0, team[group[curg][i]].a = 0
};
toggleT1size = function() {
    $("#t1r").toggleClass("ex");
};


var C = {
    "A1": {c: [5, 5], n: "A1"},
    "A2": {c: [5, 5], n: "A2"},
    "A3": {c: [5, 5], n: "A3"},
    "A4": {c: [5, 5], n: "A4"},
    "B1": {c: [5, 5], n: "B1"},
    "B2": {c: [5, 5], n: "B2"},
    "B3": {c: [5, 5], n: "B3"},
    "B4": {c: [5, 5], n: "B4"},
    "C1": {c: [5, 5], n: "C1"},
    "C2": {c: [5, 5], n: "C2"},
    "C3": {c: [5, 5], n: "C3"},
    "C4": {c: [5, 5], n: "C4"},
    "D1": {c: [5, 5], n: "D1"},
    "D2": {c: [5, 5], n: "D2"},
    "D3": {c: [5, 5], n: "D3"},
    "D4": {c: [5, 5], n: "D4"},
    "E1": {c: [5, 5], n: "E1"},
    "E2": {c: [5, 5], n: "E2"},
    "E3": {c: [5, 5], n: "E3"},
    "E4": {c: [5, 5], n: "E4"},
    "F1": {c: [5, 5], n: "F1"},
    "F2": {c: [5, 5], n: "F2"},
    "F3": {c: [5, 5], n: "F3"},
    "F4": {c: [5, 5], n: "F4"},
    "G1": {c: [5, 5], n: "G1"},
    "G2": {c: [5, 5], n: "G2"},
    "G3": {c: [5, 5], n: "G3"},
    "G4": {c: [5, 5], n: "G4"},
    "H1": {c: [5, 5], n: "H1"},
    "H2": {c: [5, 5], n: "H2"},
    "H3": {c: [5, 5], n: "H3"},
    "H4": {c: [5, 5], n: "H4"},
    "BR": {c: [8, 8], n: "Brazil"},
    "AR": {c: [8, 6], n: "Argentina"},
    "CO": {c: [7, 5], n: "Colombia"},
    "UY": {c: [7, 6], n: "Uruguay"},
    "BE": {c: [6, 5], n: "Belgium"},
    "DE": {c: [8, 7], n: "Germany"},
    "ES": {c: [8, 7], n: "Spain"},
    "CH": {c: [6, 6], n: "Switzerland"},
    "DZ": {c: [4, 5], n: "Algeria"},
    "CM": {c: [5, 5], n: "Cameroon"},
    "CI": {c: [7, 5], n: "Côte d'Ivoire"},
    "GH": {c: [6, 5], n: "Ghana"},
    "NG": {c: [5, 5], n: "Nigeria"},
    "CL": {c: [6, 5], n: "Chile"},
    "EC": {c: [6, 5], n: "Ecuador"},
    "AU": {c: [4, 3], n: "Australia"},
    "IR": {c: [4, 4], n: "Iran"},
    "JP": {c: [6, 5], n: "Japan"},
    "KR": {c: [5, 4], n: "Korea Republic"},
    "CR": {c: [4, 5], n: "Costa Rica"},
    "HN": {c: [4, 4], n: "Honduras"},
    "MX": {c: [5, 5], n: "Mexico"},
    "US": {c: [5, 5], n: "USA"},
    "BA": {c: [6, 5], n: "Bosnia-Herzeg."},
    "HR": {c: [6, 5], n: "Croatia"},
    "UK": {c: [6, 6], n: "England"},
    "FR": {c: [6, 6], n: "France"},
    "GR": {c: [5, 5], n: "Greece"},
    "IT": {c: [7, 6], n: "Italy"},
    "NL": {c: [7, 6], n: "Netherlands"},
    "PT": {c: [7, 5], n: "Portugal"},
    "RU": {c: [6, 6], n: "Russia"},
    "UA": {c: [6, 5], n: "Ukraine*"},
    "BF": {c: [5, 5], n: "Burkina Faso*"}
};
var scdl = {
    a: [
        ["1", "2014-06-12", "17:00:+3", "A1", "A2", "", "", null, null, "Sao Paulo"],
        ["1", "2014-06-13", "13:00:+3", "A3", "A4", "", "", null, null, "Natal"],
        ["2", "2014-06-17", "16:00:+3", "A1", "A3", "", "", null, null, "Fortaleza"],
        ["2", "2014-06-18", "15:00:+4", "A4", "A2", "", "", null, null, "Manaus"],
        ["3", "2014-06-23", "17:00:+3", "A4", "A1", "", "", null, null, "Brasilia"],
        ["3", "2014-06-23", "17:00:+3", "A2", "A3", "", "", null, null, "Recife"]
    ],
    b: [
        ["1", "2014-06-13", "16:00:+3", "B1", "B2", "", "", null, null, "Salvador"],
        ["1", "2014-06-13", "18:00:+4", "B3", "B4", "", "", null, null, "Cuiaba"],
        ["2", "2014-06-18", "19:00:+3", "B1", "B3", "", "", null, null, "Rio De Janeiro"],
        ["2", "2014-06-18", "13:00:+3", "B4", "B2", "", "", null, null, "Porto Alegre"],
        ["3", "2014-06-23", "13:00:+3", "B4", "B1", "", "", null, null, "Curitiba"],
        ["3", "2014-06-23", "13:00:+3", "B2", "B3", "", "", null, null, "Sao Paulo"]
    ],
    c: [
        ["1", "2014-06-14", "13:00:+3", "C1", "C2", "", "", null, null, "Belo Horizonte"],
        ["1", "2014-06-14", "19:00:+3", "C3", "C4", "", "", null, null, "Recife"],
        ["2", "2014-06-19", "13:00:+3", "C1", "C3", "", "", null, null, "Brasilia"],
        ["2", "2014-06-19", "19:00:+3", "C4", "C2", "", "", null, null, "Natal"],
        ["3", "2014-06-24", "16:00:+4", "C4", "C1", "", "", null, null, "Cuiaba"],
        ["3", "2014-06-24", "17:00:+3", "C2", "C3", "", "", null, null, "Fortaleza"]
    ],
    d: [
        ["1", "2014-06-14", "16:00:+3", "D1", "D2", "", "", null, null, "Fortaleza"],
        ["1", "2014-06-14", "21:00:+4", "D3", "D4", "", "", null, null, "Manaus"],
        ["2", "2014-06-19", "16:00:+3", "D1", "D3", "", "", null, null, "Sao Paulo"],
        ["2", "2014-06-20", "13:00:+3", "D4", "D2", "", "", null, null, "Recife"],
        ["3", "2014-06-24", "13:00:+3", "D4", "D1", "", "", null, null, "Natal"],
        ["3", "2014-06-24", "13:00:+3", "D2", "D3", "", "", null, null, "Belo Horizonte"]
    ],
    e: [
        ["1", "2014-06-15", "13:00:+3", "E1", "E2", "", "", null, null, "Brasilia"],
        ["1", "2014-06-15", "16:00:+3", "E3", "E4", "", "", null, null, "Porto Alegre"],
        ["2", "2014-06-20", "16:00:+3", "E1", "E3", "", "", null, null, "Salvador"],
        ["2", "2014-06-20", "19:00:+3", "E4", "E2", "", "", null, null, "Curitiba"],
        ["3", "2014-06-24", "16:00:+4", "E4", "E1", "", "", null, null, "Manaus"],
        ["3", "2014-06-24", "17:00:+3", "E2", "E3", "", "", null, null, "Rio De Janeiro"]
    ],
    f: [
        ["1", "2014-06-15", "19:00:+3", "F1", "F2", "", "", null, null, "Rio De Janeiro"],
        ["1", "2014-06-16", "16:00:+3", "F3", "F4", "", "", null, null, "Curitiba"],
        ["2", "2014-06-21", "13:00:+3", "F1", "F3", "", "", null, null, "Belo Horizonte"],
        ["2", "2014-06-21", "18:00:+4", "F4", "F2", "", "", null, null, "Cuiaba"],
        ["3", "2014-06-25", "13:00:+3", "F4", "F1", "", "", null, null, "Porto Alegre"],
        ["3", "2014-06-25", "13:00:+3", "F2", "F3", "", "", null, null, "Salvador"]
    ],
    g: [
        ["1", "2014-06-16", "13:00:+3", "G1", "G2", "", "", null, null, "Salvador"],
        ["1", "2014-06-16", "19:00:+3", "G3", "G4", "", "", null, null, "Natal"],
        ["2", "2014-06-21", "16:00:+3", "G1", "G3", "", "", null, null, "Fortaleza"],
        ["2", "2014-06-22", "15:00:+4", "G4", "G2", "", "", null, null, "Manaus"],
        ["3", "2014-06-26", "13:00:+3", "G4", "G1", "", "", null, null, "Recife"],
        ["3", "2014-06-26", "13:00:+3", "G2", "G3", "", "", null, null, "Brasilia"]
    ],
    h: [
        ["1", "2014-06-17", "13:00:+3", "H1", "H2", "", "", null, null, "Belo Horizonte"],
        ["1", "2014-06-17", "18:00:+4", "H3", "H4", "", "", null, null, "Cuiaba"],
        ["2", "2014-06-22", "19:00:+3", "H1", "H3", "", "", null, null, "Rio De Janeiro"],
        ["2", "2014-06-22", "13:00:+3", "H4", "H2", "", "", null, null, "Porto Alegre"],
        ["3", "2014-06-26", "17:00:+3", "H4", "H1", "", "", null, null, "Sao Paulo"],
        ["3", "2014-06-26", "17:00:+3", "H2", "H3", "", "", null, null, "Curitiba"]
    ]
},
calcmode = 0,
        team = {
            "A1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "BR", tc: "BR"},
            "A2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "HR", tc: "HR"},
            "A3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "MX", tc: "MX"},
            "A4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CM", tc: "CM"},
            "B1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "ES", tc: "ES"},
            "B2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "NL", tc: "NL"},
            "B3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CL", tc: "CL"},
            "B4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "AU", tc: "AU"},
            "C1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CO", tc: "CO"},
            "C2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "GR", tc: "GR"},
            "C3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CI", tc: "CI"},
            "C4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "JP", tc: "JP"},
            "D1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "UY", tc: "UY"},
            "D2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CR", tc: "CR"},
            "D3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "UK", tc: "UK"},
            "D4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "IT", tc: "IT"},
            "E1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "CH", tc: "CH"},
            "E2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "EC", tc: "EC"},
            "E3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "FR", tc: "FR"},
            "E4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "HN", tc: "HN"},
            "F1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "AR", tc: "AR"},
            "F2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "BA", tc: "BA"},
            "F3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "IR", tc: "IR"},
            "F4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "NG", tc: "NG"},
            "G1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "DE", tc: "DE"},
            "G2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "PT", tc: "PT"},
            "G3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "GH", tc: "GH"},
            "G4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "US", tc: "US"},
            "H1": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "BE", tc: "BE"},
            "H2": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "DZ", tc: "DZ"},
            "H3": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "RU", tc: "RU"},
            "H4": {pts: 0, p: 0, w: 0, d: 0, l: 0, f: 0, a: 0, cc: "KR", tc: "KR"}
        },
group = {
    a: ["A1", "A2", "A3", "A4"],
    b: ["B1", "B2", "B3", "B4"],
    c: ["C1", "C2", "C3", "C4"],
    d: ["D1", "D2", "D3", "D4"],
    e: ["E1", "E2", "E3", "E4"],
    f: ["F1", "F2", "F3", "F4"],
    g: ["G1", "G2", "G3", "G4"],
    h: ["H1", "H2", "H3", "H4"]
},
rank = {};

var urlp = {};
if (location.hash.match(/\#!(.+)/)) {
    var kv = RegExp.$1.split("&");
    for (i = 0, iLen = kv.length; i < iLen; i++) {
        var k = kv[i].split("=")[0];
        var v = kv[i].split("=")[1];
        urlp[k] = decodeURIComponent(v);
    }
} else if (location.search.match(/\?(.+)/)) {
    var kv = RegExp.$1.split("&");
    for (i = 0, iLen = kv.length; i < iLen; i++) {
        var k = kv[i].split("=")[0];
        var v = kv[i].split("=")[1];
        urlp[k] = decodeURIComponent(v);
    }
}
if (urlp["A1"])
    $("#warning-0").show();
for (i in urlp) {
    if (team[i]) {
        team[i].cc = urlp[i];
        team[i].tc = urlp[i];
        team[i].n = C[urlp[i]];
        team[i].n2 = C[urlp[i]];
    }
}