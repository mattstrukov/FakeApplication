package com.strukov.fakeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.strukov.fake_feature.ui.FakeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentByTag(FakeFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, FakeFragment.newInstance(), FakeFragment.TAG)
                .commit()
        }
    }
}