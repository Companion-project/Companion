// 게시글 수에 따른 그리드 행 조정
function adjustGridRows() {
    // 게시글 영역 선택
    const boardPost = document.querySelector('.goods-frame');

    // 게시글 수 확인
    const postCount = boardPost.children.length;

    // 한 행에 포함되는 게시글 수
    const columnsPerRow = 3; // 그리드의 열 수

    // 행의 수 계산
    const rowCount = Math.ceil(postCount / columnsPerRow);

    // grid-template-rows 속성 조정
    boardPost.style.gridTemplateRows = `repeat(${rowCount}, 1fr)`;
}

// 페이지 로드 후 그리드 행 조정
window.onload = function() {
    adjustGridRows();
};