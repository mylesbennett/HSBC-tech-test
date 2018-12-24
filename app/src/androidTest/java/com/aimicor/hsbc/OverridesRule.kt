package com.aimicor.hsbc

import android.support.test.InstrumentationRegistry
import com.aimicor.hsbc.view.MainApplication
import org.junit.rules.ExternalResource
import org.kodein.di.Kodein

/**
 * Used to retrieve test application before every test and override dependencies.
 */
class OverridesRule(private val bindings: Kodein.MainBuilder.() -> Unit = {}) : ExternalResource() {

    private fun app() = InstrumentationRegistry.getTargetContext().applicationContext as MainApplication

  override fun before() {
    app().bindingsOverride = bindings
  }

  override fun after() {
      app().bindingsOverride = {}
  }
}
