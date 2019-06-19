import React from "react"

function SearchString(){
    return(
        <div className="search-string">
            <div className="search-holder">
                <input name="s" placeholder="Марка" type="search" size="20px"/>
                <input name="s" placeholder="Модель" type="search" size="50px"/>
                <button>Искать</button>
            </div>
        </div>
    )
}

export default SearchString