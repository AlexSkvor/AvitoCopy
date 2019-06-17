import React from "react"


function Product(props){
    console.log(props.imgURL)
    return(
        <div className="product-style">
            <img src={props.imgURL} alt={props.mark}></img>

            <h1>{props.mark} {props.model}</h1>
            <p>Цвет: {props.color}</p>
            <p>Привод: {props.driveUnit}</p>
            <p>Тип кузова: {props.carBody} </p>
            <p>Цена: {props.price} рублей</p>
            <p>Год выпуска: {props.year}</p>
            <p>Руль: {props.steeringWheel}</p>
            <p>Пробег: {props.mileage} км</p>
        </div>
    )
}

export default Product