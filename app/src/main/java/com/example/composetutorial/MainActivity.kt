package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard("Hello World")
        }
    }

    @Composable
    fun MessageCard(textMessage:String){
        Text(text = "Text of message: $textMessage")
    }

    @Preview
    @Composable
    fun PreviewMessageCard(){
        MessageCard("Hello Preview")
    }

}

