package com.belajar.aplikasicuaca.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun InputKeyboard(
    value:String,
    onValue:(String) -> Unit,
    onAction:()->Unit,
    closeSearch:() -> Unit
) {
    Spacer(modifier = Modifier.height(3.dp))
    OutlinedTextField(value = value,
        onValueChange = onValue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(20.dp),
        trailingIcon = {
           IconButton(onClick = { closeSearch.invoke() }) {
               Icon(imageVector = Icons.Default.Close,
                   contentDescription = "Icon Close" )
           }
        },
        placeholder = {
            Text(text = "Cari disini")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            onAction.invoke()
        }
    )
    Spacer(modifier = Modifier.height(3.dp))

}