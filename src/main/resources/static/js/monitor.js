var myHeaders = new Headers();
const token = localStorage.getItem("token");
myHeaders.append("Authorization", token);

var raw = "";

var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
};

fetch("http://localhost:8080/monitor", requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));