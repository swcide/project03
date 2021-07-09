$(document).ready(function (){
    getComment()
    console.log("??1")

})



function getComment() {
    let boardId = location.search.split('=')[1]
    console.log("??2")
    var dataSource = null;

    dataSource =`/comment/${boardId}`

    $('#comment_content').empty();
    $('#pagination').pagination({
        dataSource,
        locator: 'content',
        alias: {
            pageNumber: 'page',
            pageSize: 'size'
        },
        totalNumberLocator: (response) => {
            return response.totalElements;
        },
        pageSize: 10,
        showPrevious: true,
        showNext: true,
        ajax: {
            beforeSend: function() {
                $('#comments-list').html('<span>등록된 댓글이 없습니다.</span>');
            }
        },
        callback: function(data, pagination) {
            $('#comments-list').empty();
            $('#comments-list')
            for (let i = 0; i < data.length; i++) {
                let comments = data[i];
                console.log(comments)
                let tempHtml = addComments(comments);
                $('#comments-list').append(tempHtml);
            }
        }
    });
}

function addComments(comment){
    console.log("??3")
    let date = comment.createdAt.split('T')
    return `
          <li class="comment even thread-even depth-1" id="">
          <input type="hidden" value="${comment.userId}" id="comment-${comment.userId}">
            <div id="comment-1" class="comment-wrap clearfix">
              <div class="comment-content clearfix">
                <div class="comment-author">
                    <div class="entry-meta">
                    <ul>
                        <li style="margin: 0"><i class="icon-line-github"></i>
                         ${comment.username}
                         </li>
                         <li style="margin: 0;padding-left: 25px;"><i class="icon-line-calendar" ></i> ${date[0]}</li>
                    </ul>
                    </div>
                    <span>
                <hr>
                
                </span></div>
                
                <h5>${comment.contents}</h5>
              </div>
              <div class="clear"></div>
            </div>
          </li>`


}








function commentwrite() {
    let boardId = location.search.split('=')[1]
    let contents = $('#comment_content').val()

    if (session_id ==''){
        alert("로그인하쇼 거 쉽게살려고 하지말고")
        return
    }
    if (contents == '') {
        alert('제목을 적어주세요!')
        return
    }
    console.log("user"+session_User)
    console.log("id"+session_id)
    console.log(contents)
    let data = {
        'board': boardId,
        'member': session_id,
        'username': session_User,
        'contents': contents
    }
    $.ajax({
        type: 'POST',
        url: '/comment/write',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            alert("게시물 작성이 완료 되었습니다!")
            window.location.reload()
        }
    })
}

