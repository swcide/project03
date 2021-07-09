
$(document).ready(function () {
    getId()


})

function getId() {
    let id = location.search.split('=')[1]
    getDetail(id)
}


function getDetail(id) {
    $.ajax({
        type: 'GET',
        url: `/board/detail/${id}`,
        success: function (response) {
            addDetail(response['id'],response['userId'],response['username'], response['title'], response['contents'], response['modifiedAt'])
        }
    })
}
function addDetail(id,userId, username, title, contents, modifiedAt) {


    let date = modifiedAt.split('T')
    let detial_title = $('#detail_title')
    let detial_content =$('#detail_contents')
    let detail_name =$('#detail_name')
    let detial_date =$('#detial_date')
    let writer_id = $('#writer').val()
    detial_title.append('<input type="hidden" id="writer" value="'+userId+'">')
    detial_title.text(title);
    detial_content.append(contents);
    detail_name.append(username);
    detial_date.append(date[0]);
    console.log(session_User)
    console.log(writer_id)

    if (session_id === userId) {
        let temp =
            `
           <ul class=" float-right">
                        <li><a href="javascript:void(0)" onclick="update(${id})"><i class="icon-legal"></i> 수정</a></li>
                        <li><a href="javascript:void(0)" onclick="bdelete(${id})"><i class="icon-line-trash-2"></i> 삭제</a></li>
                      </ul>`
    $("#update_delete").append(temp)
    }

}


function update(id) {
    var test = confirm("수정하시겠습니까?")

    if(test==true){

        location.href="/board/view/update?id="+id

    }
}
function bdelete(id) {
    var test = confirm("삭제하시겠습니까?")
    var bId = $("#bId").val()
    if(test==true){
        location.href="bdelete.do?bId="+bId;
    }
}



