package com.plcoding.mycashtask.yjahz.util

object Constants {

    const val SCROLLER_TAG = "SCROLLER_TAG"
    const val LOGIN_SWITCH_TAG = "LOGIN_SWITCH_TAG"
    const val SIGNUP_SWITCH_TAG = "SIGNUP_SWITCH_TAG"

    val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

}