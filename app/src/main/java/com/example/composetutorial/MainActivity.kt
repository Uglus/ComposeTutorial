package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Conversation(SampleData.conversationSample)
            }

        }
    }



    @Composable
    fun Conversation(messages: List<Message>){
        LazyColumn {
            items(messages){message->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation(){
        ComposeTutorialTheme {
            Conversation(SampleData.conversationSample)
        }
    }

    @Composable
    fun MessageCard(message: Message){
        Row(modifier = Modifier.padding(all = 8.dp)) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50))
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))


            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            )

            Column(modifier = Modifier.clickable{isExpanded = !isExpanded}) {
                Text(
                    text = message.author,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                    )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)) {
                    Row(modifier = Modifier.padding(all = 4.dp)){
                        Text(text = "Text: ",
                            color = MaterialTheme.colorScheme.secondary,
                            style =  MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = message.body,
                            maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }


            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        name = "Dark mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewMessageCard(){
        ComposeTutorialTheme {
            Surface {
                MessageCard(
                    message = Message(author = "Stepan", body = "Hello World")
                )
            }
        }

    }

}

