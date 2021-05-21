package com.project.voluntas

class Data{

    private var title:String? = null
    private var work:String? = null
    private var date:String? = null
    private var key:Int? = null

    constructor(title:String, work:String, date:String, key:Int){
        this.title = title
        this.work = work
        this.date = date
        this.key = key

    }

    fun settitle(title: String){this.title = title}
    fun setwork(work:String){this.work = work}
    fun setdate(date: String){this.date = date}
    fun setkey(key:Int){this.key = key}

    fun gettitle(): String? {return this.title}
    fun getwork(): String? {return this.work}
    fun getdate(): String? {return this.date}
    fun getkey(): Int? {return this.key}

}