import React from "react"


function Product(props){
    return(
        <div className="card-style" >
            <div className="my-2 mx-auto p-relative bg-white shadow-1 blue-hover" >
                <img src={props.imageUrl} alt={props.tradeMarktradeMark} className="d-block w-full"/>
                <div className="px-2 py-2">
                    <p className="mb-0 small font-weight-medium text-uppercase mb-1 text-muted lts-2px"> {props.contact}</p>
                    <h1 className="ff-serif font-weight-normal text-black card-heading mt-0 mb-1"> {props.tradeMark} {props.model}</h1>
                    <p className="mb-1">Цвет: {props.color}</p>
                    <p className="mb-1">Кузов: {props.bodyType}</p>
                    <p className="mb-1">Год: {props.year}</p>
                    <p className="mb-1">Пробег: {props.mileage}</p>
                    <p className="mb-1">Привод: {props.driveUnit}</p>
                    <p className="mb-1">Руль: {props.steeringSide}</p>
                    <p className="mb-1">Состояние: {props.condition}</p>
                    <p className="mb-1">Владельцев по ПТС: {props.ptsOwners}</p>
                    <p className="mb-1">Коробка : {props.transmission}</p>
                    <p className="mb-1">Место смотра: {props.watchPlace}</p>
                </div>
                <a href="#0" className="text-uppercase  d-inline-block font-weight-medium lts-2px ml-2 mb-2 text-center styled-link">{props.price} ₽</a>
            </div>
        </div>
    )
}

export default Product