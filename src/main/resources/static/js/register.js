	/*
	 	- 회원가입 버튼을 클릭하기
		- 닉네임, 비밀번호, 비밀번호 확인을 입력하기
		- 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
		- 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
		- 비밀번호 확인은 비밀번호와 정확하게 일치하기
		- 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 프론트엔드에서 보여주기
		- 회원가입 버튼을 누르고 에러메세지가 발생하지 않는다면 `로그인 페이지`로 이동시키기
	 */

	//가입하기
	//client, server 둘다 유효성 및 조건 체크
	function register() {
		let userId = $('#register-form-userId').val();
		let email = $('#register-form-email').val();
		let username = $('#register-form-name').val();
		let password = $('#register-form-password').val();
		let passwordNen = $('#register-form-repassword').val();
		let passwordConfirm = $('#register-form-password').val();
		let data = {
			'userId': userId,
			'email': email,
			'username': username,
			'password': password,
			'passwordNen': passwordNen,
			'passwordConfirm': passwordConfirm
		};
		console.log(data)
	    //닉네임 체크
		if (checkName(userId) != "") {
            alert(checkName());
            return false;
        }

		//비밀번호 체크
        if (checkPass() != "") {
            alert(checkPass());
            return false;
        }
        console.log("??????")
       // 닉네임 중복 체크 (중복체크를 위해 db조회 필요 ajax 활용)
       // ajax 특성상 비동기 처리이기 때문에 중복확인 ajax 후에 회원가입 ajax가 실행되지 않도록 콜백으로 처리.
       //  checkNameDuplication(function(response){
    	// 	if(response.username){
    	// 		alert("닉네임 중복 입니다.");
    	// 		return false;
    	// 	}else{
    			//회원가입
    			 $.ajax({
			        type:'POST',
			        url:'/member/signup',
			        contentType:'application/json',
			        data: JSON.stringify(data),
			        async : false,
			        beforeSend : function(xhr, opts) {

			        },
			        success: function (response){
			            alert("회원 가입이 완료 되었습니다!");
			            window.history.back();
			        },
			        error : function(err){

			        }
			    });
    		// }
    	// });
	}

	// 닉네임 체크 (정규식 활용)
	function checkName(userId){
		let err_msg = "";
		// let userId = $('#register-form-userId').val();
		console.log(userId)
		let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{3,20}$/; //  3 ~ 20자 영문, 숫자 조합

		if (userId === '') {
	    	err_msg = '닉네임을 적어주세요!';
	    } else if(!regExp.test(userId)){
			err_msg = "닉네임은 숫자와 영문자 조합으로 3~20자리를 사용해야 합니다.";
	    }
		return err_msg;
	}

	// 닉네임 중복 체크 (ajax db 조회 체크)
	function checkNameDuplication(callback){
		let userId = $('#register-form-userId').val();
		 $.ajax({
	        type:'GET',
	        url:`/member/${userId}`,
			 contentType: 'application/json',
	        beforeSend : function(xhr, opts) {
	        },
	        success: function (response){
	        	callback(response); //동기화 처리
	        }
	    });
	}

	//비밀번호 체크
	function checkPass(){
		let err_msg = "";
		let username = $('#register-form-name').val();
		let password = $('#register-form-password').val();
		let passwordConfirm = $('#register-form-repassword').val();

		if (password == '' || passwordConfirm == '') {
	    	err_msg = '패스워드를 적어주세요!';
	    } else if(password.length < 4 || password.length > 20){
			err_msg = '비밀번호는  4~20자리를 사용해야 합니다.';
	    } else if(password.indexOf(username) != -1){
			err_msg = "비밀번호에 닉네임을 포함할 수 없습니다.";
		} else if (password != passwordConfirm) {
	    	err_msg = '패스워드가 일치하지 않습니다!';
	    }

		return err_msg;
	}

