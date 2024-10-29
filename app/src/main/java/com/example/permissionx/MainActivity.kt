package com.example.permissionx

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.permissionx.databinding.ActivityMainBinding
import com.permissionx.fdjfk2.PermissionCallback
import com.permissionx.fdjfk2.PermissionX


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.makeCallBtn.setOnClickListener{
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE){ allGranted, deniedList ->
                    if(allGranted){
                        call()
                    }else{
                        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun call(){
        try{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}