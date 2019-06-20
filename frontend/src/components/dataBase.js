
var requestURL = 'http://84.201.139.189:8080/devApi/justCar/avito'
var request = new XMLHttpRequest();
request.open('GET', requestURL,false);

request.send();
let DB = request.responseText
let dataBase = JSON.parse(DB).data
console.log(dataBase)
export default dataBase