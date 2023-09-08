package ir.bluesource.test2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pack = intent.getStringExtra("package") ?: "none"
        val act = intent.getStringExtra("act") ?: "none"


        CoroutineScope(Dispatchers.Main).launch {

            delay(2000)

            try {

//                val launchIntent = this@MainActivity.packageManager.getLaunchIntentForPackage(pack)
//                launchIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                val launchIntent = Intent()
                launchIntent.setClassName(pack, "$pack.$act")
                launchIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

                launchIntent.putExtra("result", "successful")

                Toast.makeText(this@MainActivity, "$pack.$act", Toast.LENGTH_SHORT).show()

                if (launchIntent != null) {
                    this@MainActivity.startActivity(launchIntent)
                    finish()
                } else {
                    Log.i("TAG", "onCreate: there is not any app")
                    Toast.makeText(this@MainActivity, "there is not any app", Toast.LENGTH_SHORT).show()

                }

            } catch (e: Exception) {
                Log.i("TAG", "onCreate: $e")
                Toast.makeText(this@MainActivity, "$e", Toast.LENGTH_SHORT).show()
            }

        }
    }
}