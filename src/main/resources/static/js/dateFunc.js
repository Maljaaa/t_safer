(function() {
    var today = new Date();
    var formattedDate = formatDate(today);

    document.getElementById('startDate').value = formattedDate;
    document.getElementById('endDate').value = formattedDate;

    function formatDate(date) {
        var year = date.getFullYear();
        var month = (1 + date.getMonth()).toString();
        month = month.length > 1 ? month : '0' + month;
        var day = date.getDate().toString();
        day = day.length > 1 ? day : '0' + day;

        return year + '-' + month + '-' + day;
    }

    document.getElementById('startDate').addEventListener('change', function() {
        localStorage.setItem('storedStartDate', this.value);
    });

    document.getElementById('endDate').addEventListener('change', function() {
        localStorage.setItem('storedEndDate', this.value);
    });

    loadStoredDates();

    function loadStoredDates() {
        var storedStartDate = localStorage.getItem('storedStartDate');
        var storedEndDate = localStorage.getItem('storedEndDate');

        if (storedStartDate) {
            document.getElementById('startDate').value = storedStartDate;
        }
        if (storedEndDate) {
            document.getElementById('endDate').value = storedEndDate;
        }
    }
})();