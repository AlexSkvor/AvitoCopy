var requestURL = 'http://84.201.139.189:8080/devapi-2/info/cars/count'
var request = new XMLHttpRequest();
request.open('GET', requestURL,false);
request.send();
let CB = request.responseText
let countAuto= JSON.parse(CB).data[0]
export default countAuto