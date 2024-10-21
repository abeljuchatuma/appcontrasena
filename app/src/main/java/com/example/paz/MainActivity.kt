import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.paz.R
import java.security.SecureRandom

class MainActivity : AppCompatActivity() {

    private lateinit var tvPassword: TextView
    private lateinit var btnGenerate: Button
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private val sharedPreferences by lazy {
        getSharedPreferences("passwords", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPassword = findViewById(R.id.tv_password)
        btnGenerate = findViewById(R.id.btn_generate)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        btnGenerate.setOnClickListener {
            tvPassword.text = generatePassword(12) // Cambia 12 por la longitud deseada
        }

        btnSave.setOnClickListener {
            savePassword(tvPassword.text.toString())
        }

        btnDelete.setOnClickListener {
            deletePassword()
        }
    }

    private fun generatePassword(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()"
        val password = StringBuilder()
        val random = SecureRandom()
        for (i in 0 until length) {
            password.append(chars[random.nextInt(chars.length)])
        }
        return password.toString()
    }

    private fun savePassword(password: String) {
        with(sharedPreferences.edit()) {
            putString("saved_password", password)
            apply()
        }
    }

    private fun deletePassword() {
        with(sharedPreferences.edit()) {
            remove("saved_password")
            apply()
        }
        tvPassword.text = "Contraseña eliminada"
    }
}
