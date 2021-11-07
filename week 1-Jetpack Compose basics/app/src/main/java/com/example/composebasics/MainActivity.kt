package com.example.composebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme
import kotlin.math.exp

/**
 * Composable func 은 다른 컴포저블 함수나, onCreate 의 setContent 에서 호출 가능.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                MyApp()
            }
        }
    }
}

/**
 * configuration change 에도 state을 살리고 싶다면 remember 대신 rememberSaveable
 *
 */
@Composable
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen { shouldShowOnboarding = false }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

/**
 * Surface의 color만 지정해주었을 뿐인데, 텍스트의 색상도 흰색으로 알아서 바뀐다.
 * Material Component(Surface)가 적절한 텍스트 컬러를 골라 주었기 때문에.
 *
 * modifier
 * => how to lay out, display, or behave within its parent layout
 * => dozens of modifiers which can be used to align, animate, lay out, make clickable or scrollable, transform
 *
 * Modifier.fillMaxWidth()
 *
 * To add multiple modifiers to an element, simply chain them.
 *
 * remember => guard against recomposition.
 *
 * spring 애니메이션을 달아줌 -> 애니메이션 시간과 관련된 인자가 들어가지 않고, 물리적으로 자연스럽게 (?)
 *
 */
@Composable
fun Greeting(name: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    val expanded = remember { mutableStateOf(false) }
    
    Row(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello,")
            Text(
                text = "$name", 
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded.value) {
                Text(text = "아무말이나 해보자\n도대체 무슨 말을 해야하는지 모르겠지만\n호호호")
            }
        }
        IconButton(onClick = { expanded.value = !expanded.value },) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) "Show less" else "Show more"
            )
        }
    }
}

/**
 * shouldShowOnboarding is using a by keyword instead of equal.
 * (property delegate saves you from typing .value every time.)
 *
 */
@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

/**
 * 미리보기를 위해서는 @Preview 어노테이션이 달린 함수 (이때, default parameter 와 미리보기에 필요한 이름 지정)
 */
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasicsTheme {
        MyApp()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview2() {
    ComposeBasicsTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeBasicsTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}