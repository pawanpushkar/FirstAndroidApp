package com.example.firstandroidapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApiScreen()
        }
    }

    @Composable
    fun ApiScreen() {

        var result by remember { mutableStateOf("Click button to fetch API") }

        Column(modifier = Modifier.padding(20.dp)) {

            Button(onClick = {

                val url = "https://jsonplaceholder.typicode.com/posts/1"

                val queue = Volley.newRequestQueue(this@MainActivity)

                val request = JsonObjectRequest(
                    Request.Method.GET, url, null,
                    { response ->

                        val title = response.getString("title")
                        val body = response.getString("body")

                        result = "Title: $title\n\nBody: $body"

                    },
                    {

                        Toast.makeText(
                            this@MainActivity,
                            "API Error",
                            Toast.LENGTH_SHORT
                        ).show()

                    })

                queue.add(request)

            }) {

                Text("Fetch API Data")

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(result)

        }
    }
}