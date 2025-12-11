package com.example.rentacarapp.ui.info


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentacarapp.R
import com.example.rentacarapp.data.local.FaqData

@Composable
fun InfoScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.mainColor))
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.info),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        HorizontalDivider(thickness = 4.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn(contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp))
        {
            item {
                ExpandableCard(
                    title = stringResource(R.string.faq),
                    content = { FaqContent() }
                )
            }
            item{
                ExpandableCard(
                    title = stringResource(R.string.about),
                    content = { AboutContent() }
                )
            }
        }
    }
}

@Composable
fun ExpandableCard(
    title: String,
    content: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180F else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded =! isExpanded },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.darker_mainColor))
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .rotate(rotationState)
                        .size(35.dp)
                )
            }
            AnimatedVisibility(visible = isExpanded)
            {
                Column(modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)) {
                    content()
                }
            }
        }
    }
}


@Composable
fun FaqContent() {

    val configuration = LocalConfiguration.current // screen max size
    val maxHeight = (configuration.screenHeightDp.dp * 0.5f)
    Column(modifier = Modifier.fillMaxWidth()
        .heightIn(max = maxHeight)
        .verticalScroll(rememberScrollState())) {
        FaqData.Questions.forEach { fAQItem ->
            Text(text = stringResource(id = fAQItem.question),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = fAQItem.answer),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White)
            if(fAQItem != FaqData.Questions.last()){
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun AboutContent(){

    //val uriHandler = LocalUriHandler.current
    val githubUrl = stringResource(R.string.github_profile_url)

    val configuration = LocalConfiguration.current // screen max size
    val maxHeight = (configuration.screenHeightDp.dp * 0.5f)

    val githubLink = buildAnnotatedString {
        append("Source code and profile: ")
        withLink(
            LinkAnnotation.Url(
                url = githubUrl,
                styles = TextLinkStyles(style = SpanStyle(color = Color.White,
                    textDecoration = TextDecoration.Underline))
            )
        ){
            append("GitHub")
        }
    }

    Column(modifier = Modifier.fillMaxWidth()
        .heightIn(max = maxHeight)
        .verticalScroll(rememberScrollState())) {
        Text( text = stringResource(R.string.about_developed_by),
            fontSize = 16.sp,
            color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = githubLink,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(navController = rememberNavController())
}