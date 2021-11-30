package pl.mateuszmigot.brats_information.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat.*
import androidx.webkit.WebViewFeature
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.databinding.FragmentGeneralInfoBinding

class GeneralInfoFragment : Fragment() {

    private var _binding: FragmentGeneralInfoBinding? = null
    private lateinit var webView: WebView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var page = "index.html"
        page = requireArguments().getString("page").orEmpty()
        _binding = FragmentGeneralInfoBinding.inflate(inflater, container, false)
        webView = binding.root.findViewById<WebView>(R.id.WebView)
        setDarkModeWithStrategyForWebView()
        loadPage(page ?: "index.html")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        resources.configuration.setLocale(newConfig.locales.get(0))
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

    fun loadPage(pageName: String) {
        val lang = resources.configuration.locales.get(0).language
        webView.loadUrl("file:///android_asset/$lang/$pageName")
    }

    private fun setDarkModeWithStrategyForWebView() {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            setDarkModeStrategyIfPossible()
            setDarkModeForWebView()
        }
    }

    private fun setDarkModeStrategyIfPossible() {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
            setForceDarkStrategy(
                webView.settings,
                DARK_STRATEGY_PREFER_WEB_THEME_OVER_USER_AGENT_DARKENING
            )
        }
    }

    @SuppressLint("RequiresFeature")
    private fun setDarkModeForWebView() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                setForceDark(webView.settings, FORCE_DARK_ON)
            }
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                setForceDark(webView.settings, FORCE_DARK_OFF)
            }
        }
    }
}