function selectAll(selectAll) {
    const checkboxes
        = document.getElementsByName('animal');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}

function deleteRow(rownum) {
    // table element 찾기
    const table = document.getElementById('fruits');

    // 행(Row) 삭제
    const newRow = table.deleteRow(rownum);
}