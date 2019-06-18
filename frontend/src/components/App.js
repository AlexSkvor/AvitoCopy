import React from "react"
import SearchString from "./Search-string"
import ProductsTable from "./ProductsTable"
import AddProduct from "./AddProduct"
function App(props){
    return(
        <div className="app-style">
            <SearchString/>
            <ProductsTable/>
            <AddProduct/>
        </div>
    )
}

export default App