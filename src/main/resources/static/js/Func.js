function toggleActiveClass(element, targetPageIds) {
    // 모든 .card.detect 요소를 선택
    const cardDetectElements = document.querySelectorAll(".card.detect");

    // 클릭한 요소 이외의 모든 .card.detect 요소에서 active 클래스 제거
    cardDetectElements.forEach((el) => {
        if (el !== element) {
            el.classList.remove("active");
        }
    });

    // 클릭한 요소의 active 클래스 상태 토글
    element.classList.toggle("active");

    // 모든 페이지를 숨기는 처리
    const allPageIds = ['pageA1', 'pageA2', 'pageB1', 'pageB2']; // 여기에 모든 페이지의 pageId를 추가하세요.
    allPageIds.forEach((pageId) => {
        document.getElementById(pageId).style.display = 'none';
    });

    // 선택한 페이지를 표시하는 처리
    if (element.classList.contains('active')) {
        targetPageIds.forEach((pageId) => {
            document.getElementById(pageId).style.display = 'block';
        });
    }
}