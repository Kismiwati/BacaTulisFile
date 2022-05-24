package com.kismiwati.bacatulisfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)

        //method OnClickListener dipanggil bila pengguna menyentuh item save
        btnSave.setOnClickListener(View.OnClickListener {
            // method toString digunakan untuk merubah nilai type data menjadi type data string.
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            //fileOutputStream digunakan untuk membuat file dan menulis data yang akan diberikan.
            //openfileOutput digunakan untuk mengembalikan instance kelas fileOutputStream
            //fileOutputStream.write digunakan untuk menulis data kedalam file
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        })

        //openfileInput digunakan untuk membaca file
        //fileInputStream digunakan untuk mengembalikan instance kelas
        //BufferedReader digunakan untuk membaca data dari file
        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }

                fileData.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"nama file tidak boleh kosong",Toast.LENGTH_LONG).show()
            }
        })

    }
}