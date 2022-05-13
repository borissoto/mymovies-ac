package com.gamla.mymovies.ui.detail

import com.gamla.mymovies.model.Movie

class DetailPresenter{
    private var view: View? = null

    interface View{
        fun updateUI(movie: Movie)
    }

    fun onCreate(view: View, movie: Movie){
        this.view = view
        view.updateUI(movie)
    }

    fun onDestroy(){
        view = null
    }
}