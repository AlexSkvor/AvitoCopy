import React from "react"
import Product from "./Products"
import dataBase from "./dataBase"

function ProductsTable(){
    const productComponents = dataBase.map(product => <Product key={product.id}
                                                               imgURL={product.imgURL}
                                                               mark={product.mark} 
                                                               model={product.model}
                                                               color={product.color}
                                                               engineCapacity={product.engineCapacity}
                                                               price={product.price}
                                                               year={product.year}
                                                               carBody={product.carBody}
                                                               driveUnit={product.driveUnit}
                                                               steeringWheel={product.steeringWheel}
                                                               mileage = {product.mileage}
                                                               contact = {product.contact}
                                                            /> 
                                            )
    
    return(
        <div className="table-style">
            {productComponents}
        </div>
    )
}

export default ProductsTable