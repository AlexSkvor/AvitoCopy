import React from "react"


function SearchString(){
    return(
        <div className="search-string">
            <div className="search-holder">
                <input name="s" placeholder="Давай поищем..." type="search" size="50px"/>
                <button>Искать</button>
            </div>
        </div>
    )
}

export default SearchString