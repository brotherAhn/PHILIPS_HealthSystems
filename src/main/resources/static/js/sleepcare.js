	//한글 입력
	function nonHangulSpecialKey() {
	 	if(check_key() != 1 && check_key() != 2 && check_key() != 3) {
	  	alert("특수 문자는 입력할 수 없습니다.");
	  	
		event.preventDefault ? event.preventDefault() : (event.returnValue = false);	   
	  	return;
	 }
	}
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
	//1주년 필터 전송
	function doFilter() {
		
		var is_empty = false;

		if ($("#fil_name").val() == null || $("#fil_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#fil_mobile").val() == null
				|| $("#fil_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#fil_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#fil_mobile").val("");
			return;
		}
		if ($("#fil_zipcode1").val() == null
				|| $("#fil_zipcode1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#fil_address2").val() == null
				|| $("#fil_address2").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#fil_address1").val() == null
				|| $("#fil_address1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#fil_birth").val() == null
				|| $("#fil_birth").val() == "") {
			alert("생년월일을 입력하지 않았습니다");
			return;
		}
		if ($("#fil_request").val() == null
				|| $("#fil_request").val() == "") {
			alert("요청사항을 입력하지 않았습니다");
			return;
		}
		if (!$('#fil_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#fil_private_yn').val('Y');

		}

		if ($('#fil_marketing_yn').is(':checked')) {
			$('#fil_marketing_yn').val('Y');
		} else {
			$('#fil_marketing_yn').val('N');
		}

		var address = "";
		address = $("#fil_address1").val() + $("#fil_address2").val();
		$("#fil_address").val(address);

		var zipcode = "";
		zipcode = $("#fil_zipcode1").val();
		$("#fil_zipcode").val(zipcode);
		
		var frmData = $("#filter").serialize();
		
		$.ajax({
	        url : '/do_filter',
	        type : 'POST',
	        data : frmData,
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success : function(data) {
	        	if(data.trim()=="success"){
	                var dimm = document.getElementById("dimmed");
	                dimm.style.display = "block";
	        	}else{
	        		alert("입력에 실패하였습니다. 다시 시도해주세요.");
	        	}
	        },
	        error : function(xhr, status) {
	        	console.log(xhr);
	        	console.log(status);
	            alert("입력에 실패하였습니다. 다시 시도해주세요.");
	        }
	    });
		
	}
	//개인정보동의서 전송
	function doPrivate() {
		var is_empty = false;

		if ($("#private_name").val() == null || $("#private_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}

		if ($("#private_mobile").val() == null || $("#private_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#private_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#private_mobile").val("");
			return;
		}
		if ($("#private_email").val() == null || $("#private_email").val() == "") {
			alert("이메일을 입력하지 않았습니다");
			return;
		}

		var obEmail = document.getElementById("private_email");
		if (!CheckEmail(obEmail.value)) {
			alert("이메일 형식이 잘못되었습니다");
			obEmail.focus();
			return;
		}

		if (!$('#private_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#private_private_yn').val('Y');

		}


		if ($('#private_marketing_yn').is(':checked')) {
			$('#private_marketing_yn').val('Y');
		} else {
			$('#private_marketing_yn').val('N');
		}

		var frmData = $("#frmPrivate").serialize();
		$.ajax({
			url : '/do_private',
			type : 'POST',
			data : frmData,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	//
	function doCoach() {
		var is_empty = false;

		if ($("#coach_name").val() == null || $("#coach_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}

		if ($("#coach_mobile").val() == null || $("#coach_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#coach_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#coach_mobile").val("");
			return;
		}
		if ($("#coach_call_dt").val() == null || $("#coach_call_dt").val() == "") {
			alert("통화희망요일을 선택하지 않았습니다.");
			return;
		}
		if ($("#coach_call_time").val() == null || $("#coach_call_time").val() == "") {
			alert("통화희망시간대를 선택하지 않았습니다");
			return;
		}
		if ($("#coach_request").val() == null || $("#coach_request").val() == "") {
			alert("문의사항을 입력하지 않았습니다");
			return;
		}
		if (!$('#coach_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#coach_private_yn').val('Y');

		}

		if ($('#coach_marketing_yn').is(':checked')) {
			$('#coach_marketing_yn').val('Y');
		} else {
			$('#coach_marketing_yn').val('N');
		}

		var frmData = $("#frmCoach").serialize();
		$.ajax({
			url : '/do_coach',
			type : 'POST',
			data : frmData,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	//설치 동의서 전송
	function doInstall() {
		var is_empty = false;

		if ($("#install_name").val() == null || $("#install_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#install_mobile").val() == null
				|| $("#install_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#install_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#install_mobile").val("");
			return;
		}
		if ($("#install_email").val() == null
				|| $("#install_email").val() == "") {
			alert("이메일을 입력하지 않았습니다");
			return;
		}
		if ($("#zipcode1").val() == null
				|| $("#zipcode1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#address2").val() == null
				|| $("#address2").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#address1").val() == null
				|| $("#address1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}

		var obEmail = document.getElementById("install_email");
		if (!CheckEmail(obEmail.value)) {
			alert("이메일 형식이 잘못되었습니다");
			obEmail.focus();
			return;
		}

		if (!$('#install_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#install_private_yn').val('Y');

		}

		if ($('#install_marketing_yn').is(':checked')) {
			$('#install_marketing_yn').val('Y');
		} else {
			$('#install_marketing_yn').val('N');
		}

		var address = "";
		address = $("#address1").val() + $("#address2").val();
		$("#address").val(address);

		var zipcode = "";
		zipcode = $("#zipcode1").val();
		$("#zipcode").val(zipcode);

		var frmData = $("#frmInstall").serialize();

		$.ajax({
			url : '/do_co_install',
			type : 'POST',
			data : frmData,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	//주소 찿기 다음 주소
	function addressFind() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
				}
				document.getElementById("zipcode1").value = data.zonecode;
				document.getElementById("address1").value = addr + extraAddr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("address2").focus();
			}
		}).open();
	}
	//주소 찿기
	function fil_addressFind() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
				}
				document.getElementById("fil_zipcode1").value = data.zonecode;
				document.getElementById("fil_address1").value = addr + extraAddr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("fil_address2").focus();
			}
		}).open();
	}
	function doAgree() {

		var is_empty = false;

		if ($("#agree_name").val() == null || $("#agree_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#agree_mobile").val() == null || $("#agree_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#agree_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#agree_mobile").val("");
			return;
		}
		if ($("#agree_email").val() == null || $("#agree_email").val() == "") {
			alert("이메일을 입력하지 않았습니다");
			return;
		}

		var obEmail = document.getElementById("agree_email");
		if (!CheckEmail(obEmail.value)) {
			alert("이메일 형식이 잘못되었습니다");
			obEmail.focus();
			return;
		}

		if (!$('#agree_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#agree_private_yn').val('Y');

		}

		if ($('#agree_marketing_yn').is(':checked')) {
			$('#agree_marketing_yn').val('Y');
		} else {
			$('#agree_marketing_yn').val('N');
		}
		var frmData = $("#frmAgree").serialize();

		$.ajax({
			url : '/do_co_agree',
			type : 'POST',
			data : frmData,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				console.log(xhr);
				console.log(status);
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	function CheckEmail(str) {
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if (!reg_email.test(str)) {
			return false;
		} else {
			return true;
		}
	}
	function doPrescription() {
		var is_empty = false;
		if ($("#pre_name").val() == null || $("#pre_name").val().trim() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#pre_mobile").val() == null
				|| $("#pre_mobile").val().trim() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#pre_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#pre_mobile").val("");
			return;
		}
		if ($("#pre_hospital").val() == null
				|| $("#pre_hospital").val().trim() == "") {
			alert("병원명을 입력하지 않았습니다.");
			return;
		}
		if ($("#pre_file").val() == null || $("#pre_file").val() == "") {
			alert("처방전이 없습니다.");
			return;
		}
		if (!$('#pre_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#pre_private_yn').val('Y');
		}
		if ($('#pre_marketing_yn').is(':checked')) {
			$('#pre_marketing_yn').val('Y');
		} else {
			$('#pre_marketing_yn').val('N');
		}

		var frmData = $("#frmPrescription").serialize();
		$("#frmPrescription").ajaxSubmit({
			url : '/do_prescription',
			type : 'POST',
			data : frmData,
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	function setValue(setID,data){
        $("#"+setID).val(data)
    }
	function mobileMax(e) {
		if (e.value.length > e.maxLength) {
			alert("11 자리를 초과하여 입력할 수 없습니다")
			e.value = e.value.slice(0, e.maxLength);
		}
	}
	function isNumberKey(obj) {
		val = obj.value;
		if (!(myIsNaN(val))) {
			re = /[^0-9]/gi;
			obj.value = val.replace(re, "");
		}
	}
	//숫자형식검사
	function myIsNaN(val) {
    	return typeof(val) === 'number';
	}
	//이메일 형식 검사
	function CheckEmail(str) {
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if (!reg_email.test(str)) {
			return false;
		} else {
			return true;
		}
	}
	//팝업실행
	function popup(url,name){
        var option = "width = 500, height = 500  location = no";
        window.open(url,name,option);
    }
	
	
	//반납 요청 페이지 전송
	function doReturn() {
		var is_empty = false;

		if ($("#return_name").val() == null || $("#return_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#return_mobile").val() == null
				|| $("#return_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#return_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#return_mobile").val("");
			return;
		}
		if ($("#zipcode1").val() == null
				|| $("#zipcode1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#address2").val() == null
				|| $("#address2").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		if ($("#address1").val() == null
				|| $("#address1").val() == "") {
			alert("주소를 입력하지 않았습니다");
			return;
		}
		
		if (!$('#dream').is(':checked')) {
			alert("드림 스테이션 반납 선택이 되지 않았습니다. ");
			return;
		}else {
			$('#dream').val('Y');
		}
		if (!$('#adapter').is(':checked')) {
			alert("전원 아답터 반납 선택이 되지 않았습니다.");
			return;
		}else {
			$('#adapter').val('Y');
		}
		
		if (!$('#return_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#return_private_yn').val('Y');
		}
		
		if ($('#return_marketing_yn').is(':checked')) {
			$('#return_marketing_yn').val('Y');
		} else {
			$('#return_marketing_yn').val('N');
		}
		
		var address = "";
		address = $("#address1").val() + $("#address2").val();
		$("#address").val(address);

		var zipcode = "";
		zipcode = $("#zipcode1").val();
		$("#zipcode").val(zipcode);

		var frmData = $("#frmReturn").serialize();

		$.ajax({
			url : '/do_return',
			type : 'POST',
			data : frmData,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				if (data.trim() == "success") {
					var dimm = document.getElementById("dimmed");
					dimm.style.display = "block";
				} else {
					alert("입력에 실패하였습니다. 다시 시도해주세요.");
				}
			},
			error : function(xhr, status) {
				alert("입력에 실패하였습니다. 다시 시도해주세요.");
			}
		});

	}
	
	
	//질의 페이지 전송
	function doQna() {
		
		var is_empty = false;

		if ($("#qna_name").val() == null || $("#qna_name").val() == "") {
			alert("이름을 입력하지 않았습니다");
			return;
		}
		if ($("#qna_mobile").val() == null
				|| $("#qna_mobile").val() == "") {
			alert("핸드폰 번호를 입력하지 않았습니다");
			return;
		} else if ((isNaN($("#qna_mobile").val()))) {
			alert("핸드폰 번호는 숫자만 가능합니다.");
			$("#qna_mobile").val("");
			return;
		}
		if ($("#qna_request").val() == null
				|| $("#qna_request").val() == "") {
			alert("요청사항을 입력하지 않았습니다");
			return;
		}
		if (!$('#qna_private_yn').is(':checked')) {
			alert("개인정보의 수집, 이용 동의를 하지 않았습니다.");
			return;
		} else {
			$('#qna_private_yn').val('Y');

		}

		if ($('#qna_marketing_yn').is(':checked')) {
			$('#qna_marketing_yn').val('Y');
		} else {
			$('#qna_marketing_yn').val('N');
		}
		
		var frmData = $("#frmQna").serialize();
		
		$.ajax({
	        url : '/do_qna',
	        type : 'POST',
	        data : frmData,
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success : function(data) {
	        	if(data.trim()=="success"){
	                var dimm = document.getElementById("dimmed");
	                dimm.style.display = "block";
	        	}else{
	        		alert("입력에 실패하였습니다. 다시 시도해주세요.");
	        	}
	        },
	        error : function(xhr, status) {
	        	console.log(xhr);
	        	console.log(status);
	            alert("입력에 실패하였습니다. 다시 시도해주세요.");
	        }
	    });
		
	}
	
	
	