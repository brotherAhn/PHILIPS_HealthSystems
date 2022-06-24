	function check_key() {
	 var char_ASCII = event.keyCode;
	                
	  //숫자
	 if (char_ASCII >= 48 && char_ASCII <= 57 )
	   return 1;
	 //영어
	 else if ((char_ASCII>=65 && char_ASCII<=90) || (char_ASCII>=97 && char_ASCII<=122))
	    return 2;
	 //특수기호
	 else if ((char_ASCII>=33 && char_ASCII<=47) || (char_ASCII>=58 && char_ASCII<64) 
	   || (char_ASCII>=91 && char_ASCII<=96) || (char_ASCII>=123 && char_ASCII<=126))
	    return 4;
	 //한글
	 else if ((char_ASCII >= 12592) || (char_ASCII <= 12687))
	    return 3;
	 else 
	    return 0;
	}
	
	function nonHangulSpecialKey() {
	 	if(check_key() != 1 && check_key() != 2 && check_key() != 3) {
	  	alert("비정상적인 문자는 입력할 수 없습니다.");
	  	
		event.preventDefault ? event.preventDefault() : (event.returnValue = false);	   
	  	return;
	 }
	}
	function do_logout(){
		$.ajax({
            url : '/do_logout',
            success : function(data) {
            	if(data.trim()=="success"){
            		alert("로그아웃 되었습니다.");
            		location.href="/admin"
            	}else{
            		alert("로그아웃에 실패하였습니다.");
            	}
            },
            error : function(xhr, status) {
            	alert("로그아웃에 실패하였습니다.");
            }
        });
	}
	function numberformat(){
			$('.format-mobile').text(function() {
			    var str = $(this).text().trim();    
			    var phone = str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
			    $(this).text(phone);
			});
	}
	
/*	var closing_window = false; 
		   $(window).on('focus', function () { 
			   closing_window = false; 
			   }); 
		   $(window).on('blur', function () { 
			   closing_window = true;
			   if (!document.hidden) {
				   closing_window = false; 
				   } 
			   $(window).on('resize', function (e) {
				closing_window = false;
				}); 
				$(window).off('resize');
			});
		   $('html').on('mouseleave', function () { 
			   closing_window = true; 
		   }); 
		   $('html').on('mouseenter', function () { 
			   closing_window = false; 
		   }); 
		   $(document).on('keydown', function (e) {
			   if (e.keyCode == 91 || e.keyCode == 18) { 
				   closing_window = false;
			   } if (e.keyCode == 116 || (e.ctrlKey && e.keyCode == 82)) { 
				   closing_window = true;
				   } 
			   }); 
		   $(document).on("click", "a", function () { 
			   closing_window = false; 
			   });
		   $(document).on("click", "button", function () { 
			   closing_window = false; 
			   });
		   $(document).on("submit", "form", function () {
			   closing_window = false;
			   }); 
		   $(document).on("click", "input[type=submit]", function () {
			   closing_window = false; 
			   }); 
		   var toDoWhenClosing = function() {
			   $.ajax({ 
				   async: false,
				   url : '/do_logout'
				   }); 
			   	return; 
			   }; 
			   window.addEventListener("beforeunload", function (e) {
				   if (closing_window){ 
					   do_logout();
				   } 
				});*/