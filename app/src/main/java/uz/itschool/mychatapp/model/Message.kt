package uz.itschool.mychatapp.model

data class Message(
    val to:String?,
    val from:String?,
    val text:String?,
    val date:String?,
){
    constructor() :this(null,null,null,null,)
}
