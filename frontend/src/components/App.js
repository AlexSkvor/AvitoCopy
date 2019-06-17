import React from "react"
import SearchString from "./Search-string"
import ProductsTable from "./ProductsTable"

function App(props){
    return(
        <div className="app-style">
            <SearchString/>
            <ProductsTable/>
        </div>
    )
}

export default App