document.addEventListener('DOMContentLoaded', function() {
    const mainCategoryTabs = document.getElementById('main-category-tabs');
    const subCategoryTabs = document.getElementById('sub-category-tabs');
    const categoryContents = document.querySelectorAll('.sub-category-content');

    function showSubCategories(mainCategory) {
        subCategoryTabs.innerHTML = '';
        if (categoryMap && categoryMap.hasOwnProperty(mainCategory)) {
            const subCategories = Object.keys(categoryMap[mainCategory]);
            subCategories.forEach(subCategory => {
                const button = document.createElement('button');
                button.textContent = subCategory;
                button.setAttribute('data-category', subCategory);
                subCategoryTabs.appendChild(button);
            });
        }
    }

    function showCategory(mainCategory, subCategory) {
        categoryContents.forEach(content => {
            content.style.display = 'none';
        });
        const selectedCategory = document.getElementById(`sub-category-${mainCategory}-${subCategory}`);
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
            showSubCategories(mainCategory);
            if (categoryMap && categoryMap.hasOwnProperty(mainCategory)) {
                const firstSubCategory = Object.keys(categoryMap[mainCategory])[0];
                showCategory(mainCategory, firstSubCategory);
            }
        }
    });

    subCategoryTabs.addEventListener('click', function(event) {
        if (event.target.tagName === 'BUTTON') {
            const mainCategory = document.querySelector('#main-category-tabs .active').getAttribute('data-category');
            const subCategory = event.target.getAttribute('data-category');
            subCategoryTabs.querySelectorAll('button').forEach(button => {
                button.classList.remove('active');
            });
            event.target.classList.add('active');
            showCategory(mainCategory, subCategory);
        }
    });

    // 페이지 로드 시 첫 번째 대분류 카테고리 선택
    const firstMainCategory = mainCategoryTabs.querySelector('button');
    if (firstMainCategory && categoryMap) {
        firstMainCategory.classList.add('active');
        const mainCategory = firstMainCategory.getAttribute('data-category');
        showSubCategories(mainCategory);
        if (categoryMap.hasOwnProperty(mainCategory)) {
            const firstSubCategory = Object.keys(categoryMap[mainCategory])[0];
            showCategory(mainCategory, firstSubCategory);
        }
    }
});