package com.aimicor.hsbc

import com.aimicor.hsbc.view.PresenterDelegate.Companion.presenterBindingsOverride
import org.junit.rules.ExternalResource
import org.kodein.di.Kodein

/**
 * Used to retrieve test application before every test and override dependencies.
 */
class PresenterOverridesRule(private val bindings: Kodein.MainBuilder.() -> Unit = {}) : ExternalResource() {

  override fun before() {
    presenterBindingsOverride = bindings
  }

  override fun after() {
    presenterBindingsOverride = {}
  }
}
