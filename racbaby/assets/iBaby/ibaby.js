
var settings = {
//	start : 1331932008 - (45) * 86400,
//	gender : 'girl',
//	firstName : 'Firstname',
//	lastName : 'Lastname',
	
	get : function (field) {
		return localStorage[field];
		//return this[field];
	},

	set : function (field, value) {
		localStorage[field] = value;
		//this[field] = value;
		//return this[field];
	}
};



$(document).ready(function() {

	if(isNaN(settings.get("#start"))) {
		$(window).bind("pagecontainercreate", function(event) {
			$.mobile.firstPage = $("#edit");		
		});
	}
	
	$('#edit').live('pagebeforeshow',function(event, ui){
        $("#inputFirstName").attr("value", settings.get("firstName"));
        $("#inputLastName").attr("value", settings.get("lastName"));
        var b = settings.get("gender") != "girl" ? true : false;
         
    	$("#genderOptionBoy").attr("checked", b).checkboxradio("refresh");
    	$("#genderOptionGirl").attr("checked", !b).checkboxradio("refresh");
    	
    	var b = settings.get("dateType") != "#start" ? true : false;
    	
    	$("#dateTypeDueDate").attr("checked", b).checkboxradio("refresh");
    	$("#dateTypeStartDate").attr("checked", !b).checkboxradio("refresh");
    	
    	var timestamp = settings.get("#start");
    	
    	if(b) {
    		timestamp += 24192000;
    	}
    	
    	var date;
    	if(isNaN(timestamp)) {
    		date = new Date();
    	} else {
    		date = new Date(settings.get("#start")*1000);
    	}
    	
    	var month = date.getMonth()+1;
    	month = month < 10 ? "0" + month : month + "";
    	var day = date.getDate();
    	day = day < 10 ? "0" + day : day + "";
    	
    	var dateValue = date.getFullYear() + "-" + month + "-" + day;
    	$('#startDate').val(dateValue).trigger('change');
	});
	
	$('#edit').live('pagebeforehide', function(event, ui) {
		settings.set("firstName", $("#inputFirstName").attr("value"));
		settings.set("lastName", $("#inputLastName").attr("value"));
		
		var gender = $("label[for=genderOptionBoy]").hasClass("ui-radio-on") ? 'boy' : 'girl';
		settings.set("gender", gender);
		
		var dateValue = $('#dateInput').val();		
		var dateType = $("label[for=dateTypeDueDate]").hasClass("ui-radio-on") ? 'due' : 'start';
		
		settings.set("dateType", dateType);
		
		var arr = dateValue.split(/[-]/);
	    var date = new Date(arr[0], arr[1]-1, arr[2], 0,0,0);
	    
	    var timestamp = dateType == 'start' ? date.getEpoch() : date.getEpoch() - 24192000; // 40 * 7 * 86400
	    
		settings.set('start', timestamp);
		
		reset();		
	});
	
	reset();
	$.mobile.initializePage();
	
	// Refresh once every 5 minutes.
	setInterval("updateStats()", 300000);
});


function reset() {
		
	$("body").removeClass("boy");
	$("body").removeClass("girl");
	$("body").addClass(settings.get('gender'));
	
	$(".babyFirstName").html(settings.get('firstName'));
	$(".babyLastName").html(settings.get('lastName'));
	
	if(settings.get('gender') != 'girl') {
		$("#statsStart img").attr("src", "images/sperm.png");
		$("#statsCurrent img").attr("src", "images/calendar.png");
		$("#statsDue img").attr("src", "images/babyseat.png");
		settings.set('dueImage', "images/babyseat.png");
		settings.set('bornImage', "images/speen.png");
		
	} else {
		$("#statsStart img").attr("src", "images/sperm-girl.png");
		$("#statsCurrent img").attr("src", "images/calendar-girl.png");
		$("#statsDue img").attr("src", "images/babyseat-girl.png");		
		settings.set('dueImage', "images/babyseat-girl.png");
		settings.set('bornImage', "images/speen-girl.png");
	}
	
	updateStats();
	
}

function updateStats() {
	var stats = calcWeeks();
	var month = Math.floor(stats.weeks / 4);
	var week = stats.weeks % 4;

	
	// info block
	$("#statsStart span").text("I got pregnant on " + stats.date);
	$("#statsCurrent span").text("I have been pregnant for " + stats.weeks + " weeks and " + stats.days + " days."); // + hours + " hours," + minutes + " minutes and " + elapsed + " seconds");

	if(stats.overDue) {
		$("#statsDue span").text("My baby was due on " + stats.dueDate + " which was " + stats.dueWeeks + " weeks and " + stats.dueDays + " days ago. Congratulations! " + settings.get("firstName") + " has been born!");
		$("#statsDue img").attr("src", settings.get("bornImage"));	
	} else {
		$("#statsDue span").text("My baby is due on " + stats.dueDate + " which is in " + stats.dueWeeks + " weeks and " + stats.dueDays + " days");
		$("#statsDue img").attr("src", settings.get("dueImage"));	
	}

	
	// Progress bars
	var w = 0;
	for(var i = 0; i <= 9; i++) {
		if(month>i) w = 100;
		else if(month<i) w = 0;
		else w = week*25 + Math.floor(25/7*stats.days);
		$("#progress"+i).css("width", w+"%");
		$("#progressPct"+i).text(w+"%");
	}
	
	// Weeks highlighting
	for(var i = 1; i <= 40; i++) {
		if(i <= stats.weeks) {			
			$("#detailWeek"+i+" li").addClass("done");
			$("#detailWeek"+i+" li").removeClass("current");
		} else if(i == stats.weeks+1) {
			$("#detailWeek"+i+" li").removeClass("done");
			$("#detailWeek"+i+" li").addClass("current");
		} else {
			$("#detailWeek"+i+" li").removeClass("current");
			$("#detailWeek"+i+" li").removeClass("done");
		}
	}
	
	
}



function calcWeeks() {
	var overDue;
	var date = new Date(settings.get("start") * 1000);
	var due = new Date(date.getTime() + 86400 * 7 * 40 * 1000);
	var elapsed = Math.floor(( Date.now() - date.getTime() ) / 1000);
	var weeks = Math.floor(elapsed / (86400 * 7));
	elapsed -= weeks * 86400 * 7;
	var days = Math.floor(elapsed / 86400);
	var togo = Math.floor((due.getTime() - Date.now())/1000);
	
	if(togo < 0) {
		togo = Math.abs(togo);
		overDue = true;
	} else {
		overDue = false;
	}
	
	var dueWeeks = Math.floor(togo / (86400 * 7));
	togo -= dueWeeks * 86400 * 7;
	var dueDays = Math.floor(togo / 86400) + 1;
	if(dueDays == 7) { dueWeeks++; dueDays = 0; }
	
	return {
		date :  date.toLocaleDateString(),
		dueDate : due.toLocaleDateString(),
		weeks: weeks,
		days: days,
		dueWeeks: dueWeeks,
		dueDays: dueDays,
		overDue: overDue
	};
}

