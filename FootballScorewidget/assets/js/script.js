$("#t1").append(
                "<tr>" +
                "<th>" + a[0] + "</th>" +
                "<td class='d'>" + b + "<br/>" + d + "<br/>" + a[9] + "</td>" +
//                "<td class='t'>" + d + "</td>" +
//                "<td class='v'>" + a[9] + "</td>" +
                "<td class='score'><div class='flag-24 flag-24-" + team[a[3]].tc + "'></div>" + C[team[a[3]].tc].n +
                "<div><input type='text' maxlength=1 data-team='" + a[3] + "' value='" + sc1 + "' pattern='[0-9]*'>" +
                "VS" +
                "<input type='text' maxlength=1 data-team='" + a[4] + "' value='" + sc2 + "' pattern='[0-9]*'></div>" + 
                "<div class='flag-24 flag-24-" + team[a[4]].tc + "'></div>" + C[team[a[4]].tc].n +"</td>"
                );