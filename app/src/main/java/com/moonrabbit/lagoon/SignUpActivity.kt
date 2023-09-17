package com.moonrabbit.lagoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moonrabbit.lagoon.databinding.ActivitySignUpBinding
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import usermgmt.UserManagementGrpc
import usermgmt.Usermgmt

class SignUpActivity : AppCompatActivity() {

    companion object {
        private const val address = "34.64.189.184"
        private const val port = 50051
    }

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var channel: ManagedChannel
    private lateinit var stub: UserManagementGrpc.UserManagementBlockingStub

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        channel = ManagedChannelBuilder.forAddress(address, port)
            .usePlaintext() // 인증을 사용하지 않을 경우에만
            .build()
        stub = UserManagementGrpc.newBlockingStub(channel)

        binding.btnCreate.setOnClickListener {
            val nickname = binding.edtNickname.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPw.text.toString()

            GlobalScope.launch(Dispatchers.IO) {
                val createUserResult = createUserOnServer(nickname, email, password)

                if (createUserResult) {
                    // MainActivity로 화면 전환
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // 회원가입 실패 메시지 표시
                }
            }
        }
    }

    private fun createUserOnServer(nickname: String, email: String, password: String): Boolean {
        val newUser = Usermgmt.NewUser.newBuilder()
            .setEmail(email)
            .setNickname(nickname)
            .setPwhash(password)
            .build()
        try {
            // gRPC 서버로 데이터 전송 및 응답 받기
            val response = stub.createNewUser(newUser)
            return true
        } catch (e: Exception) {
            // 서버 통신 중 오류 발생 시 처리
            Log.d("SignUpActivity", "createUserOnServer: err")
            return false
        }
    }
}