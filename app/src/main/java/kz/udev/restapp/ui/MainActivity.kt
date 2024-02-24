package kz.udev.restapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.udev.restapp.data.remote.dto.body.LoginBody
import kz.udev.restapp.data.remote.retofit.RetrofitInstance
import kz.udev.restapp.ui.components.TabLayout
import kz.udev.restapp.ui.theme.RestAppTheme
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {

    private val title = mutableStateOf("")
    private val price = mutableStateOf("")
    private val imgUrl = mutableStateOf("")

    private var retrofitInstance: RetrofitInstance? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retrofitInstance = RetrofitInstance()

        setContent {
            RestAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var productId by remember {
                        mutableStateOf("")
                    }

                    var password by remember {
                        mutableStateOf("")
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        TextField(
                            value = productId, onValueChange = {
                                productId = it
                            },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
//                                    if (productId.isNotEmpty()) {
//                                        retrofitInstance(productId)
//                                    }
                                }
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            )
                        )

                        Button(onClick = {
                            if (productId.isNotEmpty()) {
                                login(productId, password)
                            }
                        }) {
                            Text(text = "Click")
                        }

                        Text(text = title.value)

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = price.value)

                        Spacer(modifier = Modifier.height(5.dp))

                        if (imgUrl.value.isNotEmpty()) {
                            AsyncImage(
                                model = imgUrl.value,
                                contentDescription = null
                            )
                        }

                        TabLayout()
                    }
                }
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun login(login: String, password: String) {
        val dummyS = retrofitInstance?.dummyService

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = dummyS?.login(LoginBody(login, password))

                withContext(Dispatchers.Main) {
                    title.value = response?.firstName.toString()
                    price.value = response?.token.toString()
                    imgUrl.value = response?.image.toString()
                }

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

//    @OptIn(DelicateCoroutinesApi::class)
//    private fun searchProduct(search: String) {
//        val dummyS = dummyService()
//
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = dummyS.getSearchProducts("Hello", search)
//
//                if (response.products.isNotEmpty()) {
//                    withContext(Dispatchers.Main) {
//                        title.value = response.products[0].title
//                        price.value = response.products[0].price.toString()
//                        imgUrl.value = response.products[0].thumbnail
//                    }
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            } catch (e: HttpException) {
//                e.printStackTrace()
//            }
//        }
//    }

}