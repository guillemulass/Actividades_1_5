package com.example.actividades_1_5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
Actividad 1:
Hacer que el texto del botón muestre "Cargar perfil", pero cambie a "Cancelar"
cuando se muestre la línea de progreso... Cuando pulsemos "Cancelar" vuelve al texto por defecto.
*/
@Composable
fun Actividad1() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var txtBtn by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        txtBtn = if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
            "Cancelar"
        } else{
            "Cargar Perfil"
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = txtBtn)
        }
    }
}

/*
Actividad 2:
Modifica ahora también que se separe el botón de la línea de progreso 30 dp,
pero usando un estado... es decir, cuando no sea visible no quiero que tenga la separación
aunque no se vea.
*/
@Composable
fun Actividad2() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var txtBtn by rememberSaveable { mutableStateOf("") }
    var dpValue by rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            txtBtn = "Cancelar"
            dpValue = 30
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp, bottom = dpValue.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )

        } else{
            txtBtn = "Cargar Perfil"
            dpValue = 0
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = txtBtn)
        }
    }
}

/*
Actividad 3:
- Separar los botones entre ellos 10 dp, del indicador de progreso 15 dp y centrarlos horizontalmente.
- Cuando se clique el botón Incrementar, debe añadir 0.1 a la propiedad progress y quitar 0.1
  cuando se pulse el botón Decrementar.
- Evitar que nos pasemos de los márgenes de su propiedad progressStatus (0-1)
*/
@Composable
fun Actividad3() {

    var progressValue by rememberSaveable { mutableStateOf(0f) }


    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = progressValue)

        Row(Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                if (progressValue < 1f){
                    progressValue += 0.1f
                }
            }
            ) {
                Text(text = "Incrementar")
            }
            Button(onClick = {
                if (progressValue > 0.1f){
                    progressValue -= 0.1f
                }
            },
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(text = "Reducir")
            }
        }
    }
}


/*
Actividad 4:
Sitúa el TextField en el centro de la pantalla y haz que reemplace el valor de una coma por un punto
y que no deje escribir más de un punto decimal...
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad4() {
    var myVal by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = myVal,
            onValueChange = { newValue ->
                val commaChanger = newValue.replace(',', '.')

                val dotCounter = commaChanger.count { it == '.' } > 1

                if (!dotCounter){
                    myVal = commaChanger
                }
            },
            label = { Text(text = "Importe") }
        )
    }
}


/*
Actividad 5:
Hecho :
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.

Por hacer :
Haz lo mismo, pero elevando el estado a una función superior
usando un componente OutlinedTextField al que debes añadir un padding alrededor de 15 dp
y establecer colores diferentes en los bordes cuando tenga el foco y no lo tenga.

*/
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad5() {
    var myVal by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = myVal,
            onValueChange = { newValue ->
                val filteredValue = newValue.filter { it.isDigit() || it == '.' || it == ',' }

                val commaChanger = filteredValue.replace(',', '.')

                val dotCounter = commaChanger.count { it == '.' } > 1

                if (!dotCounter){
                    myVal = commaChanger
                }
            },
            label = { Text(text = "Importe") }
        )
    }
}
