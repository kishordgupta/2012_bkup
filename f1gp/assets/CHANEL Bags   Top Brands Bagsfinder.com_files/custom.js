jQuery.noConflict();

jQuery(document).ready(function(){

     // this code detects if .mainpanel is resized
     // when the height is below than the window height then
     // resize the minHeight equal to window height
     mainPanelResize();
     responsiveLayout();
     jQuery('.mainpanel').resize(function(){
	mainPanelResize();
     });
     
     jQuery(window).resize(function(){
	mainPanelResize();
	responsiveLayout();
     });
     
     function mainPanelResize() {
	var winHeight = jQuery(window).height();
	if(jQuery('.mainpanel').height() < winHeight)
		jQuery('.mainpanel').css({minHeight: winHeight});
     }
     
     // add placeholder for search input if browser doesn't support it
     if(!jQuery.support.placeholder) {
	jQuery('.searchpanel input').val('Search and hit enter...').focusin(function(){
		if(jQuery(this).val() == 'Search and hit enter...')
			jQuery(this).val('');
	}).focusout(function(){
		if(jQuery(this).val() == '')
			jQuery(this).val('Search and hit enter...');	
	});
	
     }

     // Leftpanel Menu Navigation
     jQuery('.menupanel ul').superfish({ 
	delay:       100,                            	// one second delay on mouseout 
	animation:   {opacity:'show',height:'show'},  	// fade-in and slide-down animation 
	speed:       'fast',                          	// faster animation speed 
	autoArrows:  false                           	// disable generation of arrow mark-up 
     });
     
     // CSS3 animation for left menu panel
     jQuery('.menupanel a').hover(function(){
	if(!jQuery(this).parent().hasClass('current'))
		jQuery(this).find('span').addClass('animate0 rotateIcon');
     },function(){
	jQuery(this).find('span').removeClass('animate0 rotateIcon');
     });
     
     
     // Pretty Photo
     if(jQuery.prettyPhoto) {
	jQuery("a[class^='prettyPhoto']").prettyPhoto({social_tools: false});
     }
     
     // Image Hover for Blog and Portfolio
     jQuery('.thumb a, .blogimg a, .blogimg-slide a, .gridimg a').hover(function(){
	var icon = (jQuery(this).hasClass('prettyPhoto'))? 'search' : 'link';
	icon = (jQuery(this).hasClass('video'))? 'play' : icon;
	jQuery(this).append(
                            '<div class="overlay">'+
                                '<div class="animate0 bounceInDown">'+
                                    '<span>'+
                                        '<i class="iconfa-'+icon+'" />'+
                                    '</span>'+
                                '</div>'+
                            '</div>');
	jQuery(this).find('.overlay').fadeIn();
     }, function(){
	jQuery(this).find('span').removeClass('animate0 bounceInDown').addClass('animate0 bounceOutUp');
	jQuery(this).find('.overlay').fadeOut(function(){
	    jQuery(this).remove();
	});
     });
     
     // Autogrow textarea found in contact and in blog comments
     if(jQuery().autogrow)
	jQuery('textarea').autogrow();
	
	
     // Responsive layout response
     function responsiveLayout() {
	if(jQuery(window).width() < 1250) {
	   
	   // features in homepage
	   if(!jQuery('.featinner').hasClass('resized')) { 	//this prevent to run multiple times
		var index = 0;
		jQuery('.featinner .one_fourth').each(function(){
		     jQuery(this).removeClass('last');
		     if(index%2 != 0) jQuery(this).addClass('last');
		     index++;
		});
		jQuery('.featinner').addClass('resized');
	   }
	   
	   // portfolio related in homepage
	   if(!jQuery('.portfolio-related').hasClass('resized')) {
		var index = 0;
		jQuery('.portfolio-related li').each(function(){
		     jQuery(this).removeClass('last');
		     if(index%2 != 0) jQuery(this).addClass('last');
		     index++;
		});
		jQuery('.portfolio-related').addClass('resized');
	   }
	}

     }
     
     // Add menu bar for mobile view
     jQuery('body').append('<div class="menubar"><a class="showleftmenu" /></div>');
     jQuery('.menupanel').clone().appendTo('.leftpanel').removeClass('menupanel').addClass('menupanel2');
     jQuery('.logopanel img').clone().appendTo('.menubar');
     jQuery('.menupanel2 li, .menupanel2 ul').each(function(){
	jQuery(this).css({visibility: 'visible', display: 'block'});
     });
     jQuery('.showleftmenu').live('click', function(){
	if(!jQuery('body').hasClass('mobilemenuview'))
		jQuery('body').addClass('mobilemenuview');
	else	jQuery('body').removeClass('mobilemenuview');
     });
     
     
	///// COLOR SWITCHER: DEMO PURPOSES ONLY DO NOT INCLUDE IN PRODUCTION /////
	jQuery('.settings .show').click(function(){
		if(!jQuery(this).hasClass('hide')) {
			jQuery(this).parent().stop().animate({marginRight: 0},'fast');
			jQuery(this).addClass('hide');
		} else {
			jQuery(this).parent().stop().animate({marginRight: '-150px'},'fast');	
			jQuery(this).removeClass('hide');
		}
	});
	
	jQuery('.predefined a').click(function(){
		var style = jQuery(this).attr('href');
		if(jQuery('#customstyle').length == 0)
			jQuery('head').append('<link id="customstyle" rel="stylesheet" href="css/'+style+'.css" type="text/css" />');
		else
			jQuery('#customstyle').attr('href','css/'+style+'.css');
		if(style == '') {
			jQuery('#customstyle').remove();
			jQuery('.logopanel img').attr('src','images/sartana-logo.png');
		} else {
			jQuery('.logopanel img').attr('src','images/sartana-logo2.png');
		}
		return false;
	});
	
});

// Change the background
window.setInterval(function(){
	var c = 1 + Math.floor(Math.random() * 4);;
	jQuery('body').removeClass('bg1 bg2 bg3 bg4');
	if(!jQuery('body').hasClass('homebody'))
		jQuery('body').addClass('bg'+c);
	return c++;
}, 10000);


// Page preloader
jQuery(window).load(function(){
    
    var trans = 'fadeInUp fadeInRight fadeInDown fadeInLeft'.split(' ');
    var tnum  = 0 + Math.floor(Math.random() * 4);
    
    if(Modernizr.csstransitions) { 
	jQuery('.mainpanel').addClass('animate5 '+trans[tnum]);
    } else {
	jQuery('.mainpanel').animate({opacity: 1});
    }
    
    if(!jQuery('body').hasClass('homebody')) {
       var bgnum = 1 + Math.floor(Math.random() * 4);
       jQuery('body').addClass('bg'+bgnum);
    }
    
});