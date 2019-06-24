function req(str){
    var requestURL = 'http://84.201.139.189:8080/devapi-2/search/cars?take=3&tradeMarks=' + str
    var request = new XMLHttpRequest();
    request.open('GET', requestURL,false);
    request.send();
    let DB = request.responseText
    let dataBase = JSON.parse(DB).data
    return dataBase
}

let dataBase = req("")
export default dataBase