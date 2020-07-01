package com.classwork.apiassignment

class Like{
    var key: String?
    val userId : String?
    val tvShowId : String?



    constructor(){
        key = null
        userId = null
        tvShowId = null
    }

    constructor(key: String?, userId: String?, tvShowId: String?){
        this.key = key
        this.userId = userId
        this.tvShowId = tvShowId
    }
}