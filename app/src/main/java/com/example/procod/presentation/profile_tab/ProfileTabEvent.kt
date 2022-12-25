package com.example.procod.presentation.profile_tab

sealed class ProfileTabEvent {
    object Refresh: ProfileTabEvent()
    object EditProfile: ProfileTabEvent()
    object CancelProfile: ProfileTabEvent()
    object SubmitProfile: ProfileTabEvent()
    object DeleteProfile: ProfileTabEvent()
    object Logout: ProfileTabEvent()
    data class OnNameChange(val query: String): ProfileTabEvent()
    data class OnEmailChange(val query: String): ProfileTabEvent()
    data class OnPassChange(val query: String): ProfileTabEvent()
    data class Delete(val id: Int): ProfileTabEvent()
}
