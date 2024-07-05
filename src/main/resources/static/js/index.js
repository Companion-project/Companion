document.addEventListener('DOMContentLoaded', function() {
    const categoryButtons = document.querySelectorAll('#categorynav button');

    categoryButtons.forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            showCategory(category);
        });
    });

    // 페이지 로드 시 전체 카테고리 선택
    showCategory('all');
});

function showCategory(category) {
    const categoryContents = document.querySelectorAll('.categoryContent');
    categoryContents.forEach(content => {
        content.style.display = 'none';
    });
    
    if (category === 'all') {
        document.getElementById('allCategory').style.display = 'block';
    } else {
        document.getElementById(category).style.display = 'block';
    }
}