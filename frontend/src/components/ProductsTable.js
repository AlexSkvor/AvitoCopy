import React from "react"
import Product from "./Products"
import dataBase from "./dataBase"
import colorBase from "./colorBase"
import markBase from "./markBase"
import { isUndefined } from "util";

let newDB=[]
let flag=-1
function get_info(e){
    if(e.keyCode==13){
        let sort = document.getElementById("sort-select");
        var strUser = sort.options[sort.selectedIndex].value;
     //   alert(strUser)
        let srt = "Дешевые"
        let markInfo = document.getElementById('markInfo').value.toLocaleLowerCase()
        let modelInfo = document.getElementById('modelInfo').value.toLocaleLowerCase()
        let colorInfo = document.getElementById('colorInfo').value.toLocaleLowerCase()
        let minYearInfo = document.getElementById('minYearInfo').value.toLocaleLowerCase()
        let maxYearInfo = document.getElementById('maxYearInfo').value.toLocaleLowerCase()
        let cityInfo = document.getElementById('cityInfo').value.toLocaleLowerCase()
        if(isUndefined(cityInfo)) cityInfo[0] =cityInfo[0].toUpperCase() + cityInfo.slice(1);
   //     alert(typeof(cityInfo[0]))
        if(strUser==="Сначала новые") srt = 'Новые'
        if(strUser==="Сначала старые") srt = 'Старые'
        if(strUser==="Сначала дешевые") srt = 'Дешевые'
        if(strUser==="Сначала дорогие") srt = 'Дорогие'
        if(strUser==="Сначала большой пробег") srt = 'Большой_пробег'
        if(strUser==="Сначала маленький пробег") srt = 'Маленький_пробег'
        var data = JSON.stringify({ 
            "marksAndModels": [ 
            { 
            "mark": markInfo, 
            "models": [ 
            modelInfo 
            ] 
            } 
            ] 
            }); 
        var requestURL = 'http://84.201.139.189:8080/devapi-2/search/cars?&take=100'+'&cities='+ cityInfo + '&colors='+ colorInfo + '&sort='+ srt + '&maxYear='+ maxYearInfo + '&minYear='+ minYearInfo + '&tradeMarksRequest=' + encodeURIComponent(data); 
        var request = new XMLHttpRequest(); 
        console.log(requestURL) 
        request.open('GET', requestURL, false); 
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
        request.send(data); 
        let DB = request.responseText; 
      // console.log(DB); 
        newDB = JSON.parse(DB).data; 
        console.log(newDB);
    } 
}

function click_get_info(){
    let sort = document.getElementById("sort-select");
    var strUser = sort.options[sort.selectedIndex].value;
  //  alert(strUser)
    let srt = "Дешевые"
    let markInfo = document.getElementById('markInfo').value.toLocaleLowerCase()
    let modelInfo = document.getElementById('modelInfo').value.toLocaleLowerCase()
    let colorInfo = document.getElementById('colorInfo').value.toLocaleLowerCase()
    let minYearInfo = document.getElementById('minYearInfo').value.toLocaleLowerCase()
    let maxYearInfo = document.getElementById('maxYearInfo').value.toLocaleLowerCase()
    let cityInfo = document.getElementById('cityInfo').value.toLocaleLowerCase()
    if(isUndefined(cityInfo)) cityInfo[0] =cityInfo[0].toUpperCase() + cityInfo.slice(1);
    if(strUser==="Сначала новые") srt = 'Новые'
    if(strUser==="Сначала старые") srt = 'Старые'
    if(strUser==="Сначала дешевые") srt = 'Дешевые'
    if(strUser==="Сначала дорогие") srt = 'Дорогие'
    if(strUser==="Сначала большой пробег") srt = 'Большой_пробег'
    if(strUser==="Сначала маленький пробег") srt = 'Маленький_пробег'
    var data = JSON.stringify({ 
        "marksAndModels": [ 
        { 
        "mark": markInfo, 
        "models": [ 
        modelInfo 
        ] 
        } 
        ] 
        }); 
    var requestURL = 'http://84.201.139.189:8080/devapi-2/search/cars?&take=100'+'&cities='+ cityInfo + '&colors='+ colorInfo + '&sort='+ srt + '&maxYear='+ maxYearInfo +'&minYear='+ minYearInfo + '&tradeMarksRequest=' + encodeURIComponent(data); 
    var request = new XMLHttpRequest(); 
    console.log(requestURL) 
    request.open('GET', requestURL, false); 
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
    request.send(data); 
    let DB = request.responseText; 
  // console.log(DB); 
    newDB = JSON.parse(DB).data; 
    console.log(newDB);
}


class ProductsTable extends React.Component{
    constructor(){
        super()
        this.state ={
            dataBase:dataBase,
        }
        //////////////////////////////////////////
        this.new_get = this.new_get.bind(this)
        this.refresh = this.refresh.bind(this)
        ////////////////////////////////////////// 
    }

    refresh(){
        
            this.setState({
                dataBase:newDB
            })
        
    }

    new_get(){
        get_info()
        this.setState({
            dataBase:newDB
        })
    }
    
    render(){
        return(
            <div className="app-style">
                    <div className="search-string">
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="markInfo" type="text" placeholder="Марка"/>
                        </div>
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="modelInfo" type="text" placeholder="Модель"/>
                        </div>
                        <div className="search-holder inline">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="minYearInfo" type="text" placeholder="Минимальный год выпуска"/>
                        </div>
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="maxYearInfo" type="text" placeholder="Максимальный год выпуска"/>
                        </div>
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="colorInfo" type="text" placeholder="Цвет"/>
                        </div>
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="cityInfo" type="text" placeholder="Город"/>
                        </div>
                        <form className="check-box-zone"action="#">
                            <ul>
                                <button onClick={click_get_info} className="label">Отсортировать</button>
                                    <select id="sort-select">
                                        <option >Сначала новые</option>
                                        <option>Сначала старые</option>
                                        <option>Сначала дешевые</option>
                                        <option>Сначала дорогие</option>
                                        <option>Сначала большой пробег</option>
                                        <option>Сначала маленький пробег</option>
                                    </select>
                            </ul>
                        </form>
                    </div>
                    <div className="showMore-holder">
                            <div /*onClick={this.addItem}*/ className="frame-btn">
                                <div onClick={this.refresh} className="button">
                                    <span>Refresh</span>
                                    <svg>
                                    <polyline className="o1" points="0 0, 150 0, 150 55, 0 55, 0 0"></polyline>
                                    <polyline className="o2" points="0 0, 150 0, 150 55, 0 55, 0 0"></polyline>
                                </svg>
                                </div>
                            </div>
                    </div>
                    <div className="table-style">
                        <div  className="table-style"> 
                            {this.state.dataBase.map(product => <Product key={product.id} imageUrl={product.imageUrl} dangerouslyLowPrice={product.dangerouslyLowPrice} dangerouslyHighPrice={product.dangerouslyLowPrice} dangerouslyHighMileage={product.dangerouslyHighMileage} dangerouslyLowMileage={product.dangerouslyLowMileage} city={product.city} tradeMark={product.tradeMark} model={product.model} color={product.color}  price={product.price} year={product.year} bodyType={product.bodyType} driveUnit={product.driveUnit} source={product.source} actualizationTime={product.actualizationTime} steeringSide={product.steeringSide} mileage = {product.mileage} /> )}
                        </div>
                    </div>
            </div>
        )
    }
}

export default ProductsTable