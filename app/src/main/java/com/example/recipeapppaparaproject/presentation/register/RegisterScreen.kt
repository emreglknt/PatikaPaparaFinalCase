package com.example.recipeapppaparaproject.presentation.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.recipeapppaparaproject.R
import com.example.recipeapppaparaproject.presentation.AuthViewModel.AuthViewModel
import com.example.recipeapppaparaproject.presentation.login.AnimationComponent
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(navController = rememberNavController())
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordMatchError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val gradient = Brush.horizontalGradient(listOf(Color(0xFFFF8C61), Color(0xFF5C374C)))
    val registerResult = viewModel.registerResult.collectAsState(initial = null)
    val context = LocalContext.current

    val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    fun validateEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isUpperCase() }
    }

    Image(
        painter = painterResource(id = R.drawable.foodpattern),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White.copy(alpha = 0.3f)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AnimationComponent(lottieAnimation = R.raw.anim)
            }
            Spacer(modifier = Modifier.height(5.dp))
            HeadingTextComponent(heading = "Register")
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                MyTextField(labelVal = "E-mail", Icons.Default.Email, textVal = email) {
                    email = it
                    emailError = !validateEmail(email)
                }
                if (emailError) {
                    Text(
                        text = "Please enter a valid email address",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                PasswordTextComponent(labelVal = "Password", password = password) {
                    password = it
                    passwordError = !validatePassword(password)
                }
                if (passwordError) {
                    Text(
                        text = "Password must contain at least 8 characters, 1 uppercase letter and 1 number",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                PasswordTextComponent(labelVal = "Confirm Password", password = confirmPassword) {
                    confirmPassword = it
                    passwordMatchError = confirmPassword != password
                }
                if (passwordMatchError) {
                    Text(
                        text = "Passwords do not match",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    GradientButton(
                        text = "Register",
                        onClick = {
                            emailError = !validateEmail(email)
                            passwordError = !validatePassword(password)
                            passwordMatchError = password != confirmPassword

                            if (!emailError && !passwordError && !passwordMatchError) {
                                scope.launch {
                                    viewModel.signup(email, password)
                                    if (registerResult.value != null) {
                                        navController.navigate("main_screen")
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Registration Failed",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        },
                        isEnabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                    BottomRegisterTextComponent(
                        initialText = "If you already have an account, go to ",
                        action = "Login!",
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit, isEnabled: Boolean) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFF8C61), Color(0xFF5C374C)),
        startX = 0f,
        endX = 1000f
    )
    val backgroundColor = if (isEnabled) gradientBrush else Brush.horizontalGradient(
        listOf(
            Color.Gray,
            Color.LightGray
        )
    )

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(brush = backgroundColor, shape = RoundedCornerShape(22.dp)),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun BottomRegisterTextComponent(initialText: String, action: String, navController: NavController) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF985277))) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = Color(0xFFFF8C61), fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = action, annotation = action)
            append(action)
        }
    }
    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.let { span ->
                if (span.item == "Login!") {
                    navController.navigate("login_screen")
                }
            }
    })
}

@Composable
fun HeadingTextComponent(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 39.sp,
        color = Color(0xFF985277),
        fontWeight = FontWeight.Bold
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    labelVal: String,
    icon: ImageVector,
    textVal: String,
    onValueChange: (String) -> Unit
) {
    val typeOfKeyboard: KeyboardType = when (labelVal) {
        "email ID" -> KeyboardType.Email
        "mobile" -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    OutlinedTextField(
        value = textVal,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF8C61),
            unfocusedBorderColor = Color(0xFF985277),
            focusedLeadingIconColor = Color(0xFFFF8C61),
            unfocusedLeadingIconColor = Color(0xFF985277)
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Color(0xFF985277))
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "at_symbol"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = typeOfKeyboard,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextComponent(labelVal: String, password: String, onValueChange: (String) -> Unit) {
    var isShowPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF8C61),
            unfocusedBorderColor = Color(0xFF985277),
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Color(0xFF985277))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "lock"
            )
        },
        trailingIcon = {
            val description = if (isShowPassword) "Hide password" else "Show password"
            val iconImage = if (isShowPassword) Icons.Filled.Lock else Icons.Filled.Clear
            IconButton(onClick = {
                isShowPassword = !isShowPassword
            }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true
    )
}
