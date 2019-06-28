import React from "react"

/*
 { props.dangerouslyLowPrice===true&&
                        <p className="danger">Warning:подозрительно низкая цена</p>
                    }
                    { props.dangerouslyHighPrice===true&&
                        <p className="danger">Warning:подозрительно высокая цена</p>
                    }
                    { props.dangerouslyLowMileage===true&&
                        <p className="danger">Warning:слишком маленький пробег</p>
                    }
                    { props.dangerouslyHighMileage===true&&
                        <p className="danger">Warning:слишком большой пробег</p>
                    }
                    


*/

/*
{
"id":0,
"originalUrl":"https://www.avito.ru/mamonovo/avtomobili/mazda_xedos_6_1994_1189590125",
"imageUrl":"//14.img.avito.st/208x156/5073662014.jpg",
"tradeMark":"mazda",
"model":"xedos 6",
"color":"белый",
"driveUnit":"передний",
"price":1000,
"year":1994,
"bodyType":"седан",
"steeringSide":"левый",
"mileage":350001,
"comment":"машина после дтп на запчасти есть новая запчасть на сцепление также имеется буфер на продажу фото заднего вида по просьбе могу сбросить(целый)",
"actualizationTime":"06-24-00-40",
"source":"Avito",
"city":"mamonovo",
"dangerouslyLowPrice":true,
"dangerouslyHighPrice":false,
"dangerouslyHighMileage":false,
"dangerouslyLowMileage":false
}

*/
function Product(props){
    return(
        <div className="card-style" >
            <div className="my-2 mx-auto p-relative bg-white shadow-1 blue-hover" >
                <img src={props.imageUrl} alt={props.tradeMarktradeMark} className="d-block w-full"/>
                <div className="px-2 py-2">
                    <p className="mb-0 small font-weight-medium text-uppercase text-muted lts-2px"> {props.contact}</p>
                    <h1 className="ff-serif font-weight-normal text-black card-heading mt-0 mb-1"> {props.tradeMark} {props.model}</h1>
                    <p className="mb-1">Цвет: {props.color}</p>
                    <p className="mb-1">Кузов: {props.bodyType}</p>
                    <p className="mb-1">Год: {props.year}</p>
                    <p className="mb-1">Пробег: {props.totalMileage}</p>
                    <p className="mb-1">Привод: {props.driveUnit}</p>
                    <p className="mb-1">Руль: {props.steeringSide}</p>
                    <p className="mb-1">Пробег: {props.totalMileage} км</p>
                    <p className="mb-1">Дата обьявления: {props.actualizationTime}</p>
                    <p className="mb-1">Место смотра: {props.city}</p>
                    
                    <p>Источник: {props.source}</p>
                    <p style={{display: Math.abs((props.price*100)/props.medianPrice-100)>40 && props.medianPrice> props.price ? "intherit" : "none"}} className="danger">Warning: подозрительно низкая цена</p>
                    <p style={{display: Math.abs((props.price*100)/props.medianPrice-100)>40 && props.medianPrice< props.price ? "intherit" : "none"}} className="danger">Warning: завышенная цена</p>
                    <p style={{display: props.mileagePerYear>20000 ? "intherit" : "none"}} className="danger">Warning: подозрительно большой пробег</p>
                    <p style={{display: props.mileagePerYear<10000  ? "intherit" : "none"}} className="danger">Warning: подозрительно маленький пробег</p>
                </div>
                <a href="#0" className="text-uppercase  d-inline-block font-weight-medium lts-2px ml-2 mb-2 text-center styled-link">{props.price} ₽</a>
            </div>
        </div>
    )
}

export default Product