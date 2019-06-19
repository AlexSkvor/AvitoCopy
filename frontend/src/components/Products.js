import React from "react"


function Product(props){
    console.log(props.imgURL)
    return(
        <div className="card-style" >
            <div className="my-2 mx-auto p-relative bg-white shadow-1 blue-hover" >
                <img src={props.imgURL} alt={props.mark} className="d-block w-full"/>
                <div className="px-2 py-2">
                    <p className="mb-0 small font-weight-medium text-uppercase mb-1 text-muted lts-2px"> {props.contact}</p>
                    <h1 className="ff-serif font-weight-normal text-black card-heading mt-0 mb-1"> Audi Q7</h1>
                    <p className="mb-1">Цвет: {props.color}</p>
                    <p className="mb-1">Кузов: {props.carBody}</p>
                    <p className="mb-1">Год: {props.year}</p>
                    <p className="mb-1">Пробег: {props.mileage} км</p>
                    <p className="mb-1">Привод: {props.driveUnit}</p>
                    <p className="mb-1">Руль: {props.steeringWheel}</p>
                </div>
                <a href="#0" className="text-uppercase  d-inline-block font-weight-medium lts-2px ml-2 mb-2 text-center styled-link">{props.price} ₽</a>
            </div>
        </div>
    )
}

export default Product