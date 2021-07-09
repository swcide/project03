// //카카오톡 개발자 홈페이지에서 개인적으로 등록 필요 (https://developers.kakao.com/console/app)
// $(document).ready(function(){
//
// });
//
// //카카오톡 로그인 클릭
// function kakaoLogin(){
// 	Kakao.init('ceda1cb94e0658825f7cf401b9bc2d66');
// 	if(Kakao.Auth.getAccessToken() != null){
// 		kakaoLoginSuccess(checkToken);
// 	}
//
// 	Kakao.Auth.login({
// 	  persistAccessToken: true,
// 	  success: (auth) => {
// 		  kakaoLoginSuccess(checkToken);
// 	  },
// 	  fail: (err) => {
// 	    console.error(err);
// 	  }
// 	})
// }
//
// //카카오 로그인 성공 후 닉네임 저장 index 페이지로 이동
// function kakaoLoginSuccess(callback){
// 	console.log("석세스")
// 	Kakao.API.request({
// 	    url: '/v2/user/me',
// 	    success: function(response) {
//
// 	    	callback(response.properties.nickname);
// 	    },
// 	    fail: function(error) {
// 	        console.log(error);
// 	    }
// 	});
// }
//
// //카카오톡 로그인 여부 체크 (로그인 상태면 index 로 이동)
// function checkToken(nickname){
// 	console.log("토큰체크")
// 	  $.ajax({
// 	        url:'member/kakao/token',
// 	        type:'GET',
// 	        contentType:'application/json',
// 	        data : {'token': Kakao.Auth.getAccessToken(), username:nickname},
// 	        beforeSend: function (xhr) {
// 	        },
// 	        success: function (response){
// 	           if(response == 200){
// 				   sessionStorage.getItem();
//
// 	           }
// 	        }
// 	    });
// }
//
// function logout(){
// 	Kakao.Auth.logout();
// 	setTimeout(function(){
// 		location.href = "/logout";
// 	},200);
// }