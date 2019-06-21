import React from "react"
import Product from "./Products"
import dataBase from "./dataBase"
import colorBase from "./colorBase"
import markBase from "./markBase"
import { isUndefined } from "util";

let newDB=[0,1]
function build_url(array){
    let schet = [0,0,0]
    var requestURL = 'http://84.201.139.189:8080/devApi/search/cars?skip=0&take=1000&'
    for (let i = 0; i < array.length; i++) {
        let tmpString = array[i]
        for (let j = 0; j < colorBase.length; j++) {
            if(tmpString.toLocaleLowerCase()==colorBase[j].toLocaleLowerCase()){
                schet[i]=1
                requestURL=requestURL+'colors='+colorBase[j] + '&'
                break
            }
        }
        for (let j = 0; j < markBase.length; j++) {
            if(tmpString.toLocaleLowerCase()==markBase[j].toLocaleLowerCase()){
                schet[i]=1
                requestURL=requestURL+'tradeMarks='+ markBase[j] + '&'
                break
            }
        }

    }
    let model_sum =''
    for(let i = 0; i < array.length; i++) {
        if(schet[i]==0&&!isUndefined(array[i])){
            model_sum+=array[i]
            if(i!=array.length-1) model_sum+=' '
        }
    }

    alert("model is" + model_sum)

    if(model_sum!='') requestURL=requestURL+'models='+model_sum+ '&'

    alert(requestURL)
    return requestURL
}
function get_info(e){
    if(e.keyCode==13){
        let searchInfo = document.getElementById('searchInfo').value
        let arrayInfo = searchInfo.split(' ')
        var requestURL = build_url(arrayInfo)
        var request = new XMLHttpRequest();
        request.open('GET', requestURL,false);
        request.send();
        let DB = request.responseText
        newDB = JSON.parse(DB).data
      //  alert(newDB)
    } 
}

class ProductsTable extends React.Component{
    constructor(){
        super()
        this.state ={
            dataBase:dataBase,
          //  newData:[{}]
        }
        //////////////////////////////////////////
     //   this.get_info = this.get_info.bind(this)
      //  this.addItem = this.addItem.bind(this)
      //  this.my_init = this.my_init.bind(this)
        this.new_get = this.new_get.bind(this)
        this.refresh = this.refresh.bind(this)
        ////////////////////////////////////////// 

        /*//////////////////////////////////////////////////
        for(let key in this.state.dataBase[0]){
            this.state.newData[0][key] = this.state.dataBase[0][key]
        }
        //////////////////////////////////////////
        for(let index = 1; index < 3; index++){
            this.state.newData.push(this.state.dataBase[index])
        }
        */
    }
    /*
    my_init(){
        const tmpData = []
        for(let key in this.state.dataBase[0]){
            tmpData[0][key] = this.state.dataBase[0][key]
        }
        //////////////////////////////////////////
        for(let index = 1; index < 3; index++){
            tmpData.push(this.state.dataBase[index])
        }
        this.setState({
            newData:tmpData
        })
    }
    */
    refresh(){
        if(newDB.length!=2){
            this.setState({
                dataBase:newDB
            })
        }
    }

    new_get(){
        get_info()
        this.setState({
            dataBase:newDB
        })
    }
    
    /*
    addItem(){
        this.setState({
            dataBase:newDB
        })

        if(this.state.newData.length<this.state.dataBase.length-3){
            const addData = []
            for(let i = 0;i<this.state.newData.length;i++) addData.push(this.state.newData[i])
            for (let i = 0; i < 3; i++) addData.push(this.state.dataBase[this.state.newData.length+i])
            this.setState({newData:addData})
        }
        alert(this.state.newData.length)
        alert("newDB len is "+newDB.length)
        alert("dataBase len is "+ this.state.dataBase.length)
    }
    */

    render(){
        return(
            <div className="app-style">
                    <div className="search-string">
                        <div className="search-holder">
                            <input onKeyDown={get_info} autocomplete="off" className="search__input" id="searchInfo" type="text" placeholder="Search..."/>
                        </div>
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
                            {this.state.dataBase.map(product => <Product key={product.id} imageUrl={product.imageUrl} watchPlace={product.additionalInfo.watchPlace} tradeMark={product.tradeMark} model={product.model} color={product.color}  price={product.price} year={product.year} bodyType={product.bodyType} driveUnit={product.driveUnit} condition = {product.additionalInfo.condition} transmission={product.additionalInfo.transmission} ptsOwners={product.additionalInfo.ptsOwners} steeringSide={product.steeringSide} mileage = {product.mileage} /> )}
                        </div>
                    </div>
            </div>
        )
    }
}

export default ProductsTable