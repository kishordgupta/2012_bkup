var curg,
//        inig = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'],
        inig = 'a',
        rgl = {},
        calcmode = 0,
        timezoned = new Date,
        timezone = timezoned.getTimezoneOffset() / 60;
var scdl = {
    a: [
        ["1", "2014-06-12", "17:00:+3", "A1", "A2", "", "", null, null, "Sao Paulo"],
        ["2", "2014-06-13", "13:00:+3", "A3", "A4", "", "", null, null, "Natal"],
        ["17", "2014-06-17", "16:00:+3", "A1", "A3", "", "", null, null, "Fortaleza"],
        ["18", "2014-06-18", "15:00:+4", "A4", "A2", "", "", null, null, "Manaus"],
        ["33", "2014-06-23", "17:00:+3", "A4", "A1", "", "", null, null, "Brasilia"],
        ["34", "2014-06-23", "17:00:+3", "A2", "A3", "", "", null, null, "Recife"]
    ],
    b: [
        ["3", "2014-06-13", "16:00:+3", "B1", "B2", "", "", null, null, "Salvador"],
        ["4", "2014-06-13", "18:00:+4", "B3", "B4", "", "", null, null, "Cuiaba"],
        ["19", "2014-06-18", "19:00:+3", "B1", "B3", "", "", null, null, "Rio De Janeiro"],
        ["20", "2014-06-18", "13:00:+3", "B4", "B2", "", "", null, null, "Porto Alegre"],
        ["35", "2014-06-23", "13:00:+3", "B4", "B1", "", "", null, null, "Curitiba"],
        ["36", "2014-06-23", "13:00:+3", "B2", "B3", "", "", null, null, "Sao Paulo"]
    ],
    c: [
        ["5", "2014-06-14", "13:00:+3", "C1", "C2", "", "", null, null, "Belo Horizonte"],
        ["6", "2014-06-14", "19:00:+3", "C3", "C4", "", "", null, null, "Recife"],
        ["21", "2014-06-19", "13:00:+3", "C1", "C3", "", "", null, null, "Brasilia"],
        ["23", "2014-06-19", "19:00:+3", "C4", "C2", "", "", null, null, "Natal"],
        ["37", "2014-06-24", "16:00:+4", "C4", "C1", "", "", null, null, "Cuiaba"],
        ["38", "2014-06-24", "17:00:+3", "C2", "C3", "", "", null, null, "Fortaleza"]
    ],
    d: [
        ["7", "2014-06-14", "16:00:+3", "D1", "D2", "", "", null, null, "Fortaleza"],
        ["8", "2014-06-14", "21:00:+4", "D3", "D4", "", "", null, null, "Manaus"],
        ["23", "2014-06-19", "16:00:+3", "D1", "D3", "", "", null, null, "Sao Paulo"],
        ["24", "2014-06-20", "13:00:+3", "D4", "D2", "", "", null, null, "Recife"],
        ["39", "2014-06-24", "13:00:+3", "D4", "D1", "", "", null, null, "Natal"],
        ["40", "2014-06-24", "13:00:+3", "D2", "D3", "", "", null, null, "Belo Horizonte"]
    ],
    e: [
        ["9", "2014-06-15", "13:00:+3", "E1", "E2", "", "", null, null, "Brasilia"],
        ["10", "2014-06-15", "16:00:+3", "E3", "E4", "", "", null, null, "Porto Alegre"],
        ["25", "2014-06-20", "16:00:+3", "E1", "E3", "", "", null, null, "Salvador"],
        ["26", "2014-06-20", "19:00:+3", "E4", "E2", "", "", null, null, "Curitiba"],
        ["41", "2014-06-24", "16:00:+4", "E4", "E1", "", "", null, null, "Manaus"],
        ["42", "2014-06-24", "17:00:+3", "E2", "E3", "", "", null, null, "Rio De Janeiro"]
    ],
    f: [
        ["11", "2014-06-15", "19:00:+3", "F1", "F2", "", "", null, null, "Rio De Janeiro"],
        ["12", "2014-06-16", "16:00:+3", "F3", "F4", "", "", null, null, "Curitiba"],
        ["27", "2014-06-21", "13:00:+3", "F1", "F3", "", "", null, null, "Belo Horizonte"],
        ["28", "2014-06-21", "18:00:+4", "F4", "F2", "", "", null, null, "Cuiaba"],
        ["43", "2014-06-25", "13:00:+3", "F4", "F1", "", "", null, null, "Porto Alegre"],
        ["44", "2014-06-25", "13:00:+3", "F2", "F3", "", "", null, null, "Salvador"]
    ],
    g: [
        ["13", "2014-06-16", "13:00:+3", "G1", "G2", "", "", null, null, "Salvador"],
        ["14", "2014-06-16", "19:00:+3", "G3", "G4", "", "", null, null, "Natal"],
        ["29", "2014-06-21", "16:00:+3", "G1", "G3", "", "", null, null, "Fortaleza"],
        ["30", "2014-06-22", "15:00:+4", "G4", "G2", "", "", null, null, "Manaus"],
        ["45", "2014-06-26", "13:00:+3", "G4", "G1", "", "", null, null, "Recife"],
        ["46", "2014-06-26", "13:00:+3", "G2", "G3", "", "", null, null, "Brasilia"]
    ],
    h: [
        ["15", "2014-06-17", "13:00:+3", "H1", "H2", "", "", null, null, "Belo Horizonte"],
        ["16", "2014-06-17", "18:00:+4", "H3", "H4", "", "", null, null, "Cuiaba"],
        ["31", "2014-06-22", "19:00:+3", "H1", "H3", "", "", null, null, "Rio De Janeiro"],
        ["32", "2014-06-22", "13:00:+3", "H4", "H2", "", "", null, null, "Porto Alegre"],
        ["47", "2014-06-26", "17:00:+3", "H4", "H1", "", "", null, null, "Sao Paulo"],
        ["48", "2014-06-26", "17:00:+3", "H2", "H3", "", "", null, null, "Curitiba"]
    ]
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

var team = {
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
};

t1load = function(e) {
    $("#t1 tbody, #t1s tbody").empty();
//    $.each(inig, function(ingex, val) {
//        curg = val;
        var i;
        $("#t1s tbody").append('<tr><th class="ui-bar-b" colspan="3"> Group ' + curg.toUpperCase() + '</th></tr>');
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
            } else {
                b = a[1], d = a[2].split(":")[0] + ":" + a[2].split(":")[1];
            }

            $("#t1s tbody").append(
                    "<tr>" +
                    "<td class='srl'>" + a[0] + "</td>" +
                    "<td class='d'>" + b + " &nbsp; " + d + "<br/>" + a[9] + "</td>" +
                    "<td class='team'><div class='flag-24 flag-24-" + team[a[3]].tc + "'></div>" + C[team[a[3]].tc].n + "<br/><div class='flag-24'>VS</div><br/>" +
                    "<div class='flag-24 flag-24-" + team[a[4]].tc + "'></div>" + C[team[a[4]].tc].n + "</td>"
                    );
            $("#t1 tbody").append(
                    "<tr>" +
                    "<th>" + a[0] + "</th>" +
                    "<td class='d'>" + b + "</td>" +
                    "<td class='t'>" + d + "</td>" +
                    "<td class='v'>" + a[9] + "</td>" +
                    "<td class='team rtxt'><div class='flag-24 flag-24-" + team[a[3]].tc + "'></div>" + C[team[a[3]].tc].n + "</td>" +
                    '<td style="width: 20px; text-align: center;">VS</td>' +
                    "<td class='team ltxt'><div class='flag-24 flag-24-" + team[a[4]].tc + "'></div>" + C[team[a[4]].tc].n + "</td>"
                );
        }
//    });
};
loadGroup = function(e) {
    $("#group a").removeClass("slct");
    $("#group a#g" + e).addClass("slct");
    curg = e;
    t1load();
};
$(function() {
    loadGroup(inig);
    $(document).on("change", "#t1ope input.timezone", function() {
        t1load();
    }).on('mobileinit', function () {
        $.mobile.ignoreContentEnabled = true;
    });
});