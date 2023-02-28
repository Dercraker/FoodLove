package com.masterclass.FoodLove.Domain

import java.io.Serializable

class FoodDomain : Serializable {
    var title: String
    var pic: String
    var desc: String
    var fee: Double
    var numberInCart = 0

    constructor(title: String, pic: String, desc: String, fee: Double) {
        this.title = title
        this.pic = pic
        this.desc = desc
        this.fee = fee
    }

    constructor(title: String, pic: String, desc: String, fee: Double, numberInCart: Int) {
        this.title = title
        this.pic = pic
        this.desc = desc
        this.fee = fee
        this.numberInCart = numberInCart
    }
}