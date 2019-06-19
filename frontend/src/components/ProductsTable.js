import React from "react"
import Product from "./Products"
import dataBase from "./dataBase"

class ProductsTable extends React.Component{
    constructor(){
        super()
        this.state ={
            dataBase:dataBase,
            newData:[{}]
        }
        //////////////////////////////////////////
        this.addItem = this.addItem.bind(this)
        //////////////////////////////////////////  
        for(let key in this.state.dataBase[0]){
            this.state.newData[0][key] = this.state.dataBase[0][key]
        }
        //////////////////////////////////////////
        for(let index = 1; index < 3; index++){
            this.state.newData.push(this.state.dataBase[index])
        }
    }

    addItem(){
        if(this.state.newData.length<this.state.dataBase.length-3){
            const addData = []
            for(let i = 0;i<this.state.newData.length;i++){
                addData.push(this.state.newData[i])
            }
            for (let i = 0; i < 3; i++) {
                addData.push(this.state.dataBase[this.state.newData.length+i])
            }
            
            this.setState({
                newData:addData
            })
        }
    }

    render(){
        return(
            <div className="table-style">
                <div className="table-style"> 
                    {this.state.newData.map(product => <Product key={product.id} imgURL={product.imgURL} mark={product.mark} model={product.model} color={product.color} engineCapacity={product.engineCapacity} price={product.price} year={product.year} carBody={product.carBody} driveUnit={product.driveUnit} steeringWheel={product.steeringWheel} mileage = {product.mileage} contact = {product.contact} /> )}
                </div>
                <div className="showMore-holder">
                    <button onClick={this.addItem} className="showMore-btn" ><p>Показать еще обьявления</p></button>
                </div>
            </div>
        )
    }
}

export default ProductsTable