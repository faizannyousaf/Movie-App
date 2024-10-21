package com.example.movieapp.ui.theme

const val DETAIL_ARGUMENT_KEY = "id"
sealed class Screens (val route : String){
    object Home : Screens("home_screen")
    object Detail : Screens("detail_screen/{$DETAIL_ARGUMENT_KEY}"){
        fun passID(id : Int) : String{
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id.toString())
        }
    }
}
