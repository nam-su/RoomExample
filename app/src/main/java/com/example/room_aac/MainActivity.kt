package com.example.room_aac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val TAG = "메인 액티비티"

    lateinit var button: Button
    lateinit var recyclerView: RecyclerView
    lateinit var editTextName: EditText
    lateinit var editTextAge: EditText
    lateinit var editTextHobby: EditText

    lateinit var mAdapter: MAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextHobby = findViewById(R.id.editTextHobby)

        mAdapter = MAdapter()

        // Database 초기화
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration()
            .build()

        // userDao 초기화
        val userDao = db.userDao()


        // Database 데이터 불러오기
        Thread {

            var userList = userDao.getAll()

            mAdapter.userList.addAll(userList)
            recyclerView.adapter = mAdapter

        }.start()

        button.setOnClickListener {

            Thread{

                val user = User(mAdapter.userList.size,editTextName.text.toString(),editTextAge.text.toString(),editTextHobby.text.toString())
                userDao.insertAll(user)

                Handler(Looper.getMainLooper()).post {

                    mAdapter.userList.add(user)
                    mAdapter.notifyDataSetChanged()
                    Toast.makeText(this,"성공적으로 추가했습니다",Toast.LENGTH_SHORT).show()

                }

            }.start()

        }

    }

}