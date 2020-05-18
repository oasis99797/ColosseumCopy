package com.bklee.colosseumcopy.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageService : FirebaseMessagingService() {

    // FCM서버에서 새 토큰을 발급해주면 실행되는 함수
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("새로발급된 토큰", token)
    }

    // 푸쉬알림이 수신되면 실행
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

}