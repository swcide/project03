$(document).ready(function () {

    getBoard()
})

function getBoard() {
    var dataSource = null;

    dataSource = `/board`

    $('#tbody').empty();
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
        pageSize: 8,
        showPrevious: true,
        showNext: true,
        ajax: {
            beforeSend: function() {
                $('#tbody').html('상품 불러오는 중...');
            }
        },
        callback: function(data, pagination) {
            $('#tbody').empty();
            for (let i = 0; i < data.length; i++) {
                let product = data[i];
                let tempHtml = addBoard(product);
                $('#tbody').append(tempHtml);
            }
        }
    });
}

function addBoard(board) {

    let date = board.createdAt.split('T')
    console.log(date[0])
    let tempHtml = `<tr>
                        <td>${board.id}</td>
                        <td>
                            <span class"title">
                                <a href="/board/view/detail?id=${JSON.stringify(board.id)}">${board.title}</a>
                             </span>
                        </td>
                        <td>
                            <span class="uName">
                               ${board.username}
                             </span></td>
                        <td>${date[0]}</td>
                        <td>${board.count}</td>
                    </tr>`
    $('#tbody').append(tempHtml)
}






