package com.android.sapo.data.model

import com.android.sapo.ui.Sex
import com.google.gson.annotations.Expose

data class User(

        @Expose var username: String = "",
        @Expose var email: String = "",
        @Expose var password: String = "",
        @Expose var city: City? = null,
        @Expose var district: District? = null,
        @Expose var sex: Sex? = null,
        @Expose var age: Int = 25

)