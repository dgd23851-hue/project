document.addEventListener("DOMContentLoaded", () => {
  const categoryItems = document.querySelectorAll('.category-item');

  categoryItems.forEach(item => {
    item.addEventListener('click', () => {
      categoryItems.forEach(el => el.classList.remove('active'));
      item.classList.add('active');
    });
  });
});

document.querySelectorAll('.category').forEach(item => {
  item.addEventListener('click', function () {
    document.querySelectorAll('.category').forEach(el => el.classList.remove('active'));
    this.classList.add('active');
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const categoryItems = document.querySelectorAll(".category-item");
  const subcategoryContainer = document.querySelector(".subcategory-container");
  const subcategories = document.querySelectorAll(".subcategory");

  let activeCategory = null;

  categoryItems.forEach(item => {
    item.addEventListener("mouseenter", () => {
      const targetId = item.getAttribute("data-sub");

      // 서브카테고리 보여주기
      subcategoryContainer.classList.add("show");

      // 활성화 클래스 조정
      categoryItems.forEach(i => i.classList.remove("active"));
      item.classList.add("active");

      // 서브카테고리 표시
      subcategories.forEach(sub => {
        sub.classList.remove("active");
        if (sub.id === targetId) {
          sub.classList.add("active");
        }
      });
    });
  });

  // 마우스를 전체 바깥으로 이동하면 사라지게
  subcategoryContainer.addEventListener("mouseleave", () => {
    subcategoryContainer.classList.remove("show");
    categoryItems.forEach(i => i.classList.remove("active"));
    subcategories.forEach(sub => sub.classList.remove("active"));
  });

  document.querySelector(".category-bar").addEventListener("mouseleave", () => {
    subcategoryContainer.classList.remove("show");
    categoryItems.forEach(i => i.classList.remove("active"));
    subcategories.forEach(sub => sub.classList.remove("active"));
  });
});

// 로그인

// 로그인 드롭다운 토글
document.addEventListener('DOMContentLoaded', function () {
  const loginBtn = document.querySelector('.login-btn');
  const loginDropdown = document.querySelector('.login-dropdown');
  if (loginBtn && loginDropdown) {
    loginBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      loginDropdown.classList.toggle('open');
    });
    document.addEventListener('click', function () {
      loginDropdown.classList.remove('open');
    });
    // 폼 제출 시 새로고침 방지
    const loginForm = loginDropdown.querySelector('form');
    if (loginForm) {
      loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        alert("로그인 기능은 준비중입니다.");
        loginDropdown.classList.remove('open');
      });
    }
  }
});
