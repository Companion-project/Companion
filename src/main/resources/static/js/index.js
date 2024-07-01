document.addEventListener('DOMContentLoaded', function() {
    const mainCategoryTabs = document.getElementById('main-category-tabs');
    const categoryContents = document.querySelectorAll('.category-content');

    function showCategory(mainCategory) {
        categoryContents.forEach(content => {
            content.style.display = 'none';
        });
        const selectedCategory = document.getElementById(`category-${mainCategory}`);
        if (selectedCategory) {
            selectedCategory.style.display = 'block';
        }
    }

    mainCategoryTabs.addEventListener('click', function(event) {
        if (event.target.tagName === 'BUTTON') {
            const mainCategory = event.target.getAttribute('data-category');
            mainCategoryTabs.querySelectorAll('button').forEach(button => {
                button.classList.remove('active');
            });
            event.target.classList.add('active');
            if (mainCategory === 'all') {
                categoryContents.forEach(content => {
                    content.style.display = 'block';
                });
            } else {
                showCategory(mainCategory);
            }
        }
    });

    // 페이지 로드 시 첫 번째 대분류 카테고리 선택
    const firstMainCategory = mainCategoryTabs.querySelector('button');
    if (firstMainCategory) {
        firstMainCategory.click();
    }
});