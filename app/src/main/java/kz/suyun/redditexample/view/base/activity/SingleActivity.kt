package kz.suyun.redditexample.view.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.appcompat.widget.Toolbar
import kz.suyun.redditexample.R


class SingleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        //initializeBinding()
       // initializeToolbar()
    }

    fun initiateGetNavigationController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        return navHostFragment.navController
    }

    fun initializeBinding(){
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_single)
    }

//    fun initializeToolbar(){
//        val toolbar: Toolbar = findViewById(R.id.toolbar_activity_toolbar_main)
//        setSupportActionBar(toolbar)
//    }
}