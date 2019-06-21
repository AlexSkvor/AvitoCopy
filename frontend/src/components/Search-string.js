import React from "react"

function SearchString(){

    function get_info(e){
        if(e.keyCode==13){
            let searchInfo = document.getElementById('searchInfo').value
            let arrayInfo = searchInfo.split(' ')
            console.log(arrayInfo[0])
         // alert(searchInfo)
        }
    }

    return(
        <div className="search-string">
            <div className="search-holder">
                <input onKeyDown= {get_info} className="search__input" id="searchInfo" type="text" placeholder="Search..."/>
            </div>
        </div>
    )
}

export default SearchString