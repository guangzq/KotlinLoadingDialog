# KotlinLoadingDialog
## It's a LoadingDialog by Kotlin

![gif](https://github.com/guangzq/KotlinLoadingDialog/blob/master/app/src/main/java/com/zqg/kotlin/loading.gif)
###使用方式
```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnShow = findViewById(R.id.btn_show) as Button
        btnShow.setOnClickListener {
            val dialog = LoadingDialog(this, "加载中")
            dialog.show()
            Handler().postDelayed({ dialog.close() }, 5000)
        }
    }
}
```